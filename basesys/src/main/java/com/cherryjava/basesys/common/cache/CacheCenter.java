package com.cherryjava.basesys.common.cache;

import cn.hutool.extra.spring.SpringUtil;
import com.cherryjava.basesys.system.dept.db.SysDeptEntity;
import com.cherryjava.basesys.system.post.db.SysPostEntity;
import com.cherryjava.basesys.system.role.db.SysRoleEntity;
import com.cherryjava.basesys.system.user.db.SysUserEntity;
import com.cherryjava.framework.cache.guava.AbstractGuavaCacheTemplate;
import com.cherryjava.framework.cache.redis.RedisCacheTemplate;
import com.cherryjava.framework.user.web.SystemLoginUser;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 缓存中心  提供全局访问点
 * 如果是领域类的缓存  可以自己新建一个直接放在CacheCenter   不用放在infrastructure包里的GuavaCacheService
 * 或者RedisCacheService
 *
 * @author hjamm
 */
@Component
public class CacheCenter {

    public static AbstractGuavaCacheTemplate<String> configCache;

    public static AbstractGuavaCacheTemplate<SysDeptEntity> deptCache;

    public static RedisCacheTemplate<String> captchaCache;

    public static RedisCacheTemplate<SystemLoginUser> loginUserCache;

    public static RedisCacheTemplate<SysUserEntity> userCache;

    public static RedisCacheTemplate<SysRoleEntity> roleCache;

    public static RedisCacheTemplate<SysPostEntity> postCache;

    @PostConstruct
    public void init() {
        GuavaCacheService guavaCache = SpringUtil.getBean(GuavaCacheService.class);
        RedisCacheService redisCache = SpringUtil.getBean(RedisCacheService.class);

        configCache = guavaCache.configCache;
        deptCache = guavaCache.deptCache;

        captchaCache = redisCache.captchaCache;
        loginUserCache = redisCache.loginUserCache;
        userCache = redisCache.userCache;
        roleCache = redisCache.roleCache;
        postCache = redisCache.postCache;
    }

}
