package com.gymdroid.services.websocket;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.domain.beans.Muscle;
import com.gymdroid.domain.beans.MuscleGroup;
import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.Message;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.EnterResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterDoneSetResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterDoneTrainingResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterDoneWorkoutResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterEquipmentResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterEquipmentTypeResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterMuscleGroupResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterMuscleResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterRelationWorkoutMuscleResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterTrainingResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterTrainingSetResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterTrainingWorkoutResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterUserWeightResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterWorkoutResponseMessage;
import com.gymdroid.services.websocket.core.WebSocketRequestService;
import com.gymdroid.services.websocket.core.WebSocketResponseEnterCoreService;

import java.util.ArrayList;

public class WebSocketResponseEnterService extends WebSocketResponseEnterCoreService {

    public boolean enter(Message responseMessage, Activity activity) {
        Gson gson = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create();
        enterResponseMessage = gson.fromJson(responseMessage.getData(), EnterResponseMessage.class);
        User user = INSTANCE.getConfigurationService().getUser();
        INSTANCE.getDialogResponseService().signalizeReceived();
        StatusEnum statusEnum = enterResponseMessage.getStatus();

        switch (statusEnum) {
            case LOGIN_SUCCESS: {
                INSTANCE.getStartActivityService().startMainActivity(activity);

                System.out.println("INSERT");
                System.out.println(enterResponseMessage.isDoneSetNeedInsert());
                System.out.println(enterResponseMessage.isDoneTrainingNeedInsert());
                System.out.println(enterResponseMessage.isDoneWorkoutNeedInsert());
                System.out.println(enterResponseMessage.isEquipmentNeedInsert());
                System.out.println(enterResponseMessage.isEquipmentTypeNeedInsert());
                System.out.println(enterResponseMessage.isMuscleNeedInsert());
                System.out.println(enterResponseMessage.isMuscleGroupNeedInsert());
                System.out.println(enterResponseMessage.isRelationWorkoutMuscleNeedInsert());
                System.out.println(enterResponseMessage.isTrainingNeedInsert());
                System.out.println(enterResponseMessage.isTrainingSetNeedInsert());
                System.out.println(enterResponseMessage.isTrainingWorkoutNeedInsert());
                System.out.println(enterResponseMessage.isUserWeightNeedInsert());
                System.out.println(enterResponseMessage.isWorkoutNeedInsert());

                System.out.println("UPDATE");
                System.out.println(enterResponseMessage.isDoneSetNeedUpdate());
                System.out.println(enterResponseMessage.isDoneTrainingNeedUpdate());
                System.out.println(enterResponseMessage.isDoneWorkoutNeedUpdate());
                System.out.println(enterResponseMessage.isEquipmentNeedUpdate());
                System.out.println(enterResponseMessage.isEquipmentTypeNeedUpdate());
                System.out.println(enterResponseMessage.isMuscleNeedUpdate());
                System.out.println(enterResponseMessage.isMuscleGroupNeedUpdate());
                System.out.println(enterResponseMessage.isRelationWorkoutMuscleNeedUpdate());
                System.out.println(enterResponseMessage.isTrainingNeedUpdate());
                System.out.println(enterResponseMessage.isUserWeightNeedUpdate());
                System.out.println(enterResponseMessage.isWorkoutNeedUpdate());

                System.out.println("DELETE");
                System.out.println(enterResponseMessage.isDoneSetNeedDelete());
                System.out.println(enterResponseMessage.isDoneTrainingNeedDelete());
                System.out.println(enterResponseMessage.isDoneWorkoutNeedDelete());
                System.out.println(enterResponseMessage.isEquipmentNeedDelete());
                System.out.println(enterResponseMessage.isEquipmentTypeNeedDelete());
                System.out.println(enterResponseMessage.isMuscleNeedDelete());
                System.out.println(enterResponseMessage.isMuscleGroupNeedDelete());
                System.out.println(enterResponseMessage.isRelationWorkoutMuscleNeedDelete());
                System.out.println(enterResponseMessage.isTrainingNeedDelete());
                System.out.println(enterResponseMessage.isUserWeightNeedDelete());
                System.out.println(enterResponseMessage.isWorkoutNeedDelete());

                return decideNext(activity, user, WebSocketRequestService.FIRST_START_INDEX);
            }
            default: return handleErrors(activity, statusEnum);
        }
    }

