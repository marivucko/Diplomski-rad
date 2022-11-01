package com.gymdroid.domain.message.response.login;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.TrainingMessage;
import com.gymdroid.domain.message.response.core.ResponseLargeDataMessage;

import java.util.ArrayList;

public class LoginLoadTrainingResponseMessage extends ResponseLargeDataMessage {

    private ArrayList<TrainingMessage> trainingMessageArrayList;

    public LoginLoadTrainingResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<TrainingMessage> trainingMessageArrayList) {
        super(status, user, totalArraySize, finishIndex);
        this.trainingMessageArrayList = trainingMessageArrayList;
    }

    public ArrayList<TrainingMessage> getTrainingMessageArrayList() {
        return trainingMessageArrayList;
    }

    public void setTrainingMessageArrayList(ArrayList<TrainingMessage> trainingMessageArrayList) {
        this.trainingMessageArrayList = trainingMessageArrayList;
    }
}
