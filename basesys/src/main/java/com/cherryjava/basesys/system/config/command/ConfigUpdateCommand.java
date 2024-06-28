package com.cherryjava.basesys.system.config.command;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author hjamm
 */
public class ConfigUpdateCommand {

    @NotNull
    @Positive
    private Long configId;

    @NotNull
    @NotEmpty
    private String configValue;

    public Long getConfigId() {
        return configId;
    }

    public void setConfigId(Long configId) {
        this.configId = configId;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}
