package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterWorkoutResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<Workout> workoutArrayList;

    public EnterWorkoutResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<Workout> workoutArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.workoutArrayList = workoutArrayList;
    }

    public ArrayList<Workout> getWorkoutArrayList() {
        return workoutArrayList;
    }

    public void setWorkoutArrayList(ArrayList<Workout> workoutArrayList) {
        this.workoutArrayList = workoutArrayList;
    }

}
