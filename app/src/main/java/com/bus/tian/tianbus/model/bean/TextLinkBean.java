package com.bus.tian.tianbus.model.bean;

/**
 * Created by hsg on 2016/11/2.
 */

public class TextLinkBean extends BaseBean {
    private String text;
    private String link;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    String getKey() {
        return link;
    }
}
