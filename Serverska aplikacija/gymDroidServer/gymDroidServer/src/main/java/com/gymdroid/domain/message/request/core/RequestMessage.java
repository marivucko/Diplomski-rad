package com.gymdroid.domain.message.request.core;

import com.gymdroid.domain.beans.User;

public class RequestMessage {

    private User user;

    public RequestMessage(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
