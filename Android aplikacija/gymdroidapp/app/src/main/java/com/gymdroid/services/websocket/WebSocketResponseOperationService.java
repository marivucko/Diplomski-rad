package com.gymdroid.services.websocket;

import android.app.Activity;

import com.google.gson.GsonBuilder;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.Message;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.AddMultiplePracticesResponseMessage;
import com.gymdroid.domain.message.response.EquipmentResponseMessage;
import com.gymdroid.domain.message.response.PracticeAddResponseMessage;
import com.gymdroid.domain.message.response.TrainingCreateResponseMessage;
import com.gymdroid.domain.message.response.TrainingDeleteResponseMessage;
import com.gymdroid.domain.message.response.UserWeightResponseMessage;
import com.gymdroid.domain.message.response.WorkoutCreateResponseMessage;
import com.gymdroid.domain.message.response.WorkoutDeleteResponseMessage;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.websocket.core.WebSocketResponseService;

import java.util.ArrayList;
import java.util.HashMap;

public class WebSocketResponseOperationService extends WebSocketResponseService {

    public boolean createWorkout(Message responseMessage, Activity activity) {
        WorkoutCreateResponseMessage workoutCreateResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), WorkoutCreateResponseMessage.class);
        StatusEnum statusEnum = workoutCreateResponseMessage.getStatus();
        switch (statusEnum) {
            case WORKOUT_CREATE_SUCCESS: {
                Workout workout = workoutCreateResponseMessage.getWorkout();
                ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = workoutCreateResponseMessage.getRelationWorkoutMuscleArrayList();
                INSTANCE.getDatabase().insert(workout);
                INSTANCE.getDatabase().insertRelationWorkoutMuscles(relationWorkoutMuscleArrayList);
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getStartActivityService().startMainActivityWorkoutFragment(activity);
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean deleteWorkout(Message responseMessage, Activity activity) {
        WorkoutDeleteResponseMessage workoutCreateResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), WorkoutDeleteResponseMessage.class);
        StatusEnum statusEnum = workoutCreateResponseMessage.getStatus();
        switch (statusEnum) {
            case DELETE_WORKOUT_SUCCESS: {
                Workout workout = workoutCreateResponseMessage.getWorkout();
                INSTANCE.getDatabase().deleteRelationWorkoutMusclesByWorkoutId(workout.getWorkoutId());
                INSTANCE.getDatabase().deleteDoneWorkoutAndDoneSet(workout);
                INSTANCE.getDatabase().deleteTrainingWorkoutTrainingSet(workout);
                INSTANCE.getDatabase().deleteWorkout(workout.getWorkoutId());
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getStartActivityService().startMainActivityWorkoutFragment(activity);
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean createTraining(Message responseMessage, Activity activity) {
        TrainingCreateResponseMessage trainingCreateResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), TrainingCreateResponseMessage.class);
        StatusEnum statusEnum = trainingCreateResponseMessage.getStatus();
        switch (statusEnum) {
            case TRAINING_CREATE_SUCCESS: {
                Training training = trainingCreateResponseMessage.getTraining();
                ArrayList<TrainingWorkout> trainingWorkoutArrayList = trainingCreateResponseMessage.getTrainingWorkoutArrayList();
                HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap = trainingCreateResponseMessage.getTrainingSetHashMap();
                AllServices.getInstance(activity).getDatabase().insertNewTraining(training, trainingWorkoutArrayList, trainingSetHashMap);
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getStartActivityService().startMainActivity(activity);
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean deleteTraining(Message responseMessage, Activity activity) {
        TrainingDeleteResponseMessage trainingdeleteResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), TrainingDeleteResponseMessage.class);
        StatusEnum statusEnum = trainingdeleteResponseMessage.getStatus();
        switch (statusEnum) {
            case DELETE_TRAINING_SUCCESS: {
                Training training = trainingdeleteResponseMessage.getTraining();
                AllServices.getInstance(activity).getDatabase().deleteTraining(training.getTrainingId());
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getStartActivityService().startMainActivity(activity);
                return true;
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean addEquipment(Message responseMessage, Activity activity) {
        EquipmentResponseMessage equipmentResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EquipmentResponseMessage.class);
        StatusEnum statusEnum = equipmentResponseMessage.getStatus();
        switch (statusEnum) {
            case ADD_EQUIPMENT_SUCCESS: {
                User user = equipmentResponseMessage.getUser();
                Equipment equipment = equipmentResponseMessage.getEquipment();
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getStartActivityService().startMainActivityEquipmentFragment(activity);
                INSTANCE.getConfigurationService().setUser(user);
                INSTANCE.getDatabase().insert(equipment);
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean deleteEquipment(Message responseMessage, Activity activity) {
        EquipmentResponseMessage equipmentResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EquipmentResponseMessage.class);
        StatusEnum statusEnum = equipmentResponseMessage.getStatus();
        switch (statusEnum) {
            case DELETE_EQUIPMENT_SUCCESS: {
                User user = equipmentResponseMessage.getUser();
                Equipment equipment = equipmentResponseMessage.getEquipment();
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getStartActivityService().startMainActivityEquipmentFragment(activity);
                INSTANCE.getConfigurationService().setUser(user);
                INSTANCE.getDatabase().delete(equipment);
                //INSTANCE.getDatabase().deleteUpgradableEquipment(equipment);
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean addUserWeight(Message responseMessage, Activity activity) {
        UserWeightResponseMessage userWeightResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), UserWeightResponseMessage.class);
        StatusEnum statusEnum = userWeightResponseMessage.getStatus();
        switch (statusEnum) {
            case ADD_USER_WEIGHT_SUCCESS: {
                UserWeight userWeight = userWeightResponseMessage.getUserWeight();
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getStartActivityService().startMainActivityWeightFragment(activity);
                INSTANCE.getDatabase().insert(userWeight);
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean deleteUserWeight(Message responseMessage, Activity activity) {
        UserWeightResponseMessage userWeightResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), UserWeightResponseMessage.class);
        StatusEnum statusEnum = userWeightResponseMessage.getStatus();
        switch (statusEnum) {
            case DELETE_USER_WEIGHT_SUCCESS: {
                UserWeight userWeight = userWeightResponseMessage.getUserWeight();
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getStartActivityService().startMainActivityWeightFragment(activity);
                INSTANCE.getDatabase().delete(userWeight);
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    public boolean addPractice(Message responseMessage, Activity activity) {
        PracticeAddResponseMessage practiceAddResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), PracticeAddResponseMessage.class);
        StatusEnum statusEnum = practiceAddResponseMessage.getStatus();
        switch (statusEnum) {
            case ADD_PRACTICE_SUCCESS: {
                DoneWorkout doneWorkout = practiceAddResponseMessage.getDoneWorkout();
                ArrayList<DoneSet> doneSetArrayList = practiceAddResponseMessage.getDoneSetArrayList();
                INSTANCE.getDatabase().insertPractice(doneWorkout,doneSetArrayList);
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getStartActivityService().startMainActivity(activity);
                return true;
            }
            default: return handleErrors(activity,statusEnum);
        }
    }

    public boolean addMultiplePractices(Message responseMessage, Activity activity) {
        AddMultiplePracticesResponseMessage operationResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), AddMultiplePracticesResponseMessage.class);
        StatusEnum statusEnum = operationResponseMessage.getStatus();
        switch (statusEnum) {
            case ADD_MULTIPLE_PRACTICES_SUCCESS: {
                ArrayList<DoneWorkout> doneWorkoutArrayList = operationResponseMessage.getDoneWorkoutArrayList();
                HashMap<Integer,ArrayList<DoneSet>> doneSetHashMap = operationResponseMessage.getDoneSetHashMap();
                DoneTraining doneTraining = operationResponseMessage.getDoneTraining();
                INSTANCE.getDatabase().insertMultiplePractices(doneWorkoutArrayList,doneSetHashMap);
                INSTANCE.getDatabase().insertDoneTraining(doneTraining);
                INSTANCE.getDialogResponseService().signalizeReceived();
                INSTANCE.getStartActivityService().startMainActivity(activity);
                return true;
            }
            default: return handleErrors(activity,statusEnum);
        }
    }

}
