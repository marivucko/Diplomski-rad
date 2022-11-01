package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

import java.util.Date;

public class DoneTraining extends BasicBean {

    private int doneTrainingId;
    private Date doneTrainingAt;
    private int trainingId;

    public DoneTraining() {
    }

    public int getDoneTrainingId() {
        return doneTrainingId;
    }

    public void setDoneTrainingId(int doneTrainingId) {
        this.doneTrainingId = doneTrainingId;
    }

    public Date getDoneTrainingAt() {
        return doneTrainingAt;
    }

    public void setDoneTrainingAt(Date doneTrainingAt) {
        this.doneTrainingAt = doneTrainingAt;
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }
}
