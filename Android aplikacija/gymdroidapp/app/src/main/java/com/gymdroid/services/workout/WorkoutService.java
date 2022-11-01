package com.gymdroid.services.workout;

import android.app.Activity;

import com.gymdroid.R;
import com.gymdroid.domain.beans.RelationWorkoutMuscle;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.services.BaseService;
import com.gymdroid.view.CardViewMuscleDetails;

import java.util.ArrayList;

public class WorkoutService extends BaseService {

    public boolean createWorkout(Activity activity, Workout workout) {

        ArrayList<CardViewMuscleDetails> cardViewMuscleDetails = INSTANCE.getFasterInputServiceNewWorkout().getCardViewMuscleDetails();

        if(workout == null) return false;
        if(cardViewMuscleDetails == null) return false;
        if(cardViewMuscleDetails.size() < 1) return false;

        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString( R.string.server_loading_messages_adding_new_workout));

        User user = INSTANCE.getConfigurationService().getUser();
        ArrayList<RelationWorkoutMuscle> relationWorkoutMuscleArrayList = new ArrayList<>();
        for(int i = 0; i < cardViewMuscleDetails.size(); i++) {
            RelationWorkoutMuscle relationWorkoutMuscle = new RelationWorkoutMuscle();
            relationWorkoutMuscle.setMuscleTargetPriority(cardViewMuscleDetails.get(i).getPriority());
            relationWorkoutMuscle.setMuscleId(cardViewMuscleDetails.get(i).getMuscleId());
            relationWorkoutMuscleArrayList.add(relationWorkoutMuscle);
        }

        INSTANCE.getWebSocketRequestOperationService().createWorkout(activity,user,workout,relationWorkoutMuscleArrayList);
        INSTANCE.getFasterInputServiceNewWorkout().clearData();
        return true;
    }

    public boolean deleteWorkout(Activity activity, Workout workout) {
        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString(R.string.server_loading_messages_deleting_workout));
        User user = INSTANCE.getConfigurationService().getUser();
        INSTANCE.getWebSocketRequestOperationService().deleteWorkout(activity, user, workout);
        return true;
    }

}
