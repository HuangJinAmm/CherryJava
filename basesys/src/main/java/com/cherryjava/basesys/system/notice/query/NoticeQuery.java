package com.cherryjava.basesys.system.notice.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cherryjava.basesys.system.notice.db.SysNoticeEntity;
import com.cherryjava.common.core.page.AbstractPageQuery;

/**
 * @author hjamm
 */
public class NoticeQuery extends AbstractPageQuery<SysNoticeEntity> {

    private String noticeType;

    private String noticeTitle;

    private String creatorName;

    public NoticeQuery() {
    }

    @Override
    public QueryWrapper<SysNoticeEntity> addQueryCondition() {
        QueryWrapper<SysNoticeEntity> queryWrapper = new QueryWrapper<SysNoticeEntity>()
                .like(StrUtil.isNotEmpty(noticeTitle), "notice_title", noticeTitle)
                .eq(StrUtil.isNotEmpty(noticeType), "notice_type", noticeType)
                .eq("n.deleted", 0)
                .like(StrUtil.isNotEmpty(creatorName), "u.username", creatorName);
        return queryWrapper;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }
}
