package com.cherryjava.basesys.system.notice.model;

import cn.hutool.core.bean.BeanUtil;
import com.cherryjava.basesys.system.notice.command.NoticeAddCommand;
import com.cherryjava.basesys.system.notice.command.NoticeUpdateCommand;
import com.cherryjava.basesys.system.notice.db.SysNoticeEntity;
import com.cherryjava.common.enums.BasicEnumUtil;
import com.cherryjava.common.enums.common.NoticeTypeEnum;
import com.cherryjava.common.enums.common.StatusEnum;

/**
 * @author hjamm
 */
public class NoticeModel extends SysNoticeEntity {

    public NoticeModel() {
    }

    public NoticeModel(SysNoticeEntity entity) {
        if (entity != null) {
            BeanUtil.copyProperties(entity, this);
        }
    }

    public void loadAddCommand(NoticeAddCommand command) {
        if (command != null) {
            BeanUtil.copyProperties(command, this, "noticeId");
        }
    }

    public void loadUpdateCommand(NoticeUpdateCommand command) {
        if (command != null) {
            loadAddCommand(command);
        }
    }

    public void checkFields() {
        BasicEnumUtil.fromValue(NoticeTypeEnum.class, getNoticeType());
        BasicEnumUtil.fromValue(StatusEnum.class, getStatus());
    }

}
