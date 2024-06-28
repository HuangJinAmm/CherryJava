package com.cherryjava.basesys.system.menu.command;

import com.cherryjava.basesys.system.menu.dto.MetaDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author hjamm
 */
public class AddMenuCommand {

    private Long parentId;
    @NotBlank(message = "菜单名称不能为空")
    @Size(max = 50, message = "菜单名称长度不能超过50个字符")
    private String menuName;
    /**
     * 路由名称 必须唯一
     */
    private String routerName;

    @Size(max = 200, message = "路由地址不能超过200个字符")
    private String path;

    private Integer status;
    private Integer menuType;

    private Boolean isButton;

    @Size(max = 100, message = "权限标识长度不能超过100个字符")
    private String permission;

    private MetaDTO meta;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Boolean getButton() {
        return isButton;
    }

    public void setButton(Boolean button) {
        isButton = button;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public MetaDTO getMeta() {
        return meta;
    }

    public void setMeta(MetaDTO meta) {
        this.meta = meta;
    }
}
