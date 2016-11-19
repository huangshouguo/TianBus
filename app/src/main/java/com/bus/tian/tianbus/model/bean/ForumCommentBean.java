package com.bus.tian.tianbus.model.bean;

import android.text.TextUtils;

import com.bus.tian.tianbus.util.DateUtil;

import java.util.Date;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumCommentBean extends BaseBean {
    //当前是几楼
    private String id;

    //回复谁的楼号
    private String bizId;

    //回复者
    private String customer;

    //回复时间
    private long createTime;

    //回复内容
    private String comment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ForumCommentBean{" +
                "id='" + id + '\'' +
                ", bizId='" + bizId + '\'' +
                ", customer='" + customer + '\'' +
                ", createTime=" + createTime +
                ", comment='" + comment + '\'' +
                '}';
    }

    @Override
    String getKey() {
        return null;
    }

    public String getCreateTimeImpl(){
        return DateUtil.formatDateTime( new Date(getCreateTime()));
    }

    public String getIdImpl(){
        String result = TextUtils.isEmpty(getId()) ? "0": getId();
        return String.format("%s楼", result);
    }

    public String getBizIdImpl(){
        if (TextUtils.isEmpty(getBizId()) || "0".equals(getBizId())){
            return "楼主";
        } else {
            return String.format("%s楼", getBizId());
        }
    }
}
