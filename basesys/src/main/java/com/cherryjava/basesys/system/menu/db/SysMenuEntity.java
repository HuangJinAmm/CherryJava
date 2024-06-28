package com.cherryjava.basesys.system.menu.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cherryjava.common.core.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 菜单权限表
 * </p>
 *
 * @author hjamm
 * @since 2023-07-21
 */
@TableName("sys_menu")
public class SysMenuEntity extends BaseEntity<SysMenuEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     **/
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单名称
     **/
    @TableField("menu_name")
    private String menuName;

    /**
     * 菜单的类型(1为普通菜单2为目录3为iFrame4为外部网站)
     **/
    @TableField("menu_type")
    private Integer menuType;

    /**
     * 路由名称（需保持和前端对应的vue文件中的name保持一致defineOptions方法中设置的name）
     **/
    @TableField("router_name")
    private String routerName;

    /**
     * 父菜单ID
     **/
    @TableField("parent_id")
    private Long parentId;

    /**
     * 组件路径（对应前端项目view文件夹中的路径）
     **/
    @TableField("path")
    private String path;

    /**
     * 是否按钮
     **/
    @TableField("is_button")
    private Boolean isButton;

    /**
     * 权限标识
     **/
    @TableField("permission")
    private String permission;

    /**
     * 路由元信息（前端根据这个信息进行逻辑处理）
     **/
    @TableField("meta_info")
    private String metaInfo;

    /**
     * 菜单状态（1启用 0停用）
     */
    @TableField("`status`")
    private Integer status;

    /**
     * 备注
     **/
    @TableField("remark")
    private String remark;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Boolean getIsButton() {
        return isButton;
    }

    public void setIsButton(Boolean button) {
        isButton = button;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getMetaInfo() {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo) {
        this.metaInfo = metaInfo;
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
        return this.menuId;
    }

}
