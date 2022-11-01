package com.gymdroid.domain.message.response.enter;


import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterTrainingWorkoutResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<TrainingWorkout> trainingWorkoutArrayList;

    public EnterTrainingWorkoutResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<TrainingWorkout> trainingWorkoutArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.trainingWorkoutArrayList = trainingWorkoutArrayList;
    }

    public ArrayList<TrainingWorkout> getTrainingWorkoutArrayList() {
        return trainingWorkoutArrayList;
    }

    public void setTrainingWorkoutArrayList(ArrayList<TrainingWorkout> trainingWorkoutArrayList) {
        this.trainingWorkoutArrayList = trainingWorkoutArrayList;
    }

}
