package com.cherryjava.basesys.system.notice.model;

import com.cherryjava.basesys.system.notice.db.SysNoticeEntity;
import com.cherryjava.basesys.system.notice.db.SysNoticeService;
import com.cherryjava.common.exception.ApiException;
import com.cherryjava.common.exception.error.ErrorCode;
import org.springframework.stereotype.Component;

/**
 * 公告模型工厂
 *
 * @author hjamm
 */
@Component
public class NoticeModelFactory {

    private final SysNoticeService noticeService;


    public NoticeModelFactory(SysNoticeService noticeService) {
        this.noticeService = noticeService;
    }

    public NoticeModel loadById(Long noticeId) {
        SysNoticeEntity byId = noticeService.getById(noticeId);

        if (byId == null) {
            throw new ApiException(ErrorCode.Business.COMMON_OBJECT_NOT_FOUND, noticeId, "通知公告");
        }

        return new NoticeModel(byId);
    }

    public NoticeModel create() {
        return new NoticeModel();
    }


}
