package com.cherryjava.common.enums;

/**
 * @param <T>
 * @author hjamm
 * 普通的枚举 接口
 */
public interface BasicEnum<T> {


    /**
     * 获取枚举的值
     *
     * @return 枚举值
     */
    T getValue();

    /**
     * 获取枚举的描述
     *
     * @return 描述
     */
    String description();


}
