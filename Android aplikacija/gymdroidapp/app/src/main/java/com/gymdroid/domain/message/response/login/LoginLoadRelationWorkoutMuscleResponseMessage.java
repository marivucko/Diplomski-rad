package com.gymdroid.domain.message.response.login;

import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseLargeDataMessage;

import java.util.ArrayList;

public class LoginLoadRelationWorkoutMuscleResponseMessage extends ResponseLargeDataMessage {

    private ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList;

    public LoginLoadRelationWorkoutMuscleResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList) {
        super(status, user, totalArraySize, finishIndex);
        this.relationWorkoutMuscleArrayList = relationWorkoutMuscleArrayList;
    }

    public ArrayList<RelationWorkoutMuscle> getRelationWorkoutMuscleArrayList() {
        return relationWorkoutMuscleArrayList;
    }

    public void setRelationWorkoutMuscleArrayList(ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList) {
        this.relationWorkoutMuscleArrayList = relationWorkoutMuscleArrayList;
    }

}
