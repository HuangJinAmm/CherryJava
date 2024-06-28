package com.cherryjava.admin.controller.system;

import com.cherryjava.admin.accessLog.AccessLog;
import com.cherryjava.basesys.common.cache.CacheCenter;
import com.cherryjava.basesys.system.monitor.MonitorApplicationService;
import com.cherryjava.basesys.system.monitor.dto.OnlineUserDTO;
import com.cherryjava.basesys.system.monitor.dto.RedisCacheInfoDTO;
import com.cherryjava.basesys.system.monitor.dto.ServerInfo;
import com.cherryjava.common.core.base.BaseController;
import com.cherryjava.common.core.dto.ResponseDTO;
import com.cherryjava.common.core.page.PageDTO;
import com.cherryjava.common.enums.common.BusinessTypeEnum;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 缓存监控
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController extends BaseController {

    private final MonitorApplicationService monitorApplicationService;


    public MonitorController(MonitorApplicationService monitorApplicationService) {
        this.monitorApplicationService = monitorApplicationService;
    }

    @PreAuthorize("@permission.has('monitor:cache:list')")
    @GetMapping("/cacheInfo")
    public ResponseDTO<RedisCacheInfoDTO> getRedisCacheInfo() {
        RedisCacheInfoDTO redisCacheInfo = monitorApplicationService.getRedisCacheInfo();
        return ResponseDTO.ok(redisCacheInfo);
    }


    @PreAuthorize("@permission.has('monitor:server:list')")
    @GetMapping("/serverInfo")
    public ResponseDTO<ServerInfo> getServerInfo() {
        ServerInfo serverInfo = monitorApplicationService.getServerInfo();
        return ResponseDTO.ok(serverInfo);
    }

    /**
     * 获取在线用户列表
     *
     * @param ipAddress ip地址
     * @param username  用户名
     * @return 分页处理后的在线用户信息
     */
    @PreAuthorize("@permission.has('monitor:online:list')")
    @GetMapping("/onlineUsers")
    public ResponseDTO<PageDTO<OnlineUserDTO>> onlineUsers(String ipAddress, String username) {
        List<OnlineUserDTO> onlineUserList = monitorApplicationService.getOnlineUserList(username, ipAddress);
        return ResponseDTO.ok(new PageDTO<>(onlineUserList));
    }

    /**
     * 强退用户
     */
    @PreAuthorize("@permission.has('monitor:online:forceLogout')")
    @AccessLog(title = "在线用户", businessType = BusinessTypeEnum.FORCE_LOGOUT)
    @DeleteMapping("/onlineUser/{tokenId}")
    public ResponseDTO<Void> logoutOnlineUser(@PathVariable String tokenId) {
        CacheCenter.loginUserCache.delete(tokenId);
        return ResponseDTO.ok();
    }


}
