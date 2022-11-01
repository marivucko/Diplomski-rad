package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

public class TrainingWorkout extends BasicBean {

    private int trainingWorkoutId;
    private int orderNumber;
    private long durationOfPauseBetweenSets;
    private int workoutId;
    private int trainingId;

    public TrainingWorkout() {
    }

    public int getTrainingWorkoutId() {
        return trainingWorkoutId;
    }

    public void setTrainingWorkoutId(int trainingWorkoutId) {
        this.trainingWorkoutId = trainingWorkoutId;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public long getDurationOfPauseBetweenSets() {
        return durationOfPauseBetweenSets;
    }

    public void setDurationOfPauseBetweenSets(long durationOfPauseBetweenSets) {
        this.durationOfPauseBetweenSets = durationOfPauseBetweenSets;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }
}
