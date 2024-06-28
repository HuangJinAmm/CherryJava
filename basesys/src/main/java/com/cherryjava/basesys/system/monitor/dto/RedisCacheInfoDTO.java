package com.cherryjava.basesys.system.monitor.dto;

import java.util.List;
import java.util.Properties;


/**
 * @author hjamm
 */
public class RedisCacheInfoDTO {

    private Properties info;
    private Long dbSize;
    private List<CommandStatusDTO> commandStats;

    public static class CommandStatusDTO {
        private String name;
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public Properties getInfo() {
        return info;
    }

    public void setInfo(Properties info) {
        this.info = info;
    }

    public Long getDbSize() {
        return dbSize;
    }

    public void setDbSize(Long dbSize) {
        this.dbSize = dbSize;
    }

    public List<CommandStatusDTO> getCommandStats() {
        return commandStats;
    }

    public void setCommandStats(List<CommandStatusDTO> commandStats) {
        this.commandStats = commandStats;
    }
}
