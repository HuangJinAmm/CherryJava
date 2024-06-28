package com.cherryjava.basesys.system.config.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cherryjava.basesys.system.config.db.SysConfigEntity;
import com.cherryjava.common.core.page.AbstractPageQuery;

/**
 * @author hjamm
 */
public class ConfigQuery extends AbstractPageQuery<SysConfigEntity> {

    private String configName;

    private String configKey;

    private Boolean isAllowChange;

    public ConfigQuery() {
    }

    @Override
    public QueryWrapper<SysConfigEntity> addQueryCondition() {
        QueryWrapper<SysConfigEntity> queryWrapper = new QueryWrapper<SysConfigEntity>()
                .like(StrUtil.isNotEmpty(configName), "config_name", configName)
                .eq(StrUtil.isNotEmpty(configKey), "config_key", configKey)
                .eq(isAllowChange != null, "is_allow_change", isAllowChange);

        this.timeRangeColumn = "create_time";

        return queryWrapper;
    }
}
