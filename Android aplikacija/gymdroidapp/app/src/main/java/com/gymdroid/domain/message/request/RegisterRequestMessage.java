package com.gymdroid.domain.message.request;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.request.core.RequestMessage;

public class RegisterRequestMessage extends RequestMessage {

    public RegisterRequestMessage(User user) {
        super(user);
    }

}
