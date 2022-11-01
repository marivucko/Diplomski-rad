package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

public class RelationWorkoutMuscle extends BasicBean {

    private int relationId;
    private int workoutId;
    private int muscleId;
    private int muscleTargetPriority;

    public RelationWorkoutMuscle() {
    }

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getMuscleId() {
        return muscleId;
    }

    public void setMuscleId(int muscleId) {
        this.muscleId = muscleId;
    }

    public int getMuscleTargetPriority() {
        return muscleTargetPriority;
    }

    public void setMuscleTargetPriority(int muscleTargetPriority) {
        this.muscleTargetPriority = muscleTargetPriority;
    }

}
