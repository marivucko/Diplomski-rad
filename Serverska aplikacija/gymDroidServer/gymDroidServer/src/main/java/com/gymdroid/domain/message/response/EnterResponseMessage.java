package com.gymdroid.domain.message.response;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

public class EnterResponseMessage extends ResponseMessage {

    private boolean doneSetNeedInsert;
    private boolean doneTrainingNeedInsert;
    private boolean doneWorkoutNeedInsert;
    private boolean equipmentNeedInsert;
    private boolean equipmentTypeNeedInsert;
    private boolean muscleNeedInsert;
    private boolean muscleGroupNeedInsert;
    private boolean relationWorkoutMuscleNeedInsert;
    private boolean trainingNeedInsert;
    private boolean trainingSetNeedInsert;
    private boolean trainingWorkoutNeedInsert;
    private boolean userWeightNeedInsert;
    private boolean workoutNeedInsert;

    private boolean doneSetNeedUpdate;
    private boolean doneTrainingNeedUpdate;
    private boolean doneWorkoutNeedUpdate;
    private boolean equipmentNeedUpdate;
    private boolean equipmentTypeNeedUpdate;
    private boolean muscleNeedUpdate;
    private boolean muscleGroupNeedUpdate;
    private boolean relationWorkoutMuscleNeedUpdate;
    private boolean trainingNeedUpdate;
    private boolean trainingSetNeedUpdate;
    private boolean trainingWorkoutNeedUpdate;
    private boolean userWeightNeedUpdate;
    private boolean workoutNeedUpdate;

    private boolean doneSetNeedDelete;
    private boolean doneTrainingNeedDelete;
    private boolean doneWorkoutNeedDelete;
    private boolean equipmentNeedDelete;
    private boolean equipmentTypeNeedDelete;
    private boolean muscleNeedDelete;
    private boolean muscleGroupNeedDelete;
    private boolean relationWorkoutMuscleNeedDelete;
    private boolean trainingNeedDelete;
    private boolean trainingSetNeedDelete;
    private boolean trainingWorkoutNeedDelete;
    private boolean userWeightNeedDelete;
    private boolean workoutNeedDelete;

    public EnterResponseMessage(StatusEnum status, User user, boolean doneSetNeedInsert, boolean doneTrainingNeedInsert, boolean doneWorkoutNeedInsert, boolean equipmentNeedInsert, boolean equipmentTypeNeedInsert, boolean muscleNeedInsert, boolean muscleGroupNeedInsert, boolean relationWorkoutMuscleNeedInsert, boolean trainingNeedInsert, boolean trainingSetNeedInsert, boolean trainingWorkoutNeedInsert, boolean userWeightNeedInsert, boolean workoutNeedInsert, boolean doneSetNeedUpdate, boolean doneTrainingNeedUpdate, boolean doneWorkoutNeedUpdate, boolean equipmentNeedUpdate, boolean equipmentTypeNeedUpdate, boolean muscleNeedUpdate, boolean muscleGroupNeedUpdate, boolean relationWorkoutMuscleNeedUpdate, boolean trainingNeedUpdate, boolean trainingSetNeedUpdate, boolean trainingWorkoutNeedUpdate, boolean userWeightNeedUpdate, boolean workoutNeedUpdate, boolean doneSetNeedDelete, boolean doneTrainingNeedDelete, boolean doneWorkoutNeedDelete, boolean equipmentNeedDelete, boolean equipmentTypeNeedDelete, boolean muscleNeedDelete, boolean muscleGroupNeedDelete, boolean relationWorkoutMuscleNeedDelete, boolean trainingNeedDelete, boolean trainingSetNeedDelete, boolean trainingWorkoutNeedDelete, boolean userWeightNeedDelete, boolean workoutNeedDelete) {
        super(status, user);
        this.doneSetNeedInsert = doneSetNeedInsert;
        this.doneTrainingNeedInsert = doneTrainingNeedInsert;
        this.doneWorkoutNeedInsert = doneWorkoutNeedInsert;
        this.equipmentNeedInsert = equipmentNeedInsert;
        this.equipmentTypeNeedInsert = equipmentTypeNeedInsert;
        this.muscleNeedInsert = muscleNeedInsert;
        this.muscleGroupNeedInsert = muscleGroupNeedInsert;
        this.relationWorkoutMuscleNeedInsert = relationWorkoutMuscleNeedInsert;
        this.trainingNeedInsert = trainingNeedInsert;
        this.trainingSetNeedInsert = trainingSetNeedInsert;
        this.trainingWorkoutNeedInsert = trainingWorkoutNeedInsert;
        this.userWeightNeedInsert = userWeightNeedInsert;
        this.workoutNeedInsert = workoutNeedInsert;
        this.doneSetNeedUpdate = doneSetNeedUpdate;
        this.doneTrainingNeedUpdate = doneTrainingNeedUpdate;
        this.doneWorkoutNeedUpdate = doneWorkoutNeedUpdate;
        this.equipmentNeedUpdate = equipmentNeedUpdate;
        this.equipmentTypeNeedUpdate = equipmentTypeNeedUpdate;
        this.muscleNeedUpdate = muscleNeedUpdate;
        this.muscleGroupNeedUpdate = muscleGroupNeedUpdate;
        this.relationWorkoutMuscleNeedUpdate = relationWorkoutMuscleNeedUpdate;
        this.trainingNeedUpdate = trainingNeedUpdate;
        this.trainingSetNeedUpdate = trainingSetNeedUpdate;
        this.trainingWorkoutNeedUpdate = trainingWorkoutNeedUpdate;
        this.userWeightNeedUpdate = userWeightNeedUpdate;
        this.workoutNeedUpdate = workoutNeedUpdate;
        this.doneSetNeedDelete = doneSetNeedDelete;
        this.doneTrainingNeedDelete = doneTrainingNeedDelete;
        this.doneWorkoutNeedDelete = doneWorkoutNeedDelete;
        this.equipmentNeedDelete = equipmentNeedDelete;
        this.equipmentTypeNeedDelete = equipmentTypeNeedDelete;
        this.muscleNeedDelete = muscleNeedDelete;
        this.muscleGroupNeedDelete = muscleGroupNeedDelete;
        this.relationWorkoutMuscleNeedDelete = relationWorkoutMuscleNeedDelete;
        this.trainingNeedDelete = trainingNeedDelete;
        this.trainingSetNeedDelete = trainingSetNeedDelete;
        this.trainingWorkoutNeedDelete = trainingWorkoutNeedDelete;
        this.userWeightNeedDelete = userWeightNeedDelete;
        this.workoutNeedDelete = workoutNeedDelete;
    }

