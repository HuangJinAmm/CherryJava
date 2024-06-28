package com.cherryjava.basesys.system.log;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cherryjava.basesys.common.command.BulkOperationCommand;
import com.cherryjava.basesys.system.log.db.SysLoginInfoEntity;
import com.cherryjava.basesys.system.log.db.SysLoginInfoService;
import com.cherryjava.basesys.system.log.db.SysOperationLogEntity;
import com.cherryjava.basesys.system.log.db.SysOperationLogService;
import com.cherryjava.basesys.system.log.dto.LoginLogDTO;
import com.cherryjava.basesys.system.log.dto.OperationLogDTO;
import com.cherryjava.basesys.system.log.dto.OperationLogQuery;
import com.cherryjava.basesys.system.log.query.LoginLogQuery;
import com.cherryjava.common.core.page.PageDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hjamm
 */
@Service
public class LogApplicationService {

    private final SysLoginInfoService loginInfoService;

    private final SysOperationLogService operationLogService;

    public LogApplicationService(SysLoginInfoService loginInfoService, SysOperationLogService operationLogService) {
        this.loginInfoService = loginInfoService;
        this.operationLogService = operationLogService;
    }

    public PageDTO<LoginLogDTO> getLoginInfoList(LoginLogQuery query) {
        Page<SysLoginInfoEntity> page = loginInfoService.page(query.toPage(), query.toQueryWrapper());
        List<LoginLogDTO> records = page.getRecords().stream().map(LoginLogDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public void deleteLoginInfo(BulkOperationCommand<Long> deleteCommand) {
        QueryWrapper<SysLoginInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("info_id", deleteCommand.getIds());
        loginInfoService.remove(queryWrapper);
    }

    public PageDTO<OperationLogDTO> getOperationLogList(OperationLogQuery query) {
        Page<SysOperationLogEntity> page = operationLogService.page(query.toPage(), query.toQueryWrapper());
        List<OperationLogDTO> records = page.getRecords().stream().map(OperationLogDTO::new).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public void deleteOperationLog(BulkOperationCommand<Long> deleteCommand) {
        operationLogService.removeBatchByIds(deleteCommand.getIds());
    }

}
