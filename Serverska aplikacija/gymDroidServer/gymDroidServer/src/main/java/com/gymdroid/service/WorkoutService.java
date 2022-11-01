package com.gymdroid.service;

import com.gymdroid.dao.*;
import com.gymdroid.domain.beans.*;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.WorkoutCreateRequestMessage;
import com.gymdroid.domain.message.request.WorkoutDeleteRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.domain.message.response.WorkoutCreateResponseMessage;
import com.gymdroid.domain.message.response.WorkoutDeleteResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterWorkoutResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadWorkoutResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class WorkoutService {

    public static LoginLoadWorkoutResponseMessage loginLoadWorkouts(LoginLoadRequestMessage loginLoadRequestMessage) {
        User user = loginLoadRequestMessage.getUser();
        ArrayList<Workout> workoutArrayList = WorkoutHandler.getAllUsersOrActiveWorkouts(user);
        ArrayList<Workout> selectedWorkouts = new ArrayList<>();
        int startIndex = loginLoadRequestMessage.getStartIndex();
        int finishIndex = startIndex + loginLoadRequestMessage.getIncrement();
        int totalArraySize = workoutArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedWorkouts.add(workoutArrayList.get(i));
        }

        return new LoginLoadWorkoutResponseMessage( StatusEnum.LOGIN_LOAD_SUCCESS, user, totalArraySize, finishIndex, selectedWorkouts );
    }

    public static WorkoutCreateResponseMessage createWorkout(WorkoutCreateRequestMessage workoutCreateRequestMessage) {

        User user = workoutCreateRequestMessage.getUser();
        Workout workout = workoutCreateRequestMessage.getWorkout();
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = workoutCreateRequestMessage.getRelationWorkoutMuscleArrayList();

        int userId = UserHandler.getUserId(user);
        if(userId == -1) {
            return new WorkoutCreateResponseMessage(StatusEnum.USER_DOES_NOT_EXIST, user, workout, relationWorkoutMuscleArrayList);
        }

        workout.setUserCreatorId(userId);
        int workoutId = WorkoutHandler.insertWorkout(workout);

        for (RelationWorkoutMuscle relationWorkoutMuscle : relationWorkoutMuscleArrayList) {
            relationWorkoutMuscle.setWorkoutId(workoutId);
            RelationWorkoutMuscleHandler.insertRelation(relationWorkoutMuscle);
        }

        return new WorkoutCreateResponseMessage(StatusEnum.WORKOUT_CREATE_SUCCESS, user, workout, relationWorkoutMuscleArrayList);
    }

    public static WorkoutDeleteResponseMessage deleteWorkout(WorkoutDeleteRequestMessage workoutDeleteRequestMessage) {

        User user = workoutDeleteRequestMessage.getUser();
        Workout workout = workoutDeleteRequestMessage.getWorkout();
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = RelationWorkoutMuscleHandler.getAllWorkoutsRelations(workout);

        int userId = UserHandler.getUserId(user);
        if(userId == -1) {
            return new WorkoutDeleteResponseMessage(StatusEnum.USER_DOES_NOT_EXIST, user, workout);
        }

        for (RelationWorkoutMuscle relationWorkoutMuscle : relationWorkoutMuscleArrayList) {
            RelationWorkoutMuscleHandler.deleteRelationWorkoutMuscle(relationWorkoutMuscle);
        }

        ArrayList<DoneWorkout> doneWorkoutArrayList = DoneWorkoutHandler.getDoneWorkout(workout);
        for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
            DoneWorkout doneWorkout = doneWorkoutArrayList.get(i);
            ArrayList<DoneSet> doneSetArrayList = DoneSetHandler.getAllDoneWorkoutsDoneSets(doneWorkout);
            for (int j = 0; j < doneSetArrayList.size(); j++) {
                DoneSetHandler.deleteDoneSet(doneSetArrayList.get(j).getDoneSetId());
            }
            DoneWorkoutHandler.deleteDoneWorkout(doneWorkout.getDoneWorkoutId());
        }

        ArrayList<TrainingWorkout> trainingWorkoutArrayList = TrainingWorkoutHandler.getTrainingWorkoutByWorkoutId(workout.getWorkoutId());
        for (int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            TrainingWorkout trainingWorkout = trainingWorkoutArrayList.get(i);
            ArrayList<TrainingSet> trainingSetArrayList = TrainingSetHandler.getTrainingSetByTrainingWorkoutId(trainingWorkout.getTrainingWorkoutId());
            for (int j = 0; j < trainingSetArrayList.size(); j++) {
                TrainingSet trainingSet = trainingSetArrayList.get(j);
                TrainingSetHandler.deleteTrainingSet(trainingSet.getTrainingSetId());
            }
            TrainingWorkoutHandler.deleteTrainingWorkout(trainingWorkout.getTrainingWorkoutId());
            Training training = TrainingHandler.getTraining(trainingWorkout.getTrainingId());
            TrainingService.deleteTraining(training);
        }

        WorkoutHandler.deleteWorkout(workout.getWorkoutId());

        return new WorkoutDeleteResponseMessage(StatusEnum.DELETE_WORKOUT_SUCCESS, user, workout);
    }

    public static EnterWorkoutResponseMessage enterWorkout(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<Workout> workoutArrayList;

        if (enterRequestMessage.getOperation() == Operation.INSERT)
            workoutArrayList = WorkoutHandler.generateMissingListForInsert(lastUpdatedAt,user);
        else
            workoutArrayList = WorkoutHandler.generateMissingListForUpdate(lastUpdatedAt,user);


        ArrayList<Workout> selectedUserWeightArrayList = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = workoutArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedUserWeightArrayList.add(workoutArrayList.get(i));
        }

        return new EnterWorkoutResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedUserWeightArrayList, enterRequestMessage.getOperation()
        );
    }

}
