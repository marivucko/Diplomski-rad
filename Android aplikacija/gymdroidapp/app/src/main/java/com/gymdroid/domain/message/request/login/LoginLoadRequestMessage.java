package com.gymdroid.domain.message.request.login;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.request.core.RequestLargeDataMessage;

public class LoginLoadRequestMessage extends RequestLargeDataMessage {

    public LoginLoadRequestMessage(User user, int startIndex, int increment) {
        super(user, startIndex, increment);
    }
}