package com.cherryjava.common.enums.common;

import com.cherryjava.common.enums.BasicEnum;
import com.cherryjava.common.enums.dictionary.Dictionary;

/**
 * 操作者类型
 *
 * @author hjamm
 */
@Dictionary(name = "sysOperationLog.operatorType")
public enum OperatorTypeEnum implements BasicEnum<Integer> {

    /**
     * 菜单类型
     */
    OTHER(1, "其他"),
    WEB(2, "Web用户"),
    MOBILE(3, "手机端用户");

    private final int value;
    private final String description;

    OperatorTypeEnum(int value, String description) {
        this.value = value;
        this.description = description;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String description() {
        return description;
    }


}
