package com.cherryjava.basesys.system.post.model;

import com.cherryjava.basesys.system.post.db.SysPostEntity;
import com.cherryjava.basesys.system.post.db.SysPostService;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * @author hjamm
 */
@Component
public class PostModelFactory {

    private final SysPostService postService;

    public PostModelFactory(SysPostService postService) {
        this.postService = postService;
    }

    public PostModel loadById(Long postId) {
        SysPostEntity byId = postService.getById(postId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, postId, "职位");
        }
        return new PostModel(byId, postService);
    }

    public PostModel create() {
        return new PostModel(postService);
    }

}
