package com.gymdroid.service;

import com.gymdroid.dao.RelationWorkoutMuscleHandler;
import com.gymdroid.dao.WorkoutHandler;
import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.domain.message.response.enter.EnterRelationWorkoutMuscleResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadRelationWorkoutMuscleResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class RelationWorkoutMuscleService {

    public static LoginLoadRelationWorkoutMuscleResponseMessage loginLoadRelations(LoginLoadRequestMessage loginLoadRequestMessage) {
        User user = loginLoadRequestMessage.getUser();
        ArrayList<Workout> workoutArrayList = WorkoutHandler.getAllUsersOrActiveWorkouts(user);
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscles = new ArrayList<>();
        ArrayList<RelationWorkoutMuscle> selectedRelationWorkoutMuscles = new ArrayList<>();

        for(int i = 0; i < workoutArrayList.size(); i++){
            relationWorkoutMuscles.addAll(RelationWorkoutMuscleHandler.getAllWorkoutsRelations(workoutArrayList.get(i)));
        }

        int startIndex = loginLoadRequestMessage.getStartIndex();
        int finishIndex = startIndex + loginLoadRequestMessage.getIncrement();
        int totalArraySize = relationWorkoutMuscles.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedRelationWorkoutMuscles.add(relationWorkoutMuscles.get(i));
        }

        return new LoginLoadRelationWorkoutMuscleResponseMessage( StatusEnum.LOGIN_LOAD_SUCCESS, user, totalArraySize, finishIndex, selectedRelationWorkoutMuscles );
    }

    public static EnterRelationWorkoutMuscleResponseMessage enterRelationWorkoutMuscle(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList;

        if (enterRequestMessage.getOperation() == Operation.INSERT)
            relationWorkoutMuscleArrayList = RelationWorkoutMuscleHandler.generateMissingListForInsert(lastUpdatedAt);
        else
            relationWorkoutMuscleArrayList = RelationWorkoutMuscleHandler.generateMissingListForUpdate(lastUpdatedAt);


        ArrayList<RelationWorkoutMuscle> selectedRelationWorkoutArrayList = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = relationWorkoutMuscleArrayList.size();

        if(finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedRelationWorkoutArrayList.add(relationWorkoutMuscleArrayList.get(i));
        }

        return new EnterRelationWorkoutMuscleResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedRelationWorkoutArrayList, enterRequestMessage.getOperation()
        );
    }

}