    public EnterResponseMessage(StatusEnum status, User user) {
        super(status, user);
        this.doneSetNeedInsert = false;
        this.doneTrainingNeedInsert = false;
        this.doneWorkoutNeedInsert = false;
        this.equipmentNeedInsert = false;
        this.equipmentTypeNeedInsert = false;
        this.muscleNeedInsert = false;
        this.muscleGroupNeedInsert = false;
        this.relationWorkoutMuscleNeedInsert = false;
        this.trainingNeedInsert = false;
        this.trainingSetNeedInsert = false;
        this.trainingWorkoutNeedInsert = false;
        this.userWeightNeedInsert = false;
        this.workoutNeedInsert = false;

        this.doneSetNeedUpdate = false;
        this.doneTrainingNeedUpdate = false;
        this.doneWorkoutNeedUpdate = false;
        this.equipmentNeedUpdate = false;
        this.equipmentTypeNeedUpdate = false;
        this.muscleNeedUpdate = false;
        this.muscleGroupNeedUpdate = false;
        this.relationWorkoutMuscleNeedUpdate = false;
        this.trainingNeedUpdate = false;
        this.trainingSetNeedUpdate = false;
        this.trainingWorkoutNeedUpdate = false;
        this.userWeightNeedUpdate = false;
        this.workoutNeedUpdate = false;

        this.doneSetNeedDelete = false;
        this.doneTrainingNeedDelete = false;
        this.doneWorkoutNeedDelete = false;
        this.equipmentNeedDelete = false;
        this.equipmentTypeNeedDelete = false;
        this.muscleNeedDelete = false;
        this.muscleGroupNeedDelete = false;
        this.relationWorkoutMuscleNeedDelete = false;
        this.trainingNeedDelete = false;
        this.trainingSetNeedDelete = false;
        this.trainingWorkoutNeedDelete = false;
        this.userWeightNeedDelete = false;
        this.workoutNeedDelete = false;
    }

    public boolean isDoneSetNeedInsert() {
        return doneSetNeedInsert;
    }

    public void setDoneSetNeedInsert(boolean doneSetNeedInsert) {
        this.doneSetNeedInsert = doneSetNeedInsert;
    }

    public boolean isDoneWorkoutNeedInsert() {
        return doneWorkoutNeedInsert;
    }

    public void setDoneWorkoutNeedInsert(boolean doneWorkoutNeedInsert) {
        this.doneWorkoutNeedInsert = doneWorkoutNeedInsert;
    }

    public boolean isDoneTrainingNeedInsert() {
        return doneTrainingNeedInsert;
    }

    public void setDoneTrainingNeedInsert(boolean doneTrainingNeedInsert) {
        this.doneTrainingNeedInsert = doneTrainingNeedInsert;
    }

    public boolean isEquipmentNeedInsert() {
        return equipmentNeedInsert;
    }

    public void setEquipmentNeedInsert(boolean equipmentNeedInsert) {
        this.equipmentNeedInsert = equipmentNeedInsert;
    }

