package com.gymdroid.services.practice;

import android.app.Activity;

import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.User;
import com.gymdroid.services.BaseService;

import java.util.ArrayList;
import java.util.HashMap;

public class PracticeAddService extends BaseService {

    public boolean addPractice(Activity activity, ArrayList<DoneSet> doneSetArrayList, DoneWorkout doneWorkout) {
        User user = INSTANCE.getConfigurationService().getUser();
        doneWorkout.setUserId(user.getUserId());
        return INSTANCE.getWebSocketRequestOperationService().addPractice(activity, user, doneSetArrayList, doneWorkout);
    }

    public boolean addMultiplePractices(Activity activity, ArrayList<DoneSet> doneSetArrayList, ArrayList<DoneWorkout> doneWorkoutArrayList, ArrayList<Integer> doneSetBreakingPoints, Training training) {
        HashMap<Integer,ArrayList<DoneSet>> doneSetHashMap = new HashMap<>();

        int doneSetIndex = 0;
        for(int i = 0; i < doneSetArrayList.size(); ) {
            ArrayList<DoneSet> currentDoneSetArrayList = new ArrayList<>();
            for(int j = i; j < i + doneSetBreakingPoints.get(doneSetIndex); j++) {
                currentDoneSetArrayList.add(doneSetArrayList.get(j));
            }
            doneSetHashMap.put(doneSetIndex,currentDoneSetArrayList);
            i += doneSetBreakingPoints.get(doneSetIndex);
            doneSetIndex++;
        }

        User user = INSTANCE.getConfigurationService().getUser();
        for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
            doneWorkoutArrayList.get(i).setUserId(user.getUserId());
        }

        return INSTANCE.getWebSocketRequestOperationService().addMultiplePractices(activity, user, doneWorkoutArrayList, doneSetHashMap, training);
    }

}
