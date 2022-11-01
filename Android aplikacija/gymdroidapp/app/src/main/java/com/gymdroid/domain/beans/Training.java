package com.gymdroid.domain.beans;

import com.gymdroid.domain.beans.core.BasicBean;

public class Training extends BasicBean {

    private int trainingId;
    private String trainingName;
    private String trainingDescription;
    private int trainingIntensityLevel;
    private long durationOfPauseBetweenWorkouts;
    private int userCreatorId;

    public Training() {

    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingDescription() {
        return trainingDescription;
    }

    public void setTrainingDescription(String trainingDescription) {
        this.trainingDescription = trainingDescription;
    }

    public int getTrainingIntensityLevel() {
        return trainingIntensityLevel;
    }

    public void setTrainingIntensityLevel(int trainingIntensityLevel) {
        this.trainingIntensityLevel = trainingIntensityLevel;
    }

    public long getDurationOfPauseBetweenWorkouts() {
        return durationOfPauseBetweenWorkouts;
    }

    public void setDurationOfPauseBetweenWorkouts(long durationOfPauseBetweenWorkouts) {
        this.durationOfPauseBetweenWorkouts = durationOfPauseBetweenWorkouts;
    }

    public int getUserCreatorId() {
        return userCreatorId;
    }

    public void setUserCreatorId(int userCreatorId) {
        this.userCreatorId = userCreatorId;
    }
}
