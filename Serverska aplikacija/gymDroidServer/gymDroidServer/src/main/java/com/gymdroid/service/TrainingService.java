package com.gymdroid.service;

import com.gymdroid.dao.*;
import com.gymdroid.domain.beans.*;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.TrainingMessage;
import com.gymdroid.domain.message.request.TrainingCreateRequestMessage;
import com.gymdroid.domain.message.request.TrainingDeleteRequestMessage;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.domain.message.response.TrainingCreateResponseMessage;
import com.gymdroid.domain.message.response.TrainingDeleteResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterTrainingResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadTrainingResponseMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TrainingService {
    public static TrainingCreateResponseMessage createTraining(TrainingCreateRequestMessage trainingCreateRequestMessage) {

        User user = trainingCreateRequestMessage.getUser();
        Training training = trainingCreateRequestMessage.getTraining();
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = trainingCreateRequestMessage.getTrainingWorkoutArrayList();
        HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap = trainingCreateRequestMessage.getTrainingSetHashMap();

        training = TrainingHandler.insertTraining(training);

        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            TrainingWorkout trainingWorkout = trainingWorkoutArrayList.get(i);
            trainingWorkout.setTrainingId(training.getTrainingId());
            trainingWorkout = TrainingWorkoutHandler.insertTrainingWorkout(trainingWorkout);

            for (int j = 0; j < trainingSetHashMap.get(i).size(); j++) {
                TrainingSet trainingSet= trainingSetHashMap.get(i).get(j);
                trainingSet.setTrainingWorkoutId(trainingWorkout.getTrainingWorkoutId());
                TrainingSetHandler.insertTrainingSet(trainingSet);
            }
        }

        return new TrainingCreateResponseMessage(StatusEnum.TRAINING_CREATE_SUCCESS, user, training, trainingWorkoutArrayList, trainingSetHashMap);
    }

    public static TrainingDeleteResponseMessage deleteTraining(TrainingDeleteRequestMessage trainingDeleteRequestMessage) {

        User user = trainingDeleteRequestMessage.getUser();
        Training training = trainingDeleteRequestMessage.getTraining();
        int trainingId = training.getTrainingId();

        ArrayList<TrainingWorkout> trainingWorkoutArrayList = TrainingWorkoutHandler.getTrainingWorkoutByTrainingId(training.getTrainingId());
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            TrainingWorkout trainingWorkout = trainingWorkoutArrayList.get(i);
            ArrayList<TrainingSet> trainingSetArrayList = TrainingSetHandler.getTrainingSetByTrainingWorkoutId(trainingWorkout.getTrainingWorkoutId());
            for (int j = 0; j < trainingSetArrayList.size(); j++) {
                TrainingSet trainingSet = trainingSetArrayList.get(j);
                TrainingSetHandler.deleteTrainingSet(trainingSet.getTrainingSetId());
            }

            TrainingWorkoutHandler.deleteTrainingWorkout(trainingWorkout.getTrainingWorkoutId());
        }

        ArrayList<DoneTraining> doneTrainingArrayList = DoneTrainingHandler.getDoneTrainings(trainingId);
        for (int i = 0; i < doneTrainingArrayList.size(); i++) {
            DoneTrainingHandler.deleteDoneTraining(doneTrainingArrayList.get(i).getDoneTrainingId());
        }

        TrainingHandler.deleteTraining(trainingId);


        return new TrainingDeleteResponseMessage(StatusEnum.DELETE_TRAINING_SUCCESS, user, training);
    }

    public static void deleteTraining(Training training) {
        ArrayList<TrainingWorkout> trainingWorkoutArrayList = TrainingWorkoutHandler.getTrainingWorkoutByTrainingId(training.getTrainingId());
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            TrainingWorkout trainingWorkout = trainingWorkoutArrayList.get(i);
            ArrayList<TrainingSet> trainingSetArrayList = TrainingSetHandler.getTrainingSetByTrainingWorkoutId(trainingWorkout.getTrainingWorkoutId());
            for (int j = 0; j < trainingSetArrayList.size(); j++) {
                TrainingSet trainingSet = trainingSetArrayList.get(j);
                TrainingSetHandler.deleteTrainingSet(trainingSet.getTrainingSetId());
            }

            TrainingWorkoutHandler.deleteTrainingWorkout(trainingWorkout.getTrainingWorkoutId());
        }

        ArrayList<DoneTraining> doneTrainingArrayList = DoneTrainingHandler.getDoneTrainings(training.getTrainingId());
        for (int i = 0; i < doneTrainingArrayList.size(); i++) {
            DoneTrainingHandler.deleteDoneTraining(doneTrainingArrayList.get(i).getDoneTrainingId());
        }

        TrainingHandler.deleteTraining(training.getTrainingId());
    }


    public static EnterTrainingResponseMessage enterTraining(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<Training> trainingArrayList;

        if (enterRequestMessage.getOperation() == Operation.INSERT)
            trainingArrayList = TrainingHandler.generateMissingListForInsert(lastUpdatedAt, user);
        else
            trainingArrayList = TrainingHandler.generateMissingListForUpdate(lastUpdatedAt, user);

        ArrayList<Training> selectedTraining = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = trainingArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedTraining.add(trainingArrayList.get(i));
        }

        return new EnterTrainingResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedTraining, enterRequestMessage.getOperation()
        );
    }



