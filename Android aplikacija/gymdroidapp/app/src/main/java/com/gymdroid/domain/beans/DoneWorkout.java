package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

import java.util.Date;

public class DoneWorkout extends BasicBean {

    private int doneWorkoutId;
    private int workoutId;
    private int userId;
    private Date doneWorkoutDate;

    public DoneWorkout() {
    }

    public int getDoneWorkoutId() {
        return doneWorkoutId;
    }

    public void setDoneWorkoutId(int doneWorkoutId) {
        this.doneWorkoutId = doneWorkoutId;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getDoneWorkoutDate() {
        return doneWorkoutDate;
    }

    public void setDoneWorkoutDate(Date doneWorkoutDate) {
        this.doneWorkoutDate = doneWorkoutDate;
    }

}
