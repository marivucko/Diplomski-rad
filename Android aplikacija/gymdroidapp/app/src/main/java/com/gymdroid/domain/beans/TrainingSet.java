package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

public class TrainingSet extends BasicBean {

    private int trainingSetId;
    private long trainingSetTime;
    private int trainingSetNumberOfReps;

    public double getTrainingSetWeight() {
        return trainingSetWeight;
    }

    public void setTrainingSetWeight(double trainingSetWeight) {
        this.trainingSetWeight = trainingSetWeight;
    }

    private int trainingWorkoutId;
    private double trainingSetWeight;

    public TrainingSet() {
    }

    public int getTrainingSetId() {
        return trainingSetId;
    }

    public void setTrainingSetId(int trainingSetId) {
        this.trainingSetId = trainingSetId;
    }

    public long getTrainingSetTime() {
        return trainingSetTime;
    }

    public void setTrainingSetTime(long trainingSetTime) {
        this.trainingSetTime = trainingSetTime;
    }

    public int getTrainingSetNumberOfReps() {
        return trainingSetNumberOfReps;
    }

    public void setTrainingSetNumberOfReps(int trainingSetNumberOfReps) {
        this.trainingSetNumberOfReps = trainingSetNumberOfReps;
    }

    public int getTrainingWorkoutId() {
        return trainingWorkoutId;
    }

    public void setTrainingWorkoutId(int trainingWorkoutId) {
        this.trainingWorkoutId = trainingWorkoutId;
    }
}