//    public static EnterTrainingResponseMessage enterInsertTrainings(EnterRequestMessage enterRequestMessage) {
//
//        User user = enterRequestMessage.getUser();
//
//        ArrayList<TrainingMessage> selectedTrainingMessage = new ArrayList<>();
//        ArrayList<TrainingMessage> trainingMessageArrayList = new ArrayList<>();
//        ArrayList<Training> trainingArrayList = TrainingHandler.getAllUsersTraining(user);
//
//        for (int i = 0; i < trainingArrayList.size(); i++) {
//            Training training = trainingArrayList.get(i);
//            ArrayList<TrainingWorkout> trainingWorkoutArrayList = TrainingWorkoutHandler.getTrainingWorkoutByTrainingId(training.getTrainingId());
//            HashMap<Integer, ArrayList<TrainingSet>> trainingSetHashMap = new HashMap<>();
//            for (int j = 0; j < trainingWorkoutArrayList.size(); j++) {
//                TrainingWorkout trainingWorkout = trainingWorkoutArrayList.get(j);
//                ArrayList<TrainingSet> trainingSetArrayList = TrainingSetHandler.getTrainingSetByTrainingWorkoutId(trainingWorkout.getTrainingWorkoutId());
//                trainingSetHashMap.put(j, trainingSetArrayList);
//            }
//            TrainingMessage trainingMessage = new TrainingMessage(training, trainingWorkoutArrayList, trainingSetHashMap);
//            trainingMessageArrayList.add(trainingMessage);
//        }
//
//        int startIndex = enterRequestMessage.getStartIndex();
//        int finishIndex = startIndex + enterRequestMessage.getIncrement();
//        int totalArraySize = trainingMessageArrayList.size();
//
//        if (finishIndex > totalArraySize) {
//            finishIndex = totalArraySize;
//        }
//
//        for (int i = startIndex; i < finishIndex; i++) {
//            selectedTrainingMessage.add(trainingMessageArrayList.get(i));
//        }
//
//        return new EnterTrainingResponseMessage(StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedTrainingMessage, enterRequestMessage.getOperation());
//    }

    public static LoginLoadTrainingResponseMessage loginLoadTrainings(LoginLoadRequestMessage requestMessage) {

        User user = requestMessage.getUser();

        ArrayList<TrainingMessage> selectedTrainingMessage = new ArrayList<>();
        ArrayList<TrainingMessage> trainingMessageArrayList = new ArrayList<>();
        ArrayList<Training> trainingArrayList = TrainingHandler.getAllUsersTraining(user);

        for (int i = 0; i < trainingArrayList.size(); i++) {
            Training training = trainingArrayList.get(i);
            ArrayList<TrainingWorkout> trainingWorkoutArrayList = TrainingWorkoutHandler.getTrainingWorkoutByTrainingId(training.getTrainingId());
            HashMap<Integer, ArrayList<TrainingSet>> trainingSetHashMap = new HashMap<>();
            for (int j = 0; j < trainingWorkoutArrayList.size(); j++) {
                TrainingWorkout trainingWorkout = trainingWorkoutArrayList.get(j);
                ArrayList<TrainingSet> trainingSetArrayList = TrainingSetHandler.getTrainingSetByTrainingWorkoutId(trainingWorkout.getTrainingWorkoutId());
                trainingSetHashMap.put(j, trainingSetArrayList);
            }
            TrainingMessage trainingMessage = new TrainingMessage(training, trainingWorkoutArrayList, trainingSetHashMap);
            trainingMessageArrayList.add(trainingMessage);
        }

        int startIndex = requestMessage.getStartIndex();
        int finishIndex = startIndex + requestMessage.getIncrement();
        int totalArraySize = trainingMessageArrayList.size();

        if (finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for (int i = startIndex; i < finishIndex; i++) {
            selectedTrainingMessage.add(trainingMessageArrayList.get(i));
        }

        return new LoginLoadTrainingResponseMessage(StatusEnum.LOGIN_LOAD_SUCCESS, user, totalArraySize, finishIndex, selectedTrainingMessage);
    }

}
