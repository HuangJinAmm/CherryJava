package com.cherryjava.basesys.system.config.model;

import com.cherryjava.basesys.system.config.db.SysConfigEntity;
import com.cherryjava.basesys.system.config.db.SysConfigService;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * 配置模型工厂
 *
 * @author hjamm
 */
@Component
public class ConfigModelFactory {

    private final SysConfigService configService;

    public ConfigModelFactory(SysConfigService configService) {
        this.configService = configService;
    }

    public ConfigModel loadById(Long configId) {
        SysConfigEntity byId = configService.getById(configId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, configId, "参数配置");
        }
        return new ConfigModel(byId, configService);
    }

    public ConfigModel create() {
        return new ConfigModel(configService);
    }


}
