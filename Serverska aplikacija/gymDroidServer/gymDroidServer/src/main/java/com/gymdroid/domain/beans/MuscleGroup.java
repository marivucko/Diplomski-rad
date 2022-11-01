package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

public class MuscleGroup extends BasicBean {

    private int muscleGroupId;
    private String muscleGroupName;

    public MuscleGroup() {
    }

    public int getMuscleGroupId() {
        return muscleGroupId;
    }

    public void setMuscleGroupId(int muscleGroupId) {
        this.muscleGroupId = muscleGroupId;
    }

    public String getMuscleGroupName() {
        return muscleGroupName;
    }

    public void setMuscleGroupName(String muscleGroupName) {
        this.muscleGroupName = muscleGroupName;
    }

}
