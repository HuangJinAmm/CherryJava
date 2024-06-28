package com.cherryjava.admin.service.login.dto;

import com.cherryjava.common.enums.dictionary.DictionaryData;

import java.util.List;
import java.util.Map;

/**
 * @author hjamm
 */
public class ConfigDTO {

    private Boolean isCaptchaOn;

    private Map<String, List<DictionaryData>> dictionary;

    public Boolean getIsCaptchaOn() {
        return isCaptchaOn;
    }

    public void setIsCaptchaOn(Boolean captchaOn) {
        isCaptchaOn = captchaOn;
    }

    public Map<String, List<DictionaryData>> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<String, List<DictionaryData>> dictionary) {
        this.dictionary = dictionary;
    }


}
