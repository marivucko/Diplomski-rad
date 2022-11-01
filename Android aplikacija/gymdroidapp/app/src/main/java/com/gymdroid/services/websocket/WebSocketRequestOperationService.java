package com.gymdroid.services.websocket;

import android.app.Activity;

import com.google.gson.GsonBuilder;
import com.gymdroid.domain.beans.DoneSet;
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
import com.gymdroid.domain.message.MessageEnum;
import com.gymdroid.domain.message.request.AddMultiplePracticesRequestMessage;
import com.gymdroid.domain.message.request.EquipmentRequestMessage;
import com.gymdroid.domain.message.request.PracticeAddRequestMessage;
import com.gymdroid.domain.message.request.TrainingCreateRequestMessage;
import com.gymdroid.domain.message.request.TrainingDeleteRequestMessage;
import com.gymdroid.domain.message.request.UserWeightRequestMessage;
import com.gymdroid.domain.message.request.WorkoutCreateRequestMessage;
import com.gymdroid.domain.message.request.WorkoutDeleteRequestMessage;
import com.gymdroid.services.websocket.core.WebSocketRequestService;

import java.util.ArrayList;
import java.util.HashMap;

public class WebSocketRequestOperationService extends WebSocketRequestService {

    public boolean createWorkout(Activity activity, User user, Workout workout, ArrayList<RelationWorkoutMuscle> muscleDetailsCards) {
        WorkoutCreateRequestMessage requestMessage = new WorkoutCreateRequestMessage(user,workout,muscleDetailsCards);
        Message message = new Message( MessageEnum.OPERATION_CREATE_WORKOUT, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, "Sending new workout to server...", message);
    }

    public boolean deleteWorkout(Activity activity, User user, Workout workout) {
        WorkoutDeleteRequestMessage requestMessage = new WorkoutDeleteRequestMessage(user, workout);
        Message message = new Message( MessageEnum.OPERATION_DELETE_WORKOUT, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, "Sending the workout to be deleted to server...", message);
    }

    public boolean createTraining(Activity activity, User user, Training training, ArrayList<TrainingWorkout> trainingWorkoutArrayList, HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap) {
        TrainingCreateRequestMessage requestMessage = new TrainingCreateRequestMessage(user, training, trainingWorkoutArrayList, trainingSetHashMap);
        Message message = new Message( MessageEnum.OPERATION_CREATE_TRAINING, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, "Sending new training to server...", message);
    }

    public boolean deleteTraining(Activity activity, User user, Training training) {
        TrainingDeleteRequestMessage requestMessage = new TrainingDeleteRequestMessage(user, training);
        Message message = new Message( MessageEnum.OPERATION_DELETE_TRAINING, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, "Sending the training to be deleted to server...", message);
    }

    public boolean addEquipment(Activity activity, User user, Equipment equipment) {
        EquipmentRequestMessage requestMessage = new EquipmentRequestMessage(user, equipment);
        Message message = new Message( MessageEnum.OPERATION_ADD_EQUIPMENT, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, "Sending new equipment to server...", message);
    }

    public boolean deleteEquipment(Activity activity, User user, Equipment equipment) {
        EquipmentRequestMessage requestMessage = new EquipmentRequestMessage(user, equipment);
        Message message = new Message( MessageEnum.OPERATION_DELETE_EQUIPMENT, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, "Sending the equipment to be deleted to server...", message);
    }

    public boolean addUserWeight(Activity activity, User user, UserWeight userWeight) {
        UserWeightRequestMessage requestMessage = new UserWeightRequestMessage(user, userWeight);
        Message message = new Message( MessageEnum.OPERATION_ADD_USER_WEIGHT, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, "Sending new weight to server...", message);
    }

    public boolean deleteUserWeight(Activity activity, User user, UserWeight userWeight) {
        UserWeightRequestMessage requestMessage = new UserWeightRequestMessage(user, userWeight);
        Message message = new Message( MessageEnum.OPERATION_DELETE_USER_WEIGHT, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, "Sending the weight to be deleted to server...", message);
    }

    public boolean addPractice(Activity activity, User user, ArrayList<DoneSet> doneSetArrayList, DoneWorkout doneWorkout) {
        PracticeAddRequestMessage requestMessage = new PracticeAddRequestMessage(user,doneSetArrayList,doneWorkout);
        Message message = new Message( MessageEnum.OPERATION_ADD_PRACTICE, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, "Sending new practice to server...", message);
    }

    public boolean addMultiplePractices(Activity activity, User user, ArrayList<DoneWorkout> doneWorkoutArrayList, HashMap<Integer,ArrayList<DoneSet>> doneSetHashMap, Training training) {
        AddMultiplePracticesRequestMessage requestMessage = new AddMultiplePracticesRequestMessage(user,doneWorkoutArrayList,doneSetHashMap,training);
        Message message = new Message( MessageEnum.OPERATION_ADD_MULTIPLE_PRACTICES, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, "Sending new training to server...", message);
    }


}
