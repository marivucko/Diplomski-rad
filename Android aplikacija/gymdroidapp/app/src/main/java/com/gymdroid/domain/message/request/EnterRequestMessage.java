package com.gymdroid.domain.message.request;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.request.core.RequestMessage;

import java.util.Date;

public class EnterRequestMessage extends RequestMessage {

    private Date doneSetLastCreationDate;
    private Date doneTrainingLastCreationDate;
    private Date doneWorkoutLastCreationDate;
    private Date equipmentLastCreationDate;
    private Date equipmentTypeLastCreationDate;
    private Date muscleLastCreationDate;
    private Date muscleGroupLastCreationDate;
    private Date relationWorkoutMuscleLastCreationDate;
    private Date trainingLastCreationDate;
    private Date trainingSetLastCreationDate;
    private Date trainingWorkoutLastCreationDate;
    private Date userWeightLastCreationDate;
    private Date workoutLastCreationDate;

    private Date doneSetLastUpdateDate;
    private Date doneTrainingLastUpdateDate;
    private Date doneWorkoutLastUpdateDate;
    private Date equipmentLastUpdateDate;
    private Date equipmentTypeLastUpdateDate;
    private Date muscleLastUpdateDate;
    private Date muscleGroupLastUpdateDate;
    private Date relationWorkoutMuscleLastUpdateDate;
    private Date trainingLastUpdateDate;
    private Date trainingSetLastUpdateDate;
    private Date trainingWorkoutLastUpdateDate;
    private Date userWeightLastUpdateDate;
    private Date workoutLastUpdateDate;

    public EnterRequestMessage(User user, Date doneSetLastCreationDate, Date doneTrainingLastCreationDate, Date doneWorkoutLastCreationDate, Date equipmentLastCreationDate, Date equipmentTypeLastCreationDate, Date muscleLastCreationDate, Date muscleGroupLastCreationDate, Date relationWorkoutMuscleLastCreationDate, Date trainingLastCreationDate, Date trainingSetLastCreationDate, Date trainingWorkoutLastCreationDate, Date userWeightLastCreationDate, Date workoutLastCreationDate, Date doneSetLastUpdateDate, Date doneTrainingLastUpdateDate, Date doneWorkoutLastUpdateDate, Date equipmentLastUpdateDate, Date equipmentTypeLastUpdateDate, Date muscleLastUpdateDate, Date muscleGroupLastUpdateDate, Date relationWorkoutMuscleLastUpdateDate, Date trainingLastUpdateDate, Date trainingSetLastUpdateDate, Date trainingWorkoutLastUpdateDate, Date userWeightLastUpdateDate, Date workoutLastUpdateDate) {
        super(user);
        this.doneSetLastCreationDate = doneSetLastCreationDate;
        this.doneTrainingLastCreationDate = doneTrainingLastCreationDate;
        this.doneWorkoutLastCreationDate = doneWorkoutLastCreationDate;
        this.equipmentLastCreationDate = equipmentLastCreationDate;
        this.equipmentTypeLastCreationDate = equipmentTypeLastCreationDate;
        this.muscleLastCreationDate = muscleLastCreationDate;
        this.muscleGroupLastCreationDate = muscleGroupLastCreationDate;
        this.relationWorkoutMuscleLastCreationDate = relationWorkoutMuscleLastCreationDate;
        this.trainingLastCreationDate = trainingLastCreationDate;
        this.trainingSetLastCreationDate = trainingSetLastCreationDate;
        this.trainingWorkoutLastCreationDate = trainingWorkoutLastCreationDate;
        this.userWeightLastCreationDate = userWeightLastCreationDate;
        this.workoutLastCreationDate = workoutLastCreationDate;
        this.doneSetLastUpdateDate = doneSetLastUpdateDate;
        this.doneTrainingLastUpdateDate = doneTrainingLastUpdateDate;
        this.doneWorkoutLastUpdateDate = doneWorkoutLastUpdateDate;
        this.equipmentLastUpdateDate = equipmentLastUpdateDate;
        this.equipmentTypeLastUpdateDate = equipmentTypeLastUpdateDate;
        this.muscleLastUpdateDate = muscleLastUpdateDate;
        this.muscleGroupLastUpdateDate = muscleGroupLastUpdateDate;
        this.relationWorkoutMuscleLastUpdateDate = relationWorkoutMuscleLastUpdateDate;
        this.trainingLastUpdateDate = trainingLastUpdateDate;
        this.trainingSetLastUpdateDate = trainingSetLastUpdateDate;
        this.trainingWorkoutLastUpdateDate = trainingWorkoutLastUpdateDate;
        this.userWeightLastUpdateDate = userWeightLastUpdateDate;
        this.workoutLastUpdateDate = workoutLastUpdateDate;
    }

    public Date getDoneSetLastCreationDate() {
        return doneSetLastCreationDate;
    }

    public Date getDoneTrainingLastCreationDate() {
        return doneTrainingLastCreationDate;
    }

    public Date getDoneWorkoutLastCreationDate() {
        return doneWorkoutLastCreationDate;
    }

    public Date getEquipmentLastCreationDate() {
        return equipmentLastCreationDate;
    }

    public Date getEquipmentTypeLastCreationDate() {
        return equipmentTypeLastCreationDate;
    }

    public Date getMuscleLastCreationDate() {
        return muscleLastCreationDate;
    }

    public Date getMuscleGroupLastCreationDate() {
        return muscleGroupLastCreationDate;
    }

    public Date getRelationWorkoutMuscleLastCreationDate() {
        return relationWorkoutMuscleLastCreationDate;
    }

    public Date getTrainingLastCreationDate() {
        return trainingLastCreationDate;
    }

    public Date getTrainingSetLastCreationDate() {
        return trainingSetLastCreationDate;
    }

    public Date getTrainingWorkoutLastCreationDate() {
        return trainingWorkoutLastCreationDate;
    }

    public Date getUserWeightLastCreationDate() {
        return userWeightLastCreationDate;
    }

    public Date getWorkoutLastCreationDate() {
        return workoutLastCreationDate;
    }

    public Date getDoneSetLastUpdateDate() {
        return doneSetLastUpdateDate;
    }

    public Date getDoneTrainingLastUpdateDate() {
        return doneTrainingLastUpdateDate;
    }

    public Date getDoneWorkoutLastUpdateDate() {
        return doneWorkoutLastUpdateDate;
    }

    public Date getEquipmentLastUpdateDate() {
        return equipmentLastUpdateDate;
    }

    public Date getEquipmentTypeLastUpdateDate() {
        return equipmentTypeLastUpdateDate;
    }

    public Date getMuscleLastUpdateDate() {
        return muscleLastUpdateDate;
    }

    public Date getMuscleGroupLastUpdateDate() {
        return muscleGroupLastUpdateDate;
    }

    public Date getRelationWorkoutMuscleLastUpdateDate() {
        return relationWorkoutMuscleLastUpdateDate;
    }

    public Date getTrainingLastUpdateDate() {
        return trainingLastUpdateDate;
    }

    public Date getTrainingSetLastUpdateDate() {
        return trainingSetLastUpdateDate;
    }

    public Date getTrainingWorkoutLastUpdateDate() {
        return trainingWorkoutLastUpdateDate;
    }

    public Date getUserWeightLastUpdateDate() {
        return userWeightLastUpdateDate;
    }

    public Date getWorkoutLastUpdateDate() {
        return workoutLastUpdateDate;
    }
}