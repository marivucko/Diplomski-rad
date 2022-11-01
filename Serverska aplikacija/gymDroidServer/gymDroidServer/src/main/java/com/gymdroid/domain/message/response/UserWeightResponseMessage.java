package com.gymdroid.domain.message.response;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

public class UserWeightResponseMessage extends ResponseMessage {

    private UserWeight userWeight;

    public UserWeightResponseMessage(StatusEnum status, User user, UserWeight userWeight) {
        super(status, user);
        this.userWeight = userWeight;
    }

    public UserWeight getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(UserWeight userWeight) {
        this.userWeight = userWeight;
    }
}
