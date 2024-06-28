package com.cherryjava.admin.controller.system;


import com.cherryjava.admin.accessLog.AccessLog;
import com.cherryjava.basesys.common.command.BulkOperationCommand;
import com.cherryjava.basesys.system.log.LogApplicationService;
import com.cherryjava.basesys.system.log.dto.LoginLogDTO;
import com.cherryjava.basesys.system.log.dto.OperationLogDTO;
import com.cherryjava.basesys.system.log.dto.OperationLogQuery;
import com.cherryjava.basesys.system.log.query.LoginLogQuery;
import com.cherryjava.common.core.base.BaseController;
import com.cherryjava.common.core.dto.ResponseDTO;
import com.cherryjava.common.core.page.PageDTO;
import com.cherryjava.common.enums.common.BusinessTypeEnum;
import com.cherryjava.common.utils.poi.CustomExcelUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统访问记录
 *
 * @author valarchie
 */
@RestController
@RequestMapping("/logs")
@Validated
public class SysLogsController extends BaseController {

    private final LogApplicationService logApplicationService;

    public SysLogsController(LogApplicationService logApplicationService) {
        this.logApplicationService = logApplicationService;
    }

    @PreAuthorize("@permission.has('monitor:logininfor:list')")
    @GetMapping("/loginLogs")
    public ResponseDTO<PageDTO<LoginLogDTO>> loginInfoList(LoginLogQuery query) {
        PageDTO<LoginLogDTO> pageDTO = logApplicationService.getLoginInfoList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @AccessLog(title = "登录日志", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('monitor:logininfor:export')")
    @GetMapping("/loginLogs/excel")
    public void loginInfosExcel(HttpServletResponse response, LoginLogQuery query) {
        PageDTO<LoginLogDTO> pageDTO = logApplicationService.getLoginInfoList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), LoginLogDTO.class, response);
    }

    @PreAuthorize("@permission.has('monitor:logininfor:remove')")
    @AccessLog(title = "登录日志", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/loginLogs")
    public ResponseDTO<Void> removeLoginInfos(@RequestParam @NotNull @NotEmpty List<Long> ids) {
        logApplicationService.deleteLoginInfo(new BulkOperationCommand<>(ids));
        return ResponseDTO.ok();
    }

    @PreAuthorize("@permission.has('monitor:operlog:list')")
    @GetMapping("/operationLogs")
    public ResponseDTO<PageDTO<OperationLogDTO>> operationLogs(OperationLogQuery query) {
        PageDTO<OperationLogDTO> pageDTO = logApplicationService.getOperationLogList(query);
        return ResponseDTO.ok(pageDTO);
    }

//    @GetMapping("/download")
//    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
//        // 从文件系统或其他位置获取文件输入流
//        File file = new File("path/to/file");
//        InputStream inputStream = new FileInputStream(file);
//        CustomExcelUtil.wri
//
//        // 创建一个 InputStreamResource 对象，将文件输入流包装在其中
//        InputStreamResource resource = new InputStreamResource(inputStream);
//
//        // 返回 ResponseEntity 对象，其中包含 InputStreamResource 对象和文件名
//        return ResponseEntity.ok()
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .contentLength(file.length())
//                .body(resource);
//    }

    /**
     * 可否改成以上的形式 TODO
     *
     * @param response
     * @param query
     */
    @AccessLog(title = "操作日志", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('monitor:operlog:export')")
    @GetMapping("/operationLogs/excel")
    public void operationLogsExcel(HttpServletResponse response, OperationLogQuery query) {
        PageDTO<OperationLogDTO> pageDTO = logApplicationService.getOperationLogList(query);
        CustomExcelUtil.writeToResponse(pageDTO.getRows(), OperationLogDTO.class, response);
    }

    @AccessLog(title = "操作日志", businessType = BusinessTypeEnum.DELETE)
    @PreAuthorize("@permission.has('monitor:operlog:remove')")
    @DeleteMapping("/operationLogs")
    public ResponseDTO<Void> removeOperationLogs(@RequestParam List<Long> operationIds) {
        logApplicationService.deleteOperationLog(new BulkOperationCommand<>(operationIds));
        return ResponseDTO.ok();
    }


}
