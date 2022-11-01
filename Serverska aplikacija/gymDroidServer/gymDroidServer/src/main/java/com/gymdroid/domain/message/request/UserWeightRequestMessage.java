package com.gymdroid.domain.message.request;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.message.request.core.RequestMessage;

public class UserWeightRequestMessage extends RequestMessage {

    private UserWeight userWeight;

    public UserWeightRequestMessage(User user, UserWeight userWeight) {
        super(user);
        this.userWeight = userWeight;
    }

    public UserWeight getUserWeight() {
        return userWeight;
    }

    public void setUserWeight(UserWeight userWeight) {
        this.userWeight = userWeight;
    }
}
