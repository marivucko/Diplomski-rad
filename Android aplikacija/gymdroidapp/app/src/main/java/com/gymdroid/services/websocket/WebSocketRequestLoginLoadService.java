package com.gymdroid.services.websocket;

import android.app.Activity;

import com.google.gson.GsonBuilder;
import com.gymdroid.R;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Message;
import com.gymdroid.domain.message.MessageEnum;
import com.gymdroid.domain.message.request.LoginRequestMessage;
import com.gymdroid.domain.message.request.RegisterRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.services.websocket.core.WebSocketRequestService;

public class WebSocketRequestLoginLoadService extends WebSocketRequestService {

    public boolean register(Activity activity, User user) {
        RegisterRequestMessage requestMessage = new RegisterRequestMessage(user);
        Message message = new Message( MessageEnum.REGISTER, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString( R.string.server_loading_messages_registration_in_progress), message);
    }

    public boolean login(Activity activity, User user) {
        LoginRequestMessage requestMessage = new LoginRequestMessage(user);
        Message message = new Message( MessageEnum.LOGIN, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }


    public boolean loginLoadDoneSet(Activity activity, User user, int startIndex) {
        LoginLoadRequestMessage requestMessage = new LoginLoadRequestMessage(user,startIndex,INCREMENT_FOR_LARGE_DATA);
        Message message = new Message( MessageEnum.LOGIN_LOAD_DONE_SET, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }

    public boolean loginLoadDoneTraining(Activity activity, User user, int startIndex) {
        LoginLoadRequestMessage requestMessage = new LoginLoadRequestMessage(user,startIndex,INCREMENT_FOR_LARGE_DATA);
        Message message = new Message( MessageEnum.LOGIN_LOAD_DONE_TRAINING, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }

    public boolean loginLoadDoneWorkout(Activity activity, User user, int startIndex) {
        LoginLoadRequestMessage requestMessage = new LoginLoadRequestMessage(user,startIndex,INCREMENT_FOR_LARGE_DATA);
        Message message = new Message( MessageEnum.LOGIN_LOAD_DONE_WORKOUT, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }

    public boolean loginLoadEquipment(Activity activity, User user, int startIndex) {
        LoginLoadRequestMessage requestMessage = new LoginLoadRequestMessage(user,startIndex,INCREMENT_FOR_LARGE_DATA);
        Message message = new Message( MessageEnum.LOGIN_LOAD_EQUIPMENT, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }

    public boolean loginLoadEquipmentType(Activity activity, User user, int startIndex) {
        LoginLoadRequestMessage requestMessage = new LoginLoadRequestMessage(user,startIndex,INCREMENT_FOR_LARGE_DATA);
        Message message = new Message( MessageEnum.LOGIN_LOAD_EQUIPMENT_TYPE, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }

    public boolean loginLoadMuscle(Activity activity, User user) {
        LoginLoadRequestMessage requestMessage = new LoginLoadRequestMessage(user,0,INCREMENT_FOR_LARGE_DATA);
        Message message = new Message( MessageEnum.LOGIN_LOAD_MUSCLE, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }

    public boolean loginLoadRelationWorkoutMuscle(Activity activity, User user, int startIndex) {
        LoginLoadRequestMessage requestMessage = new LoginLoadRequestMessage(user,startIndex,INCREMENT_FOR_LARGE_DATA);
        Message message = new Message( MessageEnum.LOGIN_LOAD_RELATION_WORKOUT_MUSCLE, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }

    public boolean loginLoadTraining(Activity activity, User user, int startIndex) {
        LoginLoadRequestMessage requestMessage = new LoginLoadRequestMessage(user,startIndex,INCREMENT_FOR_LARGE_DATA);
        Message message = new Message( MessageEnum.LOGIN_LOAD_TRAINING, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }

    public boolean loginLoadUserWeight(Activity activity, User user, int startIndex) {
        LoginLoadRequestMessage requestMessage = new LoginLoadRequestMessage(user,startIndex,INCREMENT_FOR_LARGE_DATA);
        Message message = new Message( MessageEnum.LOGIN_LOAD_USER_WEIGHT, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }

    public boolean loginLoadWorkout(Activity activity, User user, int startIndex) {
        LoginLoadRequestMessage requestMessage = new LoginLoadRequestMessage(user,startIndex,INCREMENT_FOR_LARGE_DATA);
        Message message = new Message( MessageEnum.LOGIN_LOAD_WORKOUT, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }


}
