package com.cherryjava.basesys.system.menu.model;

import com.cherryjava.basesys.system.menu.db.SysMenuEntity;
import com.cherryjava.basesys.system.menu.db.SysMenuService;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * 菜单模型工厂
 *
 * @author hjamm
 */
@Component
public class MenuModelFactory {

    private final SysMenuService menuService;

    public MenuModelFactory(SysMenuService menuService) {
        this.menuService = menuService;
    }

    public MenuModel loadById(Long menuId) {
        SysMenuEntity byId = menuService.getById(menuId);
        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, menuId, "菜单");
        }
        return new MenuModel(byId, menuService);
    }

    public MenuModel create() {
        return new MenuModel(menuService);
    }


}
