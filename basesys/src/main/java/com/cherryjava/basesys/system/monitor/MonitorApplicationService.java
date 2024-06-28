package com.cherryjava.basesys.system.monitor;

import cn.hutool.core.util.StrUtil;
import com.cherryjava.basesys.common.cache.CacheCenter;
import com.cherryjava.basesys.system.monitor.dto.OnlineUserDTO;
import com.cherryjava.basesys.system.monitor.dto.RedisCacheInfoDTO;
import com.cherryjava.basesys.system.monitor.dto.ServerInfo;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import com.cherryjava.framework.cache.redis.CacheKeyEnum;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author hjamm
 */
@Service
public class MonitorApplicationService {

    private final RedisTemplate<String, ?> redisTemplate;

    public MonitorApplicationService(RedisTemplate<String, ?> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisCacheInfoDTO getRedisCacheInfo() {
        Properties info = (Properties) redisTemplate.execute((RedisCallback<Object>) RedisServerCommands::info);
        Properties commandStats = (Properties) redisTemplate.execute(
                (RedisCallback<Object>) connection -> connection.info("commandstats"));
        Long dbSize = redisTemplate.execute(RedisServerCommands::dbSize);

        if (commandStats == null || info == null) {
            throw new ApiException(ErrorCode.Internal.INTERNAL_ERROR, "获取Redis监控信息失败。");
        }

        RedisCacheInfoDTO cacheInfo = new RedisCacheInfoDTO();

        cacheInfo.setInfo(info);
        cacheInfo.setDbSize(dbSize);
        cacheInfo.setCommandStats(new ArrayList<>());

        commandStats.stringPropertyNames().forEach(key -> {
            String property = commandStats.getProperty(key);

            RedisCacheInfoDTO.CommandStatusDTO commonStatus = new RedisCacheInfoDTO.CommandStatusDTO();
            commonStatus.setName(StrUtil.removePrefix(key, "cmdstat_"));
            commonStatus.setValue(StrUtil.subBetween(property, "calls=", ",usec"));

            cacheInfo.getCommandStats().add(commonStatus);
        });

        return cacheInfo;
    }

    public List<OnlineUserDTO> getOnlineUserList(String username, String ipAddress) {
        Collection<String> keys = redisTemplate.keys(CacheKeyEnum.LOGIN_USER_KEY.key() + "*");

        Stream<OnlineUserDTO> onlineUserStream = keys.stream().map(o ->
                CacheCenter.loginUserCache.getObjectOnlyInCacheByKey(o))
                .filter(Objects::nonNull).map(OnlineUserDTO::new);

        List<OnlineUserDTO> filteredOnlineUsers = onlineUserStream
                .filter(o ->
                        StrUtil.isEmpty(username) || username.equals(o.getUsername())
                ).filter(o ->
                        StrUtil.isEmpty(ipAddress) || ipAddress.equals(o.getIpAddress())
                ).collect(Collectors.toList());

        Collections.reverse(filteredOnlineUsers);
        return filteredOnlineUsers;
    }

    public ServerInfo getServerInfo() {
        return ServerInfo.fillInfo();
    }


}
