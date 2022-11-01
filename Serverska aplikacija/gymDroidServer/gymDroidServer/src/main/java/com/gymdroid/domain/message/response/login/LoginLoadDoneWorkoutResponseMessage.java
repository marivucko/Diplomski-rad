package com.gymdroid.domain.message.response.login;

import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseLargeDataMessage;

import java.util.ArrayList;

public class LoginLoadDoneWorkoutResponseMessage extends ResponseLargeDataMessage {

    private ArrayList<DoneWorkout> doneWorkoutArrayList;

    public LoginLoadDoneWorkoutResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<DoneWorkout> doneWorkoutArrayList) {
        super(status, user, totalArraySize, finishIndex);
        this.doneWorkoutArrayList = doneWorkoutArrayList;
    }

    public ArrayList<DoneWorkout> getDoneWorkoutArrayList() {
        return doneWorkoutArrayList;
    }

    public void setDoneWorkoutArrayList(ArrayList<DoneWorkout> doneWorkoutArrayList) {
        this.doneWorkoutArrayList = doneWorkoutArrayList;
    }
}
