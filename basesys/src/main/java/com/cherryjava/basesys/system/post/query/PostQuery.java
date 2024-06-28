package com.cherryjava.basesys.system.post.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cherryjava.basesys.system.post.db.SysPostEntity;
import com.cherryjava.common.core.page.AbstractPageQuery;

/**
 * @author hjamm
 */
public class PostQuery extends AbstractPageQuery<SysPostEntity> {

    private String postCode;
    private String postName;
    private Integer status;

    public PostQuery() {
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public QueryWrapper<SysPostEntity> addQueryCondition() {
        QueryWrapper<SysPostEntity> queryWrapper = new QueryWrapper<SysPostEntity>()
                .eq(status != null, "status", status)
                .eq(StrUtil.isNotEmpty(postCode), "post_code", postCode)
                .like(StrUtil.isNotEmpty(postName), "post_name", postName);
        // 当前端没有选择排序字段时，则使用post_sort字段升序排序（在父类AbstractQuery中默认为升序）
        if (StrUtil.isEmpty(this.getOrderColumn())) {
            this.setOrderColumn("post_sort");
        }
        this.setTimeRangeColumn("create_time");

        return queryWrapper;
    }


}