    @Override
    public boolean enterDoneSet(Message responseMessage, Activity activity) {
        EnterDoneSetResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterDoneSetResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<DoneSet> doneSetArrayList = enterInsertResponseMessage.getDoneSetArrayList();

                if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                    INSTANCE.getDatabase().insertDoneSet(doneSetArrayList);
                else
                    INSTANCE.getDatabase().updateDoneSet(doneSetArrayList);

            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterDoneSet(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterDoneWorkout(Message responseMessage, Activity activity) {
        EnterDoneWorkoutResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterDoneWorkoutResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<DoneWorkout> doneWorkoutArrayList = enterInsertResponseMessage.getDoneSetArrayList();

            System.out.println( "-------------------" );
            for (int i = 0; i < doneWorkoutArrayList.size(); i++)
                System.out.println( doneWorkoutArrayList.get(i).getDoneWorkoutId() );
            System.out.println( "-------------------" );

                if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                    INSTANCE.getDatabase().insertDoneWorkout(doneWorkoutArrayList);
                else
                    INSTANCE.getDatabase().updateDoneWorkout(doneWorkoutArrayList);

            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterDoneWorkout(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterEquipment(Message responseMessage, Activity activity) {
        EnterEquipmentResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterEquipmentResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<Equipment> equipmentArrayList = enterInsertResponseMessage.getEquipmentArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().insertEquipment(equipmentArrayList);
            else
                INSTANCE.getDatabase().updateEquipment(equipmentArrayList);


            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterEquipment(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterEquipmentType(Message responseMessage, Activity activity) {
        EnterEquipmentTypeResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterEquipmentTypeResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<EquipmentType> equipmentTypeArrayList = enterInsertResponseMessage.getEquipmentTypeArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().insertEquipmentTypes(equipmentTypeArrayList);
            else
                INSTANCE.getDatabase().updateEquipmentType(equipmentTypeArrayList);

            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterEquipmentTypes(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterMuscle(Message responseMessage, Activity activity) {
        EnterMuscleResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterMuscleResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<Muscle> muscleArrayList = enterInsertResponseMessage.getMuscleArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().insertMuscles(muscleArrayList);
            else
                INSTANCE.getDatabase().updateMuscle(muscleArrayList);

            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterMuscle(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterMuscleGroup(Message responseMessage, Activity activity) {
        EnterMuscleGroupResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterMuscleGroupResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<MuscleGroup> muscleGroupArrayList = enterInsertResponseMessage.getMuscleGroupArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().insertMuscleGroups(muscleGroupArrayList);
            else
                INSTANCE.getDatabase().updateMuscleGroup(muscleGroupArrayList);


            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterMuscleGroup(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterRelationWorkoutMuscle(Message responseMessage, Activity activity) {
        EnterRelationWorkoutMuscleResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterRelationWorkoutMuscleResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = enterInsertResponseMessage.getRelationWorkoutMuscleArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().insertRelationWorkoutMuscles(relationWorkoutMuscleArrayList);
            else
                INSTANCE.getDatabase().updateRelationWorkoutMuscle(relationWorkoutMuscleArrayList);

            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterRelationWorkoutMuscle(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterUserWeight(Message responseMessage, Activity activity) {
        EnterUserWeightResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterUserWeightResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<UserWeight> userWeightArrayList = enterInsertResponseMessage.getUserWeightArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().insertUserWeight(userWeightArrayList);
            else
                INSTANCE.getDatabase().updateUserWeight(userWeightArrayList);

            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterUserWeight(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterWorkout(Message responseMessage, Activity activity) {
        EnterWorkoutResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterWorkoutResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<Workout> workoutArrayList= enterInsertResponseMessage.getWorkoutArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().insertWorkouts(workoutArrayList);
            else
                INSTANCE.getDatabase().updateWorkout(workoutArrayList);


            if(finishIndex < totalArraySize) {
                //INSTANCE.getWebSocketRequestLoadEnterService().enterInsertUserWeight(activity, user, finishIndex);
                INSTANCE.getWebSocketRequestEnterService().enterWorkouts(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterTraining(Message responseMessage, Activity activity) {
        EnterTrainingResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterTrainingResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<Training> trainingArrayList= enterInsertResponseMessage.getTrainingArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().enterInsertTraining(trainingArrayList);
            else
                INSTANCE.getDatabase().updateTraining(trainingArrayList);


            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterTraining(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterTrainingSet(Message responseMessage, Activity activity) {
        EnterTrainingSetResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterTrainingSetResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<TrainingSet> trainingSetArrayList = enterInsertResponseMessage.getTrainingSetArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().insertTrainingSet(trainingSetArrayList);
            else
                INSTANCE.getDatabase().updateTrainingSet(trainingSetArrayList);


            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterTrainingSet(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterTrainingWorkout(Message responseMessage, Activity activity) {
        EnterTrainingWorkoutResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterTrainingWorkoutResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<TrainingWorkout> trainingWorkoutArrayList= enterInsertResponseMessage.getTrainingWorkoutArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().insertTrainingWorkout(trainingWorkoutArrayList);
            else
                INSTANCE.getDatabase().updateTrainingWorkout(trainingWorkoutArrayList);


            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterTrainingWorkout(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }

    @Override
    public boolean enterDoneTraining(Message responseMessage, Activity activity) {
        EnterDoneTrainingResponseMessage enterInsertResponseMessage = new GsonBuilder().setDateFormat("MMM dd, yyyy, HH:mm:ss a").create().fromJson(responseMessage.getData(), EnterDoneTrainingResponseMessage.class);
        StatusEnum statusEnum = enterInsertResponseMessage.getStatus();
        if(statusEnum == StatusEnum.ENTER_SUCCESS) {
            int finishIndex = enterInsertResponseMessage.getFinishIndex();
            int totalArraySize = enterInsertResponseMessage.getTotalArraySize();
            User user = enterInsertResponseMessage.getUser();
            ArrayList<DoneTraining> doneTrainingArrayList= enterInsertResponseMessage.getDoneTrainingArrayList();

            if (enterInsertResponseMessage.getOperation() == Operation.INSERT)
                INSTANCE.getDatabase().insertDoneTrainingArrayList(doneTrainingArrayList);
            else
                INSTANCE.getDatabase().updateDoneTraining(doneTrainingArrayList);

            if(finishIndex < totalArraySize) {
                INSTANCE.getWebSocketRequestEnterService().enterDoneTraining(activity, user, finishIndex, enterInsertResponseMessage.getOperation());
            }
            else {
                decideNext(activity,user, WebSocketRequestService.FIRST_START_INDEX);
            }
        }
        else {
            return handleErrors(activity, statusEnum);
        }
        return true;
    }
}
