package com.bus.tian.tianbus.model.bean;

/**
 * Created by hsg on 2016/11/19.
 */

public class ImageTextBean {
    private int ImageResId;
    private String text;

    public int getImageResId() {
        return ImageResId;
    }

    public void setImageResId(int imageResId) {
        ImageResId = imageResId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "ImageTextBean{" +
                "ImageResId=" + ImageResId +
                ", text='" + text + '\'' +
                '}';
    }
}
