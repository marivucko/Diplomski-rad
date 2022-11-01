package com.gymdroid.domain.message.response;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

public class RegisterResponseMessage extends ResponseMessage {

    public RegisterResponseMessage(StatusEnum status, User user) {
        super(status, user);
    }

}
