package com.cherryjava.basesys.system.menu.command;

import javax.validation.constraints.NotNull;

/**
 * @author hjamm
 */
public class UpdateMenuCommand extends AddMenuCommand {

    @NotNull
    private Long menuId;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}