    public boolean isEquipmentTypeNeedInsert() {
        return equipmentTypeNeedInsert;
    }

    public void setEquipmentTypeNeedInsert(boolean equipmentTypeNeedInsert) {
        this.equipmentTypeNeedInsert = equipmentTypeNeedInsert;
    }

    public boolean isMuscleNeedInsert() {
        return muscleNeedInsert;
    }

    public void setMuscleNeedInsert(boolean muscleNeedInsert) {
        this.muscleNeedInsert = muscleNeedInsert;
    }

    public boolean isMuscleGroupNeedInsert() {
        return muscleGroupNeedInsert;
    }

    public void setMuscleGroupNeedInsert(boolean muscleGroupNeedInsert) {
        this.muscleGroupNeedInsert = muscleGroupNeedInsert;
    }

    public boolean isRelationWorkoutMuscleNeedInsert() {
        return relationWorkoutMuscleNeedInsert;
    }

    public void setRelationWorkoutMuscleNeedInsert(boolean relationWorkoutMuscleNeedInsert) {
        this.relationWorkoutMuscleNeedInsert = relationWorkoutMuscleNeedInsert;
    }

    public boolean isTrainingNeedInsert() {
        return trainingNeedInsert;
    }

    public void setTrainingNeedInsert(boolean trainingNeedInsert) {
        this.trainingNeedInsert = trainingNeedInsert;
    }

    public boolean isTrainingSetNeedInsert() {
        return trainingSetNeedInsert;
    }

    public void setTrainingSetNeedInsert(boolean trainingSetNeedInsert) {
        this.trainingSetNeedInsert = trainingSetNeedInsert;
    }

    public boolean isTrainingWorkoutNeedInsert() {
        return trainingWorkoutNeedInsert;
    }

    public void setTrainingWorkoutNeedInsert(boolean trainingWorkoutNeedInsert) {
        this.trainingWorkoutNeedInsert = trainingWorkoutNeedInsert;
    }

    public boolean isUserWeightNeedInsert() {
        return userWeightNeedInsert;
    }

    public void setUserWeightNeedInsert(boolean userWeightNeedInsert) {
        this.userWeightNeedInsert = userWeightNeedInsert;
    }

    public boolean isWorkoutNeedInsert() {
        return workoutNeedInsert;
    }

    public void setWorkoutNeedInsert(boolean workoutNeedInsert) {
        this.workoutNeedInsert = workoutNeedInsert;
    }

    public boolean isDoneSetNeedUpdate() {
        return doneSetNeedUpdate;
    }

    public void setDoneSetNeedUpdate(boolean doneSetNeedUpdate) {
        this.doneSetNeedUpdate = doneSetNeedUpdate;
    }

    public boolean isDoneWorkoutNeedUpdate() {
        return doneWorkoutNeedUpdate;
    }

    public void setDoneWorkoutNeedUpdate(boolean doneWorkoutNeedUpdate) {
        this.doneWorkoutNeedUpdate = doneWorkoutNeedUpdate;
    }

    public boolean isDoneTrainingNeedUpdate() {
        return doneTrainingNeedUpdate;
    }

    public void setDoneTrainingNeedUpdate(boolean doneTrainingNeedUpdate) {
        this.doneTrainingNeedUpdate = doneTrainingNeedUpdate;
    }

    public boolean isEquipmentNeedUpdate() {
        return equipmentNeedUpdate;
    }

    public void setEquipmentNeedUpdate(boolean equipmentNeedUpdate) {
        this.equipmentNeedUpdate = equipmentNeedUpdate;
    }

    public boolean isEquipmentTypeNeedUpdate() {
        return equipmentTypeNeedUpdate;
    }

    public void setEquipmentTypeNeedUpdate(boolean equipmentTypeNeedUpdate) {
        this.equipmentTypeNeedUpdate = equipmentTypeNeedUpdate;
    }

    public boolean isMuscleNeedUpdate() {
        return muscleNeedUpdate;
    }

    public void setMuscleNeedUpdate(boolean muscleNeedUpdate) {
        this.muscleNeedUpdate = muscleNeedUpdate;
    }

    public boolean isMuscleGroupNeedUpdate() {
        return muscleGroupNeedUpdate;
    }

    public void setMuscleGroupNeedUpdate(boolean muscleGroupNeedUpdate) {
        this.muscleGroupNeedUpdate = muscleGroupNeedUpdate;
    }

    public boolean isRelationWorkoutMuscleNeedUpdate() {
        return relationWorkoutMuscleNeedUpdate;
    }

    public void setRelationWorkoutMuscleNeedUpdate(boolean relationWorkoutMuscleNeedUpdate) {
        this.relationWorkoutMuscleNeedUpdate = relationWorkoutMuscleNeedUpdate;
    }

