package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

public class DoneSet extends BasicBean {

    private int doneSetId;
    private int doneSetTimeInMilliseconds;
    private int doneSetNumberOfReps;
    private double doneSetWeight;
    private int doneWorkoutId;

    public DoneSet() {
    }

    public int getDoneSetId() {
        return doneSetId;
    }

    public void setDoneSetId(int doneSetId) {
        this.doneSetId = doneSetId;
    }

    public int getDoneSetNumberOfReps() {
        return doneSetNumberOfReps;
    }

    public void setDoneSetNumberOfReps(int doneSetNumberOfReps) {
        this.doneSetNumberOfReps = doneSetNumberOfReps;
    }

    public double getDoneSetWeight() {
        return doneSetWeight;
    }

    public void setDoneSetWeight(double doneSetWeight) {
        this.doneSetWeight = doneSetWeight;
    }

    public int getDoneWorkoutId() {
        return doneWorkoutId;
    }

    public void setDoneWorkoutId(int doneWorkoutId) {
        this.doneWorkoutId = doneWorkoutId;
    }

    public int getDoneSetTimeInMilliseconds() {
        return doneSetTimeInMilliseconds;
    }

    public void setDoneSetTimeInMilliseconds(int doneSetTimeInMilliseconds) {
        this.doneSetTimeInMilliseconds = doneSetTimeInMilliseconds;
    }
}
