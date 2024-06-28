package com.cherryjava.basesys.system.menu.dto;


/**
 * @author hjamm
 */
public class ExtraIconDTO {

    // 是否是svg
    private boolean svg;
    // iconfont名称，目前只支持iconfont，后续拓展
    private String name;

    public boolean isSvg() {
        return svg;
    }

    public void setSvg(boolean svg) {
        this.svg = svg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
