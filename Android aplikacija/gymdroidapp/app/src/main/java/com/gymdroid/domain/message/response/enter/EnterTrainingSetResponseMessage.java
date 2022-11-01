package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterTrainingSetResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<TrainingSet> trainingSetArrayList;

    public EnterTrainingSetResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<TrainingSet> trainingSetArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.trainingSetArrayList = trainingSetArrayList;
    }

    public ArrayList<TrainingSet> getTrainingSetArrayList() {
        return trainingSetArrayList;
    }

    public void setTrainingSetArrayList(ArrayList<TrainingSet> trainingSetArrayList) {
        this.trainingSetArrayList = trainingSetArrayList;
    }
}
