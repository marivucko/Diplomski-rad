package com.gymdroid.services;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.view.AddPracticeDoneSetView;

import java.util.ArrayList;

public class FasterInputServiceWorkoutDetails extends BaseService {

    public void refreshWorkoutDetailsLinearLayout(
            Activity activity, LinearLayout setLinearLayout,
            ArrayList<AddPracticeDoneSetView> addPracticeDoneSetViewArrayList, Workout selectedWorkout, boolean showWeight
    ) {
        ArrayList<DoneSet> doneSetArrayList = INSTANCE.getDatabase().countNumberOfSetsInLastDoneWorkout(selectedWorkout.getWorkoutId());

        addPracticeDoneSetViewArrayList.clear();
        setLinearLayout.removeAllViews();

        if(doneSetArrayList.size() == 0) {
            for (int i = 0; i < 3; i++) {
                addPracticeDoneSetViewArrayList.add(new AddPracticeDoneSetView(activity, setLinearLayout, selectedWorkout, i + 1, showWeight));
                setLinearLayout.addView(addPracticeDoneSetViewArrayList.get(i));
            }
        }
        else {
            for (int i = 0; i < doneSetArrayList.size(); i++) {
                addPracticeDoneSetViewArrayList.add(new AddPracticeDoneSetView(activity, setLinearLayout, selectedWorkout, i + 1, showWeight));
                setLinearLayout.addView(addPracticeDoneSetViewArrayList.get(i));
                addPracticeDoneSetViewArrayList.get(i).setReps(doneSetArrayList.get(i).getDoneSetNumberOfReps());
                addPracticeDoneSetViewArrayList.get(i).setWeight(doneSetArrayList.get(i).getDoneSetWeight());
                addPracticeDoneSetViewArrayList.get(i).setTime(doneSetArrayList.get(i).getDoneSetTimeInMilliseconds());
            }
        }
    }

    public void refreshWorkoutDetailsLinearLayoutEmpty(
            Activity activity, LinearLayout setLinearLayout,
            ArrayList<AddPracticeDoneSetView> addPracticeDoneSetViewArrayList, Workout selectedWorkout, boolean showWeight
    ) {
        addPracticeDoneSetViewArrayList.clear();
        setLinearLayout.removeAllViews();
        for (int i = 0; i < 3; i++) {
            addPracticeDoneSetViewArrayList.add(new AddPracticeDoneSetView(activity, setLinearLayout, selectedWorkout, i + 1, showWeight));
            setLinearLayout.addView(addPracticeDoneSetViewArrayList.get(i));
        }
    }

    public boolean saveWorkoutDetailsDataChecker(Activity activity, View masterLayout, ArrayList<AddPracticeDoneSetView> addPracticeDoneSetViewArrayList) {
        for (int i = 0; i < addPracticeDoneSetViewArrayList.size(); i++) {
            int currentReps = addPracticeDoneSetViewArrayList.get(i).getReps();
            double currentWeight = addPracticeDoneSetViewArrayList.get(i).getWeight();
            long currentTime = addPracticeDoneSetViewArrayList.get(i).getTime();
            if (currentReps <= 0 || currentWeight < 0 || currentTime < 0 ) {
                Snackbar.make(masterLayout, activity.getResources().getString( R.string.faster_input_service_snack_bar_all_sets_with_data), Snackbar.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

}
