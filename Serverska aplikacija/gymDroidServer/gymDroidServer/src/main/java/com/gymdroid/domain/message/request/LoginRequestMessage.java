package com.gymdroid.domain.message.request;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.request.core.RequestMessage;

public class LoginRequestMessage extends RequestMessage {

    public LoginRequestMessage(User user) {
        super(user);
    }

}
