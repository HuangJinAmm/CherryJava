package com.cherryjava.basesys.system.config.dto;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.cherryjava.basesys.system.config.db.SysConfigEntity;
import com.cherryjava.common.enums.BasicEnumUtil;
import com.cherryjava.common.enums.common.YesOrNoEnum;

import java.util.Date;
import java.util.List;

/**
 * @author hjamm
 */
public class ConfigDTO {

    public ConfigDTO(SysConfigEntity entity) {
        if (entity != null) {
            configId = entity.getConfigId() + "";
            configName = entity.getConfigName();
            configKey = entity.getConfigKey();
            configValue = entity.getConfigValue();
            configOptions =
                    JSONUtil.isTypeJSONArray(entity.getConfigOptions()) ? JSONUtil.toList(entity.getConfigOptions(),
                            String.class) : ListUtil.empty();
            isAllowChange = Convert.toInt(entity.getIsAllowChange());
            isAllowChangeStr = BasicEnumUtil.getDescriptionByBool(YesOrNoEnum.class, entity.getIsAllowChange());
            remark = entity.getRemark();
            createTime = entity.getCreateTime();
        }
    }

    private String configId;
    private String configName;
    private String configKey;
    private String configValue;
    private List<String> configOptions;
    private Integer isAllowChange;
    private String isAllowChangeStr;
    private String remark;
    private Date createTime;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
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

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public List<String> getConfigOptions() {
        return configOptions;
    }

    public void setConfigOptions(List<String> configOptions) {
        this.configOptions = configOptions;
    }

    public Integer getIsAllowChange() {
        return isAllowChange;
    }

    public void setIsAllowChange(Integer isAllowChange) {
        this.isAllowChange = isAllowChange;
    }

    public String getIsAllowChangeStr() {
        return isAllowChangeStr;
    }

    public void setIsAllowChangeStr(String isAllowChangeStr) {
        this.isAllowChangeStr = isAllowChangeStr;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
