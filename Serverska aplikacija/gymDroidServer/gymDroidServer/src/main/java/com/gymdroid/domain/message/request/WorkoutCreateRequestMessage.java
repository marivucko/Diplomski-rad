package com.gymdroid.domain.message.request;

import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.request.core.RequestMessage;

import java.util.ArrayList;

public class WorkoutCreateRequestMessage extends RequestMessage {

    private Workout workout;
    private ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList;

    public WorkoutCreateRequestMessage(User user, Workout workout, ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList) {
        super(user);
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
