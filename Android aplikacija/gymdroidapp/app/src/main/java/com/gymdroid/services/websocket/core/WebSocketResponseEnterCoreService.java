package com.gymdroid.services.websocket.core;

import android.app.Activity;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Message;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.response.EnterResponseMessage;

public abstract class WebSocketResponseEnterCoreService extends WebSocketResponseService {

    protected EnterResponseMessage enterResponseMessage;

    protected boolean decideNext(Activity activity, User user, int startIndex) {
        if(enterResponseMessage.isDoneSetNeedInsert()) {
            enterResponseMessage.setDoneSetNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterDoneSet(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isDoneTrainingNeedInsert()) {
            enterResponseMessage.setDoneTrainingNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterDoneTraining(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isDoneWorkoutNeedInsert()) {
            enterResponseMessage.setDoneWorkoutNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterDoneWorkout(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isEquipmentNeedInsert()) {
            enterResponseMessage.setEquipmentNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterEquipment(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isEquipmentTypeNeedInsert()) {
            enterResponseMessage.setEquipmentTypeNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterEquipmentTypes(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isMuscleNeedInsert()) {
            enterResponseMessage.setMuscleNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterMuscle(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isMuscleGroupNeedInsert()) {
            enterResponseMessage.setMuscleGroupNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterMuscleGroup(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isRelationWorkoutMuscleNeedInsert()) {
            enterResponseMessage.setRelationWorkoutMuscleNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterRelationWorkoutMuscle(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isTrainingNeedInsert()) {
            enterResponseMessage.setTrainingNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterTraining(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isTrainingSetNeedInsert()) {
            enterResponseMessage.setTrainingSetNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterTrainingSet(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isTrainingWorkoutNeedInsert()) {
            enterResponseMessage.setTrainingWorkoutNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterTrainingWorkout(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isUserWeightNeedInsert()) {
            enterResponseMessage.setUserWeightNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterUserWeight(activity,user,startIndex, Operation.INSERT);
        }
        if(enterResponseMessage.isWorkoutNeedInsert()) {
            enterResponseMessage.setWorkoutNeedInsert(false);
            return INSTANCE.getWebSocketRequestEnterService().enterWorkouts(activity,user,startIndex, Operation.INSERT);
        }




        if(enterResponseMessage.isDoneSetNeedUpdate()) {
            enterResponseMessage.setDoneSetNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterDoneSet(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isDoneTrainingNeedUpdate()) {
            enterResponseMessage.setDoneTrainingNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterDoneTraining(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isDoneWorkoutNeedUpdate()) {
            enterResponseMessage.setDoneWorkoutNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterDoneWorkout(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isEquipmentNeedUpdate()) {
            enterResponseMessage.setEquipmentNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterEquipment(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isEquipmentTypeNeedUpdate()) {
            enterResponseMessage.setEquipmentTypeNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterEquipmentTypes(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isMuscleNeedUpdate()) {
            enterResponseMessage.setMuscleNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterMuscle(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isMuscleGroupNeedUpdate()) {
            enterResponseMessage.setMuscleGroupNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterMuscleGroup(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isRelationWorkoutMuscleNeedUpdate()) {
            enterResponseMessage.setRelationWorkoutMuscleNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterRelationWorkoutMuscle(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isTrainingNeedUpdate()) {
            enterResponseMessage.setTrainingNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterTraining(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isTrainingSetNeedUpdate()) {
            enterResponseMessage.setTrainingSetNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterTrainingSet(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isTrainingWorkoutNeedUpdate()) {
            enterResponseMessage.setTrainingWorkoutNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterTrainingWorkout(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isUserWeightNeedUpdate()) {
            enterResponseMessage.setUserWeightNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterUserWeight(activity,user,startIndex, Operation.UPDATE);
        }
        if(enterResponseMessage.isWorkoutNeedUpdate()) {
            enterResponseMessage.setWorkoutNeedUpdate(false);
            return INSTANCE.getWebSocketRequestEnterService().enterWorkouts(activity,user,startIndex, Operation.UPDATE);
        }

        INSTANCE.getStartActivityService().startMainActivity(activity);

        return true;
    }

    abstract public boolean enterDoneSet(Message responseMessage, Activity activity);
    abstract public boolean enterDoneWorkout(Message responseMessage, Activity activity);
    abstract public boolean enterEquipment(Message responseMessage, Activity activity);
    abstract public boolean enterEquipmentType(Message responseMessage, Activity activity);
    abstract public boolean enterMuscle(Message responseMessage, Activity activity);
    abstract public boolean enterMuscleGroup(Message responseMessage, Activity activity);
    abstract public boolean enterRelationWorkoutMuscle(Message responseMessage, Activity activity);
    abstract public boolean enterUserWeight(Message responseMessage, Activity activity);
    abstract public boolean enterWorkout(Message responseMessage, Activity activity);
    abstract public boolean enterTraining(Message responseMessage, Activity activity);
    abstract public boolean enterTrainingSet(Message responseMessage, Activity activity);
    abstract public boolean enterTrainingWorkout(Message responseMessage, Activity activity);
    abstract public boolean enterDoneTraining(Message responseMessage, Activity activity);

}
