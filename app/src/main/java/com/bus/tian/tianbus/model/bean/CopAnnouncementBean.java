package com.bus.tian.tianbus.model.bean;

import com.bus.tian.tianbus.util.DateUtil;

import java.util.Date;

/**
 * Created by hsg on 2016/10/30.
 */

public class CopAnnouncementBean extends BaseBean {
    private String id;
    private String title;
    private String content;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    /////////////////////////////////////////////////
    // helper
    public String getCreateTimeImpl() {
        return DateUtil.formatDateTime(getCreateTime());
    }

    @Override
    String getKey() {
        return getId();
    }

    @Override
    public String toString() {
        return "CopAnnouncementBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
