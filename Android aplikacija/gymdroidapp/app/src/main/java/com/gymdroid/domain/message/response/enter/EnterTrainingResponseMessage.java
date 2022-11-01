package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterTrainingResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<Training> trainingArrayList;

    public EnterTrainingResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<Training> trainingArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.trainingArrayList = trainingArrayList;
    }

    public ArrayList<Training> getTrainingArrayList() {
        return trainingArrayList;
    }

    public void setTrainingArrayList(ArrayList<Training> trainingArrayList) {
        this.trainingArrayList = trainingArrayList;
    }
}