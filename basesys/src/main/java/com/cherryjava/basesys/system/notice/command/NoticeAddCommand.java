package com.cherryjava.basesys.system.notice.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author hjamm
 */
public class NoticeAddCommand {

    @NotBlank(message = "公告标题不能为空")
    @Size(max = 50, message = "公告标题不能超过50个字符")
    protected String noticeTitle;

    protected String noticeType;

    /**
     * 想要支持富文本的话, 避免Xss过滤的话， 请加上@JsonDeserialize(using = StringDeserializer.class) 注解
     */
    @NotBlank
    @JsonDeserialize(using = StringDeserializer.class)
    protected String noticeContent;

    protected String status;

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
