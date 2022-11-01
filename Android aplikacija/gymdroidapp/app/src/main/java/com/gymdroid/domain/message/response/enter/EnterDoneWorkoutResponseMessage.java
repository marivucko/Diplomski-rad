package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterDoneWorkoutResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<DoneWorkout> doneSetArrayList;

    public EnterDoneWorkoutResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<DoneWorkout> doneSetArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.doneSetArrayList = doneSetArrayList;
    }

    public ArrayList<DoneWorkout> getDoneSetArrayList() {
        return doneSetArrayList;
    }

    public void setDoneSetArrayList(ArrayList<DoneWorkout> doneSetArrayList) {
        this.doneSetArrayList = doneSetArrayList;
    }
}
