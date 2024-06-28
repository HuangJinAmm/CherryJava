package com.cherryjava.basesys.system.config.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.cherryjava.common.core.base.BaseEntity;

import java.io.Serializable;

/**
 * <p>
 * 参数配置表
 * </p>
 *
 * @author hjamm
 * @since 2022-11-03
 */
@TableName("sys_config")
public class SysConfigEntity extends BaseEntity<SysConfigEntity> {

    private static final long serialVersionUID = 1L;

    /**
     * 参数主键
     **/
    @TableId(value = "config_id", type = IdType.AUTO)
    private Integer configId;

    /**
     * 配置名称
     **/
    @TableField("config_name")
    private String configName;

    /**
     * 配置键名
     **/
    @TableField("config_key")
    private String configKey;

    /**
     * 可选的选项
     **/
    @TableField("config_options")
    private String configOptions;

    /**
     * 配置值
     **/
    @TableField("config_value")
    private String configValue;

    /**
     * 是否允许修改
     **/
    @TableField("is_allow_change")
    private Boolean isAllowChange;

    /**
     * 备注
     **/
    @TableField("remark")
    private String remark;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigOptions() {
        return configOptions;
    }

    public void setConfigOptions(String configOptions) {
        this.configOptions = configOptions;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public Boolean getIsAllowChange() {
        return isAllowChange;
    }

    public void setIsAllowChange(Boolean allowChange) {
        isAllowChange = allowChange;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Serializable pkVal() {
        return this.configId;
    }

}
