package com.bus.tian.tianbus.model.bean;

import com.bus.tian.tianbus.util.DateUtil;

import java.util.Date;

/**
 * Created by hsg on 2016/11/7.
 */

public class ImageVideoBean extends BaseBean {

    private String id;
    private String comment;
    private long createTime;
    private String filePath;
    private String location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    String getKey() {
        return getId();
    }

    @Override
    public String toString() {
        return "ImageVideoBean{" +
                "id='" + id + '\'' +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", filePath='" + filePath + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    ////////////////////////////
    // helper
    public String getCreateTimeImpl() {
        return DateUtil.formatDate(new Date(getCreateTime()));
    }
}
