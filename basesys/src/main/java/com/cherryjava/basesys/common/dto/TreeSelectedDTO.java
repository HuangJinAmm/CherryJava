package com.cherryjava.basesys.common.dto;

import cn.hutool.core.lang.tree.Tree;

import java.util.List;

/**
 * @author hjamm
 */
public class TreeSelectedDTO {

    private List<Long> checkedKeys;
    private List<Tree<Long>> menus;
    private List<Tree<Long>> depts;

    public TreeSelectedDTO() {
    }

    public List<Long> getCheckedKeys() {
        return checkedKeys;
    }

    public void setCheckedKeys(List<Long> checkedKeys) {
        this.checkedKeys = checkedKeys;
    }

    public List<Tree<Long>> getMenus() {
        return menus;
    }

    public void setMenus(List<Tree<Long>> menus) {
        this.menus = menus;
    }

    public List<Tree<Long>> getDepts() {
        return depts;
    }

    public void setDepts(List<Tree<Long>> depts) {
        this.depts = depts;
    }
}
