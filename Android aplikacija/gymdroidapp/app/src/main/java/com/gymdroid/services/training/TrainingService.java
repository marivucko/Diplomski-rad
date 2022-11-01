package com.gymdroid.services.training;

import android.app.Activity;

import com.gymdroid.R;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.services.BaseService;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainingService extends BaseService {

    public boolean createTraining(Activity activity, Training training, ArrayList<TrainingWorkout> trainingWorkoutArrayList, HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap) {
        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity, activity.getResources().getString( R.string.server_loading_messages_adding_new_training));
        User user = INSTANCE.getConfigurationService().getUser();
        INSTANCE.getWebSocketRequestOperationService().createTraining(activity, user, training, trainingWorkoutArrayList, trainingSetHashMap);
        return true;
    }

    public boolean deleteTraining(Activity activity, Training training) {
        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity, activity.getResources().getString(R.string.server_loading_messages_deleting_training));
        User user = INSTANCE.getConfigurationService().getUser();
        INSTANCE.getWebSocketRequestOperationService().deleteTraining(activity, user, training);
        return true;
    }
}
