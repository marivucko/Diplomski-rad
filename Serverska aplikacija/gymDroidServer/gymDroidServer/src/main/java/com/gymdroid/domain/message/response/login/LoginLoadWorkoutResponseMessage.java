package com.gymdroid.domain.message.response.login;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseLargeDataMessage;

import java.util.ArrayList;

public class LoginLoadWorkoutResponseMessage extends ResponseLargeDataMessage {

    private ArrayList<Workout> workoutArrayList;

    public LoginLoadWorkoutResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<Workout> workoutArrayList) {
        super(status, user, totalArraySize, finishIndex);
        this.workoutArrayList = workoutArrayList;
    }

    public ArrayList<Workout> getWorkoutArrayList() {
        return workoutArrayList;
    }

    public void setWorkoutArrayList(ArrayList<Workout> workoutArrayList) {
        this.workoutArrayList = workoutArrayList;
    }

}
