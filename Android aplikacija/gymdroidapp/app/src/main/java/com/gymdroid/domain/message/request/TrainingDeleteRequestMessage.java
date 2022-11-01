package com.gymdroid.domain.message.request;

import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.request.core.RequestMessage;

public class TrainingDeleteRequestMessage extends RequestMessage {

    private Training training;

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public TrainingDeleteRequestMessage(User user, Training training) {
        super(user);
        this.training = training;
    }

}
