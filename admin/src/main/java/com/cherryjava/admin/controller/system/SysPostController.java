package com.cherryjava.admin.controller.system;

import com.cherryjava.admin.accessLog.AccessLog;
import com.cherryjava.basesys.common.command.BulkOperationCommand;
import com.cherryjava.basesys.system.post.PostApplicationService;
import com.cherryjava.basesys.system.post.command.AddPostCommand;
import com.cherryjava.basesys.system.post.command.UpdatePostCommand;
import com.cherryjava.basesys.system.post.dto.PostDTO;
import com.cherryjava.basesys.system.post.query.PostQuery;
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
 * 岗位信息操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/post")
@Validated
public class SysPostController extends BaseController {

    private final PostApplicationService postApplicationService;

    public SysPostController(PostApplicationService postApplicationService) {
        this.postApplicationService = postApplicationService;
    }

    /**
     * 获取岗位列表
     */
    @PreAuthorize("@permission.has('system:post:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO<PostDTO>> list(PostQuery query) {
        PageDTO<PostDTO> pageDTO = postApplicationService.getPostList(query);
        return ResponseDTO.ok(pageDTO);
    }

    /**
     * 导出查询到的所有岗位信息到excel文件
     *
     * @param response http响应
     * @param query    查询参数
     * @author Kevin Zhang
     * @date 2023-10-02
     */
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.EXPORT)
    @PreAuthorize("@permission.has('system:post:export')")
    @GetMapping("/excel")
    public void export(HttpServletResponse response, PostQuery query) {
        List<PostDTO> all = postApplicationService.getPostListAll(query);
        CustomExcelUtil.writeToResponse(all, PostDTO.class, response);
    }

    /**
     * 根据岗位编号获取详细信息
     */
    @PreAuthorize("@permission.has('system:post:query')")
    @GetMapping(value = "/{postId}")
    public ResponseDTO<PostDTO> getInfo(@PathVariable Long postId) {
        PostDTO post = postApplicationService.getPostInfo(postId);
        return ResponseDTO.ok(post);
    }

    /**
     * 新增岗位
     */
    @PreAuthorize("@permission.has('system:post:add')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody AddPostCommand addCommand) {
        postApplicationService.addPost(addCommand);
        return ResponseDTO.ok();
    }

    /**
     * 修改岗位
     */
    @PreAuthorize("@permission.has('system:post:edit')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<Void> edit(@RequestBody UpdatePostCommand updateCommand) {
        postApplicationService.updatePost(updateCommand);
        return ResponseDTO.ok();
    }

    /**
     * 删除岗位
     */
    @PreAuthorize("@permission.has('system:post:remove')")
    @AccessLog(title = "岗位管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping
    public ResponseDTO<Void> remove(@RequestParam @NotNull @NotEmpty List<Long> ids) {
        postApplicationService.deletePost(new BulkOperationCommand<>(ids));
        return ResponseDTO.ok();
    }

}
