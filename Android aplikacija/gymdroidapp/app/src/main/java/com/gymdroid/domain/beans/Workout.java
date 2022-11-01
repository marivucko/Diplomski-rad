package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

public class Workout extends BasicBean {

    public static final int WORKOUT_STATUS_NOT_WAIT_APPROVAL = 0;
    public static final int WORKOUT_STATUS_WAIT_APPROVAL = 1;
    public static final int WORKOUT_STATUS_NOT_APPROVED = 0;
    public static final int WORKOUT_STATUS_APPROVED = 1;
    public static final int MASS_TYPE_WITHOUT_WEIGHTS = 0;
    public static final int MASS_TYPE_WITH_WEIGHTS = 1;

    private int workoutId;
    private String workoutName;
    private String workoutDescription;
    private int workoutStatusIsApproved;
    private int workoutStatusWaitApproval;
    private int workoutNeedTime;
    private int workoutNeedWeight;

    private int userCreatorId;

    public Workout() {
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWorkoutDescription() {
        return workoutDescription;
    }

    public void setWorkoutDescription(String workoutDescription) {
        this.workoutDescription = workoutDescription;
    }

    public int getWorkoutStatusIsApproved() {
        return workoutStatusIsApproved;
    }

    public void setWorkoutStatusIsApproved(int workoutStatusIsApproved) {
        this.workoutStatusIsApproved = workoutStatusIsApproved;
    }

    public int getWorkoutStatusWaitApproval() {
        return workoutStatusWaitApproval;
    }

    public void setWorkoutStatusWaitApproval(int workoutStatusWaitApproval) {
        this.workoutStatusWaitApproval = workoutStatusWaitApproval;
    }

    public int getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(int userCreatorId) {
        this.userCreatorId = userCreatorId;
    }

    public int getWorkoutNeedWeight() {
        return workoutNeedWeight;
    }

    public void setWorkoutNeedWeight(int workoutNeedWeight) {
        this.workoutNeedWeight = workoutNeedWeight;
    }

    public int getWorkoutNeedTime() {
        return workoutNeedTime;
    }

    public void setWorkoutNeedTime(int workoutNeedTime) {
        this.workoutNeedTime = workoutNeedTime;
    }
}
