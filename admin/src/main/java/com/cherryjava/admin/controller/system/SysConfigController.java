package com.cherryjava.admin.controller.system;

import com.cherryjava.admin.accessLog.AccessLog;
import com.cherryjava.basesys.common.cache.CacheCenter;
import com.cherryjava.basesys.system.config.ConfigApplicationService;
import com.cherryjava.basesys.system.config.command.ConfigUpdateCommand;
import com.cherryjava.basesys.system.config.dto.ConfigDTO;
import com.cherryjava.basesys.system.config.query.ConfigQuery;
import com.cherryjava.common.core.base.BaseController;
import com.cherryjava.common.core.dto.ResponseDTO;
import com.cherryjava.common.core.page.PageDTO;
import com.cherryjava.common.enums.common.BusinessTypeEnum;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * 参数配置 信息操作处理
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/system")
@Validated
public class SysConfigController extends BaseController {

    private final ConfigApplicationService configApplicationService;

    public SysConfigController(ConfigApplicationService configApplicationService) {
        this.configApplicationService = configApplicationService;
    }

    /**
     * 获取参数配置列表
     */
    @PreAuthorize("@permission.has('system:config:list')")
    @GetMapping("/configs")
    public ResponseDTO<PageDTO<ConfigDTO>> list(ConfigQuery query) {
        PageDTO<ConfigDTO> page = configApplicationService.getConfigList(query);
        return ResponseDTO.ok(page);
    }

    /**
     * 根据参数编号获取详细信息
     */
    @PreAuthorize("@permission.has('system:config:query')")
    @GetMapping(value = "/config/{configId}")
    public ResponseDTO<ConfigDTO> getInfo(@NotNull @Positive @PathVariable Long configId) {
        ConfigDTO config = configApplicationService.getConfigInfo(configId);
        return ResponseDTO.ok(config);
    }


    /**
     * 修改参数配置
     */
    @PreAuthorize("@permission.has('system:config:edit')")
    @AccessLog(title = "参数管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping(value = "/config/{configId}")
    public ResponseDTO<Void> edit(@NotNull @Positive @PathVariable Long configId, @RequestBody ConfigUpdateCommand config) {
        config.setConfigId(configId);
        configApplicationService.updateConfig(config);
        return ResponseDTO.ok();
    }

    /**
     * 刷新参数缓存
     */
    @PreAuthorize("@permission.has('system:config:remove')")
    @AccessLog(title = "参数管理", businessType = BusinessTypeEnum.CLEAN)
    @DeleteMapping("/configs/cache")
    public ResponseDTO<Void> refreshCache() {
        CacheCenter.configCache.invalidateAll();
        return ResponseDTO.ok();
    }
}
