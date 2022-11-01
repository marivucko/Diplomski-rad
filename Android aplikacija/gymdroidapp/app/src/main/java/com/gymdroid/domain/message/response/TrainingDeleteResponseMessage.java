package com.gymdroid.domain.message.response;

import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

public class TrainingDeleteResponseMessage extends ResponseMessage {

    private Training training;

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public TrainingDeleteResponseMessage(StatusEnum statusEnum, User user, Training training) {
        super(statusEnum, user);
        this.training = training;
    }
}
