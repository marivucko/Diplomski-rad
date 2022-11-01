package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.MuscleGroup;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterMuscleGroupResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<MuscleGroup> muscleGroupArrayList;

    public EnterMuscleGroupResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<MuscleGroup> muscleGroupArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.muscleGroupArrayList = muscleGroupArrayList;
    }

    public ArrayList<MuscleGroup> getMuscleGroupArrayList() {
        return muscleGroupArrayList;
    }

    public void setMuscleGroupArrayList(ArrayList<MuscleGroup> muscleGroupArrayList) {
        this.muscleGroupArrayList = muscleGroupArrayList;
    }
}
