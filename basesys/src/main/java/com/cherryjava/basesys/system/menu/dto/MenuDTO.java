package com.cherryjava.basesys.system.menu.dto;

import cn.hutool.core.util.StrUtil;
import com.cherryjava.basesys.system.menu.db.SysMenuEntity;
import com.cherryjava.common.enums.BasicEnumUtil;
import com.cherryjava.common.enums.common.MenuTypeEnum;
import com.cherryjava.common.enums.common.StatusEnum;
import com.cherryjava.common.utils.jackson.JacksonUtil;

import java.util.Date;

/**
 * @author hjamm
 */
public class MenuDTO {

    public MenuDTO() {
    }

    public MenuDTO(SysMenuEntity entity) {
        if (entity != null) {
            this.id = entity.getMenuId();
            this.parentId = entity.getParentId();
            this.menuName = entity.getMenuName();
            this.routerName = entity.getRouterName();
            this.path = entity.getPath();
            this.status = entity.getStatus();
            this.isButton = entity.getIsButton();
            this.statusStr = BasicEnumUtil.getDescriptionByValue(StatusEnum.class, entity.getStatus());

            if (!entity.getIsButton()) {
                this.menuType = entity.getMenuType();
                this.menuTypeStr = BasicEnumUtil.getDescriptionByValue(MenuTypeEnum.class, entity.getMenuType());
            } else {
                this.menuType = 0;
            }

            if (StrUtil.isNotEmpty(entity.getMetaInfo()) && JacksonUtil.isJson(entity.getMetaInfo())) {
                MetaDTO meta = JacksonUtil.from(entity.getMetaInfo(), MetaDTO.class);
                this.rank = meta.getRank();
                this.icon = meta.getIcon();
            }
            this.createTime = entity.getCreateTime();
        }
    }

    // 设置成id和parentId 便于前端处理树级结构
    private Long id;

    private Long parentId;

    private String menuName;

    private String routerName;

    private String path;

    private Integer rank;

    private Integer menuType;

    private String menuTypeStr;

    private Boolean isButton;

    private Integer status;

    private String statusStr;

    private Date createTime;

    private String icon;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public String getMenuTypeStr() {
        return menuTypeStr;
    }

    public void setMenuTypeStr(String menuTypeStr) {
        this.menuTypeStr = menuTypeStr;
    }

    public Boolean getButton() {
        return isButton;
    }

    public void setButton(Boolean button) {
        isButton = button;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
