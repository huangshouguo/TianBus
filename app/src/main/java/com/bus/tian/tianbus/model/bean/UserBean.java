package com.bus.tian.tianbus.model.bean;

/**
 * Created by hsg on 10/26/16.
 */

public class UserBean extends BaseBean {
    private String phone;
    private String uid;
    private String token;
    private String captchaId;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "phone='" + phone + '\'' +
                ", uid='" + uid + '\'' +
                ", token='" + token + '\'' +
                ", captchaId='" + captchaId + '\'' +
                '}';
    }

    @Override
    String getKey() {
        return getPhone();
    }
}
