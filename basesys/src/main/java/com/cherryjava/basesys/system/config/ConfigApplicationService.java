package com.cherryjava.basesys.system.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cherryjava.basesys.common.cache.CacheCenter;
import com.cherryjava.basesys.system.config.command.ConfigUpdateCommand;
import com.cherryjava.basesys.system.config.db.SysConfigEntity;
import com.cherryjava.basesys.system.config.db.SysConfigService;
import com.cherryjava.basesys.system.config.dto.ConfigDTO;
import com.cherryjava.basesys.system.config.model.ConfigModel;
import com.cherryjava.basesys.system.config.model.ConfigModelFactory;
import com.cherryjava.basesys.system.config.query.ConfigQuery;
import com.cherryjava.common.core.page.PageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjamm
 */
@Service
public class ConfigApplicationService {

    private final ConfigModelFactory configModelFactory;

    private final SysConfigService configService;

    public ConfigApplicationService(ConfigModelFactory configModelFactory, SysConfigService configService) {
        this.configModelFactory = configModelFactory;
        this.configService = configService;
    }

    public PageDTO<ConfigDTO> getConfigList(ConfigQuery query) {
        Page<SysConfigEntity> page = configService.page(query.toPage(), query.toQueryWrapper());
        List<ConfigDTO> records = page.getRecords().stream().map(ConfigDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public ConfigDTO getConfigInfo(Long id) {
        SysConfigEntity byId = configService.getById(id);
        return new ConfigDTO(byId);
    }

    public void updateConfig(ConfigUpdateCommand updateCommand) {
        ConfigModel configModel = configModelFactory.loadById(updateCommand.getConfigId());
        configModel.loadUpdateCommand(updateCommand);

        configModel.checkCanBeModify();

        configModel.updateById();

        CacheCenter.configCache.invalidate(configModel.getConfigKey());
    }


}
