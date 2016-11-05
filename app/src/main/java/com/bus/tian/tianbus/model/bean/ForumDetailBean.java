package com.bus.tian.tianbus.model.bean;

import java.util.List;

/**
 * Created by hsg on 11/5/16.
 */

public class ForumDetailBean extends BaseBean {
    //主题
    private ForumSummaryBean theme;

    //评论列表
    private List<ForumCommentBean> comments;

    public ForumSummaryBean getTheme() {
        return theme;
    }

    public void setTheme(ForumSummaryBean theme) {
        this.theme = theme;
    }

    public List<ForumCommentBean> getComments() {
        return comments;
    }

    public void setComments(List<ForumCommentBean> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ForumDetailBean{" +
                "theme=" + theme +
                ", comments=" + comments +
                '}';
    }

    @Override
    String getKey() {
        return null;
    }
}
