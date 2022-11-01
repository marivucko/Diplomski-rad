package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

public class Muscle extends BasicBean {

    private int muscleId;
    private String muscleName;
    private double muscleSize;
    private int muscleGroupId;

    public Muscle() {
    }

    public int getMuscleId() {
        return muscleId;
    }

    public void setMuscleId(int muscleId) {
        this.muscleId = muscleId;
    }

    public String getMuscleName() {
        return muscleName;
    }

    public void setMuscleName(String muscleName) {
        this.muscleName = muscleName;
    }

    public double getMuscleSize() {
        return muscleSize;
    }

    public void setMuscleSize(double muscleSize) {
        this.muscleSize = muscleSize;
    }

    public int getMuscleGroupId() {
        return muscleGroupId;
    }

    public void setMuscleGroupId(int muscleGroupId) {
        this.muscleGroupId = muscleGroupId;
    }

}
