package com.gymdroid.activities;

import android.os.Bundle;

import androidx.fragment.app.FragmentTransaction;

import com.gymdroid.R;
import com.gymdroid.activities.base.FloatingBaseActivity;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.fragments.training.TrainingFinishFragment;
import com.gymdroid.fragments.training.TrainingPauseBetweenSetsFragment;
import com.gymdroid.fragments.training.TrainingPauseBetweenWorkoutsFragment;
import com.gymdroid.fragments.training.TrainingStartFragment;
import com.gymdroid.fragments.training.TrainingWorkoutFragment;
import com.gymdroid.services.AllServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TrainingActivity extends FloatingBaseActivity {

    public static final String TRAINING_ID = "trainingId";
    private Training training;
    private ArrayList<TrainingWorkout> trainingWorkouts;
    private HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap;
    private int currentWorkoutIndex = 0;
    private int currentSetIndex = 0;

    private ArrayList<DoneWorkout> currentlyDoneWorkoutArrayList;
    private ArrayList<DoneSet> currentlyDoneSetArrayList;
    private ArrayList<Integer> doneSetBreakingPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        int trainingId = getIntent().getIntExtra(TRAINING_ID, EXPECTED_ID);
        if(trainingId == EXPECTED_ID) {
            onBackPressed();
        }
        else {
            training = database.getTraining(trainingId);
            trainingWorkouts = database.getTrainingWorkoutArrayList(trainingId);
            trainingSetHashMap = database.getTrainingSetHashMap(trainingWorkouts);

            currentlyDoneWorkoutArrayList = new ArrayList<>();
            currentlyDoneSetArrayList = new ArrayList<>();
            doneSetBreakingPoints = new ArrayList<>();

            TrainingStartFragment trainingStartFragment = new TrainingStartFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, trainingStartFragment).commit();
        }
    }

    public void changeToWorkoutFragment() {
        TrainingWorkoutFragment newFragment = new TrainingWorkoutFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeToTrainingPauseBetweenSetsFragment() {
        TrainingPauseBetweenSetsFragment newFragment = new TrainingPauseBetweenSetsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeToTrainingPauseBetweenWorkoutsFragment() {
        TrainingPauseBetweenWorkoutsFragment newFragment = new TrainingPauseBetweenWorkoutsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void changeToTrainingFinishFragment() {
        TrainingFinishFragment newFragment = new TrainingFinishFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void addDoneSet(DoneSet doneSet) {
        boolean pauseBetweenWorkouts = false;
        currentlyDoneSetArrayList.add(doneSet);
        currentSetIndex++;
        if(currentSetIndex == trainingSetHashMap.get(currentWorkoutIndex).size()) {
            DoneWorkout doneWorkout = new DoneWorkout();
            doneWorkout.setDoneWorkoutDate(new Date());
            doneWorkout.setWorkoutId(trainingWorkouts.get(currentWorkoutIndex).getWorkoutId());
            currentlyDoneWorkoutArrayList.add(doneWorkout);
            currentWorkoutIndex++;
            doneSetBreakingPoints.add(currentSetIndex);
            currentSetIndex = 0;
            pauseBetweenWorkouts = true;
        }
        if(currentWorkoutIndex == trainingWorkouts.size()) {
            changeToTrainingFinishFragment();
        }
        else {
            if(pauseBetweenWorkouts) {
                changeToTrainingPauseBetweenWorkoutsFragment();
            } else {
                changeToTrainingPauseBetweenSetsFragment();
            }
        }
    }

    public AllServices getAllServices() {
        return allServices;
    }

    public Workout getCurrentWorkout() {
        TrainingWorkout trainingWorkout = trainingWorkouts.get(currentWorkoutIndex);
        return database.getWorkout(trainingWorkout.getWorkoutId());
    }

    public TrainingWorkout getCurrentTrainingWorkout() {
        return trainingWorkouts.get(currentWorkoutIndex);
    }

    public TrainingSet getCurrentTrainingSet() {
        return trainingSetHashMap.get(currentWorkoutIndex).get(currentSetIndex);
    }

    public Training getTraining() {
        return training;
    }

    public int getCurrentWorkoutIndex() {
        return currentWorkoutIndex;
    }

    public int getCurrentSetIndex() {
        return currentSetIndex;
    }

    public ArrayList<DoneWorkout> getCurrentlyDoneWorkoutArrayList() {
        return currentlyDoneWorkoutArrayList;
    }

    public ArrayList<DoneSet> getCurrentlyDoneSetArrayList() {
        return currentlyDoneSetArrayList;
    }

    public ArrayList<Integer> getDoneSetBreakingPoints() {
        return doneSetBreakingPoints;
    }

    @Override
    public void onBackPressed() {

    }
}
