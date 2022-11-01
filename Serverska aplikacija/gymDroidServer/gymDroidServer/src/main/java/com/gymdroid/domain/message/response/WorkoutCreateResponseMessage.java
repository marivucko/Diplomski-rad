package com.gymdroid.domain.message.response;

import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

import java.util.ArrayList;

public class WorkoutCreateResponseMessage extends ResponseMessage {

    private Workout workout;
    private ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList;

    public WorkoutCreateResponseMessage(StatusEnum status, User user, Workout workout, ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList) {
        super(status, user);
        this.workout = workout;
        this.relationWorkoutMuscleArrayList = relationWorkoutMuscleArrayList;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public ArrayList<RelationWorkoutMuscle> getRelationWorkoutMuscleArrayList() {
        return relationWorkoutMuscleArrayList;
    }

    public void setRelationWorkoutMuscleArrayList(ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList) {
        this.relationWorkoutMuscleArrayList = relationWorkoutMuscleArrayList;
    }
}
