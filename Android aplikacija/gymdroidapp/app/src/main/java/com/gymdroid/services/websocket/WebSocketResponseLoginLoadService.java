package com.gymdroid.services.websocket;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.Message;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.TrainingMessage;
import com.gymdroid.domain.message.response.LoginResponseMessage;
import com.gymdroid.domain.message.response.RegisterResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadDoneSetResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadDoneTrainingResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadDoneWorkoutResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadEquipmentResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadEquipmentTypeResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadMuscleResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadRelationWorkoutMuscleResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadTrainingResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadUserWeightResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadWorkoutResponseMessage;
import com.gymdroid.services.websocket.core.WebSocketRequestService;
import com.gymdroid.services.websocket.core.WebSocketResponseService;

import java.util.ArrayList;

public class WebSocketResponseLoginLoadService extends WebSocketResponseService {

    public boolean register(Message responseMessage, Activity activity){
        RegisterResponseMessage registerResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), RegisterResponseMessage.class);
        User user = registerResponseMessage.getUser();
        StatusEnum statusEnum = registerResponseMessage.getStatus();

        switch (statusEnum){
            case LOGIN_SUCCESS: case REGISTER_SUCCESS: {
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getConfigurationService().setUser(user);
                INSTANCE.getDatabase().clearDatabase();
                /// start with loading workouts
                INSTANCE.getWebSocketRequestLoginLoadService().loginLoadDoneSet(activity, user, WebSocketRequestService.FIRST_START_INDEX);
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean login(Message responseMessage, Activity activity){
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create();
        LoginResponseMessage loginResponseMessage = gson.fromJson(responseMessage.getData(), LoginResponseMessage.class);
        User user = loginResponseMessage.getUser();
        StatusEnum statusEnum = loginResponseMessage.getStatus();
        System.out.println(statusEnum);
        switch (statusEnum) {
            case LOGIN_SUCCESS: {
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getConfigurationService().setUser(user);
                INSTANCE.getDatabase().clearDatabase();
                /// start with loading workouts
                INSTANCE.getWebSocketRequestLoginLoadService().loginLoadDoneSet(activity, user, WebSocketRequestService.FIRST_START_INDEX);
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean loginLoadDoneSet(Message responseMessage, Activity activity) {
        LoginLoadDoneSetResponseMessage loginLoadResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), LoginLoadDoneSetResponseMessage.class);
        StatusEnum statusEnum = loginLoadResponseMessage.getStatus();

        switch (statusEnum) {
            case LOGIN_LOAD_SUCCESS: {
                int finishIndex = loginLoadResponseMessage.getFinishIndex();
                int totalArraySize = loginLoadResponseMessage.getTotalArraySize();
                User user = loginLoadResponseMessage.getUser();
                ArrayList<DoneSet> doneSetArrayList = loginLoadResponseMessage.getDoneSetArrayList();
                INSTANCE.getDatabase().insertDoneSet(doneSetArrayList);
                if(finishIndex < totalArraySize) {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadDoneSet(activity, user, finishIndex);
                }
                else {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadDoneTraining(activity, user, WebSocketRequestService.FIRST_START_INDEX);
                }
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean loginLoadDoneTraining(Message responseMessage, Activity activity) {
        LoginLoadDoneTrainingResponseMessage loginLoadResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), LoginLoadDoneTrainingResponseMessage.class);
        StatusEnum statusEnum = loginLoadResponseMessage.getStatus();

        switch (statusEnum) {
            case LOGIN_LOAD_SUCCESS: {
                int finishIndex = loginLoadResponseMessage.getFinishIndex();
                int totalArraySize = loginLoadResponseMessage.getTotalArraySize();
                User user = loginLoadResponseMessage.getUser();
                ArrayList<DoneTraining> doneTrainingArrayList = loginLoadResponseMessage.getDoneTrainingArrayList();
                INSTANCE.getDatabase().insertDoneTrainingArrayList(doneTrainingArrayList);
                if(finishIndex < totalArraySize) {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadDoneTraining(activity, user, finishIndex);
                }
                else {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadDoneWorkout(activity, user, WebSocketRequestService.FIRST_START_INDEX);
                }
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean loginLoadDoneWorkout(Message responseMessage, Activity activity) {
        LoginLoadDoneWorkoutResponseMessage loginLoadResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), LoginLoadDoneWorkoutResponseMessage.class);
        StatusEnum statusEnum = loginLoadResponseMessage.getStatus();

        switch (statusEnum) {
            case LOGIN_LOAD_SUCCESS: {
                int finishIndex = loginLoadResponseMessage.getFinishIndex();
                int totalArraySize = loginLoadResponseMessage.getTotalArraySize();
                User user = loginLoadResponseMessage.getUser();
                ArrayList<DoneWorkout> doneWorkoutArrayList = loginLoadResponseMessage.getDoneWorkoutArrayList();
                INSTANCE.getDatabase().insertDoneWorkout(doneWorkoutArrayList);
                if(finishIndex < totalArraySize) {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadDoneWorkout(activity, user, finishIndex);
                }
                else {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadEquipment(activity, user, WebSocketRequestService.FIRST_START_INDEX);
                }
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean loginLoadEquipment(Message responseMessage, Activity activity) {
        LoginLoadEquipmentResponseMessage loginLoadResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), LoginLoadEquipmentResponseMessage.class);
        StatusEnum statusEnum = loginLoadResponseMessage.getStatus();

        switch (statusEnum) {
            case LOGIN_LOAD_SUCCESS: {
                int finishIndex = loginLoadResponseMessage.getFinishIndex();
                int totalArraySize = loginLoadResponseMessage.getTotalArraySize();
                User user = loginLoadResponseMessage.getUser();
                ArrayList<Equipment> equipmentArrayList = loginLoadResponseMessage.getEquipmentArrayList();
                INSTANCE.getDatabase().insertEquipment(equipmentArrayList);
                if(finishIndex < totalArraySize) {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadEquipment(activity, user, finishIndex);
                }
                else {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadEquipmentType(activity, user, WebSocketRequestService.FIRST_START_INDEX);
                }
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean loginLoadEquipmentType(Message responseMessage, Activity activity) {
        LoginLoadEquipmentTypeResponseMessage loginLoadResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), LoginLoadEquipmentTypeResponseMessage.class);
        StatusEnum statusEnum = loginLoadResponseMessage.getStatus();

        switch (statusEnum) {
            case LOGIN_LOAD_SUCCESS: {
                int finishIndex = loginLoadResponseMessage.getFinishIndex();
                int totalArraySize = loginLoadResponseMessage.getTotalArraySize();
                User user = loginLoadResponseMessage.getUser();
                ArrayList<EquipmentType> equipmentTypeArrayList = loginLoadResponseMessage.getEquipmentTypeArrayList();
                INSTANCE.getDatabase().insertEquipmentTypes(equipmentTypeArrayList);
                if(finishIndex < totalArraySize) {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadEquipmentType(activity, user, finishIndex);
                }
                else {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadMuscle(activity, user);
                }
            }
            default: return handleErrors(activity, statusEnum);
        }
    }


    public boolean loginLoadMuscle(Message responseMessage, Activity activity) {
        LoginLoadMuscleResponseMessage loginLoadResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), LoginLoadMuscleResponseMessage.class);
        StatusEnum statusEnum = loginLoadResponseMessage.getStatus();

        switch (loginLoadResponseMessage.getStatus()) {
            case LOGIN_LOAD_SUCCESS: {
                INSTANCE.getDatabase().insertMuscles(loginLoadResponseMessage.getMuscleArrayList());
                INSTANCE.getDatabase().insertMuscleGroups(loginLoadResponseMessage.getMuscleGroupArrayList());
                User user = loginLoadResponseMessage.getUser();
                INSTANCE.getWebSocketRequestLoginLoadService().loginLoadRelationWorkoutMuscle(activity, user, WebSocketRequestService.FIRST_START_INDEX);
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean loginLoadRelationWorkoutMuscle(Message responseMessage, Activity activity) {
        LoginLoadRelationWorkoutMuscleResponseMessage loginLoadResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), LoginLoadRelationWorkoutMuscleResponseMessage.class);
        StatusEnum statusEnum = loginLoadResponseMessage.getStatus();

        switch (statusEnum) {
            case LOGIN_LOAD_SUCCESS: {
                int finishIndex = loginLoadResponseMessage.getFinishIndex();
                int totalArraySize = loginLoadResponseMessage.getTotalArraySize();
                User user = loginLoadResponseMessage.getUser();
                ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = loginLoadResponseMessage.getRelationWorkoutMuscleArrayList();
                INSTANCE.getDatabase().insertRelationWorkoutMuscles(relationWorkoutMuscleArrayList);
                if(finishIndex < totalArraySize) {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadRelationWorkoutMuscle(activity, user,finishIndex);
                }
                else {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadTraining(activity, user, WebSocketRequestService.FIRST_START_INDEX);
                }
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean loginLoadTraining(Message responseMessage, Activity activity) {
        LoginLoadTrainingResponseMessage loginLoadResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), LoginLoadTrainingResponseMessage.class);
        StatusEnum statusEnum = loginLoadResponseMessage.getStatus();

        switch (statusEnum) {
            case LOGIN_LOAD_SUCCESS: {
                int finishIndex = loginLoadResponseMessage.getFinishIndex();
                int totalArraySize = loginLoadResponseMessage.getTotalArraySize();
                User user = loginLoadResponseMessage.getUser();
                ArrayList<TrainingMessage> trainingMessageArrayList = loginLoadResponseMessage.getTrainingMessageArrayList();
                INSTANCE.getDatabase().insertTrainingMessageArrayList(trainingMessageArrayList);
                if(finishIndex < totalArraySize) {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadTraining(activity, user, finishIndex);
                }
                else {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadUserWeight(activity, user, WebSocketRequestService.FIRST_START_INDEX);
                }
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean loginLoadUserWeight(Message responseMessage, Activity activity) {
        LoginLoadUserWeightResponseMessage loginLoadResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), LoginLoadUserWeightResponseMessage.class);
        StatusEnum statusEnum = loginLoadResponseMessage.getStatus();

        switch (statusEnum) {
            case LOGIN_LOAD_SUCCESS: {
                int finishIndex = loginLoadResponseMessage.getFinishIndex();
                int totalArraySize = loginLoadResponseMessage.getTotalArraySize();
                User user = loginLoadResponseMessage.getUser();
                ArrayList<UserWeight> userWeightArrayList = loginLoadResponseMessage.getEquipmentArrayList();
                INSTANCE.getDatabase().insertUserWeight(userWeightArrayList);
                if(finishIndex < totalArraySize) {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadUserWeight(activity, user, finishIndex);
                }
                else {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadWorkout(activity, user, WebSocketRequestService.FIRST_START_INDEX);
                }
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean loginLoadWorkout(Message responseMessage, Activity activity) {
        LoginLoadWorkoutResponseMessage loginLoadResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), LoginLoadWorkoutResponseMessage.class);
        StatusEnum statusEnum = loginLoadResponseMessage.getStatus();

        switch (statusEnum) {
            case LOGIN_LOAD_SUCCESS: {
                int finishIndex = loginLoadResponseMessage.getFinishIndex();
                int totalArraySize = loginLoadResponseMessage.getTotalArraySize();
                User user = loginLoadResponseMessage.getUser();
                ArrayList<Workout> workoutArrayList = loginLoadResponseMessage.getWorkoutArrayList();
                INSTANCE.getDatabase().insertWorkouts(workoutArrayList);
                if(finishIndex < totalArraySize) {
                    INSTANCE.getWebSocketRequestLoginLoadService().loginLoadWorkout(activity, user,finishIndex);
                }
                else {
                    INSTANCE.getDialogResponseService().signalizeReceived();
                    INSTANCE.getStartActivityService().startMainActivity(activity);
                }
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }
}
