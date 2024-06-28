package com.cherryjava.basesys.system.notice.command;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * @author hjamm
 */
public class NoticeUpdateCommand extends NoticeAddCommand {

    @NotNull
    @Positive
    protected Long noticeId;

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }
}
