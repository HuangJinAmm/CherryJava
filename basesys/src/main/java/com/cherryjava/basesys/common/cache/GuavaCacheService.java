package com.cherryjava.basesys.common.cache;


import com.cherryjava.basesys.system.config.db.SysConfigService;
import com.cherryjava.basesys.system.dept.db.SysDeptEntity;
import com.cherryjava.basesys.system.dept.db.SysDeptService;
import com.cherryjava.framework.cache.guava.AbstractGuavaCacheTemplate;
import org.springframework.stereotype.Component;

/**
 * @author hjamm
 */
@Component
public class GuavaCacheService {

    private final SysConfigService configService;

    private final SysDeptService deptService;

    public GuavaCacheService(SysConfigService configService, SysDeptService deptService) {
        this.configService = configService;
        this.deptService = deptService;
    }

    public final AbstractGuavaCacheTemplate<String> configCache = new AbstractGuavaCacheTemplate<String>() {
        @Override
        public String getObjectFromDb(Object id) {
            return configService.getConfigValueByKey(id.toString());
        }
    };

    public final AbstractGuavaCacheTemplate<SysDeptEntity> deptCache = new AbstractGuavaCacheTemplate<SysDeptEntity>() {
        @Override
        public SysDeptEntity getObjectFromDb(Object id) {
            return deptService.getById(id.toString());
        }
    };


}
