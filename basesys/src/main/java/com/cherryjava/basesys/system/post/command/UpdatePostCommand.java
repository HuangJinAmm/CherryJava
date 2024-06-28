package com.cherryjava.basesys.system.post.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author hjamm
 */
public class UpdatePostCommand extends AddPostCommand {

    @NotNull(message = "岗位ID不能为空")
    @Positive
    private Long postId;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}
