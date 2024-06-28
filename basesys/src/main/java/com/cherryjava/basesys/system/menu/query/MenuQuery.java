package com.cherryjava.basesys.system.menu.query;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cherryjava.basesys.system.menu.db.SysMenuEntity;
import com.cherryjava.common.core.page.AbstractQuery;

/**
 * @author hjamm
 */
public class MenuQuery extends AbstractQuery<SysMenuEntity> {
    // 直接交给前端筛选
//    private String menuName;
//    private Boolean isVisible;
//    private Integer status;
    private Boolean isButton;

    public Boolean getIsButton() {
        return isButton;
    }

    public void setIsButton(Boolean button) {
        isButton = button;
    }

    @Override
    public QueryWrapper<SysMenuEntity> addQueryCondition() {
        QueryWrapper<SysMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(isButton != null, "is_button", isButton);
//            .like(StrUtil.isNotEmpty(menuName), "menu_name", menuName)
//            .eq(isVisible != null, "is_visible", isVisible)
//            .eq(status != null, "status", status);
        this.orderColumn = "parent_id";
        this.orderDirection = "descending";
        return queryWrapper;
    }
}
