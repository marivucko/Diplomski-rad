package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.Muscle;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterMuscleResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<Muscle> muscleArrayList;

    public EnterMuscleResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<Muscle> muscleArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.muscleArrayList = muscleArrayList;
    }

    public ArrayList<Muscle> getMuscleArrayList() {
        return muscleArrayList;
    }

    public void setMuscleArrayList(ArrayList<Muscle> muscleArrayList) {
        this.muscleArrayList = muscleArrayList;
    }

}
