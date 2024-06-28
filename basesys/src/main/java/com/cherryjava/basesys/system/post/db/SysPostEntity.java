package com.cherryjava.basesys.system.post.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cherryjava.common.core.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 岗位信息表
 * </p>
 *
 * @author hjamm
 * @since 2022-10-02
 */
@TableName("sys_post")
public class SysPostEntity extends BaseEntity<SysPostEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 岗位ID
     **/
    @TableId(value = "post_id", type = IdType.AUTO)
    private Long postId;

    /**
     * 岗位编码
     **/
    @TableField("post_code")
    private String postCode;

    /**
     * 岗位名称
     **/
    @TableField("post_name")
    private String postName;

    /**
     * 显示顺序
     **/
    @TableField("post_sort")
    private Integer postSort;

    /**
     * "状态（1正常 0停用）"
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 备注
     **/
    @TableField("remark")
    private String remark;

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
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

    public Integer getPostSort() {
        return postSort;
    }

    public void setPostSort(Integer postSort) {
        this.postSort = postSort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Serializable pkVal() {
        return this.postId;
    }

}
