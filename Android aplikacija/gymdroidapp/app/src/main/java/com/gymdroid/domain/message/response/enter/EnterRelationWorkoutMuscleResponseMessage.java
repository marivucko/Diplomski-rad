package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterRelationWorkoutMuscleResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList;

    public EnterRelationWorkoutMuscleResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.relationWorkoutMuscleArrayList = relationWorkoutMuscleArrayList;
    }

    public ArrayList<RelationWorkoutMuscle> getRelationWorkoutMuscleArrayList() {
        return relationWorkoutMuscleArrayList;
    }

    public void setRelationWorkoutMuscleArrayList(ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList) {
        this.relationWorkoutMuscleArrayList = relationWorkoutMuscleArrayList;
    }

}
