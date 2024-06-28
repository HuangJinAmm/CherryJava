package com.cherryjava.common.config;

import com.cherryjava.common.constant.Constants;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 项目相关配置
 *
 * @author hjamm
 */
@Component
@ConfigurationProperties(prefix = "project")
public class ProjectConfig {

    /**
     * 项目名称
     */
    private String name;

    /**
     * 版本
     */
    private String version;

    /**
     * 版权年份
     */
    private String copyrightYear;

    /**
     * 实例演示开关
     */
    private static boolean demoEnabled;

    /**
     * 上传路径
     */
    private static String fileBaseDir;

    /**
     * 获取地址开关
     */
    private static boolean addressEnabled;

    /**
     * 验证码类型
     */
    private static String captchaType;

    /**
     * rsa private key  静态属性的注入！！ set方法一定不能是static 方法
     */
    private static String rsaPrivateKey;

    private static String apiPrefix;
    private String checkI18nKey;
    private String traceRequestIdKey;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCopyrightYear() {
        return copyrightYear;
    }

    public void setCopyrightYear(String copyrightYear) {
        this.copyrightYear = copyrightYear;
    }

    public static String getFileBaseDir() {
        return fileBaseDir;
    }

    public void setFileBaseDir(String fileBaseDir) {
        ProjectConfig.fileBaseDir = fileBaseDir + File.separator + Constants.RESOURCE_PREFIX;
    }

    public static String getApiPrefix() {
        return apiPrefix;
    }

    public void setApiPrefix(String apiDocsPathPrefix) {
        ProjectConfig.apiPrefix = apiDocsPathPrefix;
    }

    public static boolean isAddressEnabled() {
        return addressEnabled;
    }

    public void setAddressEnabled(boolean addressEnabled) {
        ProjectConfig.addressEnabled = addressEnabled;
    }

    public static String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        ProjectConfig.captchaType = captchaType;
    }

    public static String getRsaPrivateKey() {
        return rsaPrivateKey;
    }

    public void setRsaPrivateKey(String rsaPrivateKey) {
        ProjectConfig.rsaPrivateKey = rsaPrivateKey;
    }

    public static boolean isDemoEnabled() {
        return demoEnabled;
    }

    public void setDemoEnabled(boolean demoEnabled) {
        ProjectConfig.demoEnabled = demoEnabled;
    }

    public String getCheckI18nKey() {
        return checkI18nKey;
    }

    public void setCheckI18nKey(String checkI18nKey) {
        this.checkI18nKey = checkI18nKey;
    }

    public String getTraceRequestIdKey() {
        return traceRequestIdKey;
    }

    public void setTraceRequestIdKey(String traceRequestIdKey) {
        this.traceRequestIdKey = traceRequestIdKey;
    }
}
