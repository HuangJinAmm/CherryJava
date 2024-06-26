package com.cherryjava.common.enums.dictionary;

import com.cherryjava.common.enums.DictionaryEnum;

/**
 * 字典模型类
 *
 * @author hjamm
 */
public class DictionaryData {

    private String label;
    private Integer value;
    private String cssTag;

    @SuppressWarnings("rawtypes")
    public DictionaryData(DictionaryEnum enumType) {
        if (enumType != null) {
            this.label = enumType.description();
            this.value = (Integer) enumType.getValue();
            this.cssTag = enumType.cssTag();
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getCssTag() {
        return cssTag;
    }

    public void setCssTag(String cssTag) {
        this.cssTag = cssTag;
    }
}