    public boolean isTrainingNeedUpdate() {
        return trainingNeedUpdate;
    }

    public void setTrainingNeedUpdate(boolean trainingNeedUpdate) {
        this.trainingNeedUpdate = trainingNeedUpdate;
    }

    public boolean isTrainingSetNeedUpdate() {
        return trainingSetNeedUpdate;
    }

    public void setTrainingSetNeedUpdate(boolean trainingSetNeedUpdate) {
        this.trainingSetNeedUpdate = trainingSetNeedUpdate;
    }

    public boolean isTrainingWorkoutNeedUpdate() {
        return trainingWorkoutNeedUpdate;
    }

    public void setTrainingWorkoutNeedUpdate(boolean trainingWorkoutNeedUpdate) {
        this.trainingWorkoutNeedUpdate = trainingWorkoutNeedUpdate;
    }

    public boolean isUserWeightNeedUpdate() {
        return userWeightNeedUpdate;
    }

    public void setUserWeightNeedUpdate(boolean userWeightNeedUpdate) {
        this.userWeightNeedUpdate = userWeightNeedUpdate;
    }

    public boolean isWorkoutNeedUpdate() {
        return workoutNeedUpdate;
    }

    public void setWorkoutNeedUpdate(boolean workoutNeedUpdate) {
        this.workoutNeedUpdate = workoutNeedUpdate;
    }

    public boolean isDoneSetNeedDelete() {
        return doneSetNeedDelete;
    }

    public void setDoneSetNeedDelete(boolean doneSetNeedDelete) {
        this.doneSetNeedDelete = doneSetNeedDelete;
    }

    public boolean isDoneWorkoutNeedDelete() {
        return doneWorkoutNeedDelete;
    }

    public void setDoneWorkoutNeedDelete(boolean doneWorkoutNeedDelete) {
        this.doneWorkoutNeedDelete = doneWorkoutNeedDelete;
    }

    public boolean isDoneTrainingNeedDelete() {
        return doneTrainingNeedDelete;
    }

    public void setDoneTrainingNeedDelete(boolean doneTrainingNeedDelete) {
        this.doneTrainingNeedDelete = doneTrainingNeedDelete;
    }

    public boolean isEquipmentNeedDelete() {
        return equipmentNeedDelete;
    }

    public void setEquipmentNeedDelete(boolean equipmentNeedDelete) {
        this.equipmentNeedDelete = equipmentNeedDelete;
    }

    public boolean isEquipmentTypeNeedDelete() {
        return equipmentTypeNeedDelete;
    }

    public void setEquipmentTypeNeedDelete(boolean equipmentTypeNeedDelete) {
        this.equipmentTypeNeedDelete = equipmentTypeNeedDelete;
    }

    public boolean isMuscleNeedDelete() {
        return muscleNeedDelete;
    }

    public void setMuscleNeedDelete(boolean muscleNeedDelete) {
        this.muscleNeedDelete = muscleNeedDelete;
    }

    public boolean isMuscleGroupNeedDelete() {
        return muscleGroupNeedDelete;
    }

    public void setMuscleGroupNeedDelete(boolean muscleGroupNeedDelete) {
        this.muscleGroupNeedDelete = muscleGroupNeedDelete;
    }

    public boolean isRelationWorkoutMuscleNeedDelete() {
        return relationWorkoutMuscleNeedDelete;
    }

    public void setRelationWorkoutMuscleNeedDelete(boolean relationWorkoutMuscleNeedDelete) {
        this.relationWorkoutMuscleNeedDelete = relationWorkoutMuscleNeedDelete;
    }

    public boolean isTrainingNeedDelete() {
        return trainingNeedDelete;
    }

    public void setTrainingNeedDelete(boolean trainingNeedDelete) {
        this.trainingNeedDelete = trainingNeedDelete;
    }

    public boolean isTrainingSetNeedDelete() {
        return trainingSetNeedDelete;
    }

    public void setTrainingSetNeedDelete(boolean trainingSetNeedDelete) {
        this.trainingSetNeedDelete = trainingSetNeedDelete;
    }

    public boolean isTrainingWorkoutNeedDelete() {
        return trainingWorkoutNeedDelete;
    }

    public void setTrainingWorkoutNeedDelete(boolean trainingWorkoutNeedDelete) {
        this.trainingWorkoutNeedDelete = trainingWorkoutNeedDelete;
    }

    public boolean isUserWeightNeedDelete() {
        return userWeightNeedDelete;
    }

    public void setUserWeightNeedDelete(boolean userWeightNeedDelete) {
        this.userWeightNeedDelete = userWeightNeedDelete;
    }

    public boolean isWorkoutNeedDelete() {
        return workoutNeedDelete;
    }

    public void setWorkoutNeedDelete(boolean workoutNeedDelete) {
        this.workoutNeedDelete = workoutNeedDelete;
    }


}
