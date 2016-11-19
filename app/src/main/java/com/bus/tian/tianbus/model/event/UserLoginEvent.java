package com.bus.tian.tianbus.model.event;

/**
 * Created by hsg on 2016/10/29.
 */

public class UserLoginEvent {
    private EventType eventType;

    public UserLoginEvent(EventType eventType) {
        this.eventType = eventType;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public boolean shouldLogin(){
        return ((this.eventType != null) && (this.eventType == EventType.EVENT_TYPE_SHOULD_LOGIN));
    }

    public boolean loginSuccessEvent() {
        return ((this.eventType != null) && (this.eventType == EventType.EVENT_TYPE_LOGIN_SUCCESS));
    }

    public boolean loginCancelEvent() {
        return ((this.eventType != null) && (this.eventType == EventType.EVENT_TYPE_LOGIN_CANCEL));
    }

    public boolean tokenInvalidEvent(){
        return ((this.eventType != null) && (this.eventType == EventType.EVENT_TYPE_TOKEN_INVALID));
    }

    public boolean logoutSuccessEvent(){
        return (this.eventType != null && this.eventType == EventType.EVENT_TYPE_LOGOUT_SUCCESS);
    }
}
