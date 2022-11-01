package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

import java.util.Date;

public class UserWeight extends BasicBean {

    private int weightId;
    private double weight;
    private String weightNote;
    private Date weightDate;
    private int userId;

    public UserWeight() {

    }

    public int getWeightId() {
        return weightId;
    }

    public void setWeightId(int weightId) {
        this.weightId = weightId;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getWeightNote() {
        return weightNote;
    }

    public void setWeightNote(String weightNote) {
        this.weightNote = weightNote;
    }

    public Date getWeightDate() {
        return weightDate;
    }

    public void setWeightDate(Date weightDate) {
        this.weightDate = weightDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
