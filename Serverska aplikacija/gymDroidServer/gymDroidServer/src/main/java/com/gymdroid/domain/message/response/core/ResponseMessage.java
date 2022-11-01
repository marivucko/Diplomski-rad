package com.gymdroid.domain.message.response.core;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;

public class ResponseMessage {

    private StatusEnum status;
    private User user;

    public ResponseMessage(StatusEnum status, User user) {
        this.status = status;
        this.user = user;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
