package com.gymdroid.services.websocket;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gymdroid.R;
import com.gymdroid.dao.Database;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Message;
import com.gymdroid.domain.message.MessageEnum;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.services.websocket.core.WebSocketRequestService;

import java.util.Date;

public class WebSocketRequestEnterService extends WebSocketRequestService {

    private com.gymdroid.domain.message.request.EnterRequestMessage requestMessage;

    public boolean enter(Activity activity, User user) {

        Database database = INSTANCE.getDatabase();
        Date userRegisterDate = INSTANCE.getConfigurationService().getRegisterDate();

        requestMessage = new com.gymdroid.domain.message.request.EnterRequestMessage(
                user,
                database.getDoneSetLastCreationDate(userRegisterDate),
                database.getDoneTrainingLastCreationDate(userRegisterDate),
                database.getDoneWorkoutLastCreationDate(userRegisterDate),
                database.getEquipmentLastCreationDate(userRegisterDate),
                database.getEquipmentTypeLastCreationDate(userRegisterDate),
                database.getMuscleLastCreationDate(userRegisterDate),
                database.getMuscleGroupLastCreationDate(userRegisterDate),
                database.getRelationWorkoutMuscleLastCreationDate(userRegisterDate),
                database.getTrainingLastCreationDate(userRegisterDate),
                database.getTrainingSetLastCreationDate(userRegisterDate),
                database.getTrainingWorkoutLastCreationDate(userRegisterDate),
                database.getUserWeightLastCreationDate(userRegisterDate),
                database.getWorkoutLastCreationDate(userRegisterDate),

                database.getDoneSetLastUpdateDate(userRegisterDate),
                database.getDoneTrainingLastUpdateDate(userRegisterDate),
                database.getDoneWorkoutLastUpdateDate(userRegisterDate),
                database.getEquipmentLastUpdateDate(userRegisterDate),
                database.getEquipmentTypeLastUpdateDate(userRegisterDate),
                database.getMuscleLastUpdateDate(userRegisterDate),
                database.getMuscleGroupLastUpdateDate(userRegisterDate),
                database.getRelationWorkoutMuscleLastUpdateDate(userRegisterDate),
                database.getTrainingLastUpdateDate(userRegisterDate),
                database.getTrainingSetLastUpdateDate(userRegisterDate),
                database.getTrainingWorkoutLastUpdateDate(userRegisterDate),
                database.getUserWeightLastUpdateDate(userRegisterDate),
                database.getWorkoutLastUpdateDate(userRegisterDate)
        );
        Gson gSon = new GsonBuilder().setDateFormat( "MMM dd, yyyy, HH:mm:ss a" ).create();
        Message message = new Message( MessageEnum.ENTER, gSon.toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString( R.string.server_loading_messages_connecting_to_server), message);
    }

    public boolean enterDoneSet(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_DONE_SET, requestMessage.getDoneSetLastCreationDate(), requestMessage.getDoneSetLastUpdateDate(), operation);
    }

    public boolean enterDoneTraining(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_DONE_TRAINING, requestMessage.getDoneTrainingLastCreationDate(), requestMessage.getDoneTrainingLastUpdateDate(), operation);
    }

    public boolean enterDoneWorkout(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_DONE_WORKOUT, requestMessage.getDoneWorkoutLastCreationDate(), requestMessage.getDoneWorkoutLastUpdateDate(), operation);
    }

    public boolean enterEquipment(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_EQUIPMENT, requestMessage.getEquipmentLastCreationDate(), requestMessage.getEquipmentLastUpdateDate(), operation);
    }

    public boolean enterEquipmentTypes(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_EQUIPMENT_TYPE, requestMessage.getEquipmentTypeLastCreationDate(), requestMessage.getEquipmentTypeLastUpdateDate(), operation);
    }

    public boolean enterMuscle(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_MUSCLE, requestMessage.getMuscleLastCreationDate(), requestMessage.getMuscleLastUpdateDate(), operation);
    }

    public boolean enterMuscleGroup(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_MUSCLE_GROUP, requestMessage.getMuscleGroupLastCreationDate(), requestMessage.getMuscleGroupLastUpdateDate(), operation);
    }

    public boolean enterRelationWorkoutMuscle(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_RELATION_WORKOUT_MUSCLE, requestMessage.getRelationWorkoutMuscleLastCreationDate(), requestMessage.getRelationWorkoutMuscleLastUpdateDate(), operation);
    }

    public boolean enterTraining(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_TRAINING, requestMessage.getTrainingLastCreationDate(), requestMessage.getTrainingLastUpdateDate(), operation);
    }

    public boolean enterTrainingSet(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_TRAINING_SET, requestMessage.getTrainingSetLastCreationDate(), requestMessage.getTrainingSetLastUpdateDate(), operation);
    }

    public boolean enterTrainingWorkout(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_TRAINING_WORKOUT, requestMessage.getTrainingWorkoutLastCreationDate(), requestMessage.getTrainingWorkoutLastUpdateDate(), operation);
    }

    public boolean enterUserWeight(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_USER_WEIGHT, requestMessage.getUserWeightLastCreationDate(), requestMessage.getUserWeightLastUpdateDate(), operation);
    }

    public boolean enterWorkouts(Activity activity, User user, int startIndex, Operation operation) {
        return enter(activity, user, startIndex, MessageEnum.ENTER_WORKOUT, requestMessage.getWorkoutLastCreationDate(), requestMessage.getWorkoutLastUpdateDate(), operation);
    }

    public boolean enter(Activity activity, User user, int startIndex, MessageEnum messageEnum, Date lastCreationAt, Date lastUpdatedAt, Operation operation) {
        Date lastDate = (operation == Operation.INSERT) ? lastCreationAt : lastUpdatedAt;
        EnterRequestMessage requestMessage = new EnterRequestMessage(user,startIndex,INCREMENT_FOR_LARGE_DATA, lastDate, operation);
        Message message = new Message(messageEnum, new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().toJsonTree(requestMessage));
        return sendToServer(activity, activity.getResources().getString(R.string.server_loading_messages_loading_data), message);
    }


}
