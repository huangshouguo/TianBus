package com.bus.tian.tianbus.model.bean;

import com.bus.tian.tianbus.util.DateUtil;

import java.util.Date;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumSummaryBean extends BaseBean {
    // 主题id
    private String themeId;

    // 主题内容
    private String title;

    // 创建者
    private String creator;

    // 创建时间
    private Date createTime;

    // 已评论数
    private String commentCount;

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "ForumSummaryBean{" +
                "themeId='" + themeId + '\'' +
                ", title='" + title + '\'' +
                ", creator='" + creator + '\'' +
                ", createTime=" + createTime +
                ", commentCount='" + commentCount + '\'' +
                '}';
    }

    @Override
    String getKey() {
        return themeId;
    }


    ////////////////////////////
    // helper
    public String getCreateTimeImpl() {
        return DateUtil.formatDate(getCreateTime());
    }
}
