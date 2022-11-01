package com.gymdroid.fragments.training;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.activities.TrainingActivity;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.services.AllServices;
import com.gymdroid.view.AddPracticeDoneSetView;

public class TrainingWorkoutFragment extends Fragment {

    private FrameLayout masterLayout;
    private TextView workoutNameTextView;
    private LinearLayout setLinearLayout;

    private TrainingActivity activity;
    private Workout workout;
    private Button doneButton;
    private ImageView infoImageView;
    private AddPracticeDoneSetView addPracticeDoneSetView;
    private TrainingWorkout trainingWorkout;
    private TrainingSet trainingSet;
    private int workoutNumeration;
    private int setNumeration;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_workout, container, false);
        activity = (TrainingActivity) getActivity();
        masterLayout = view.findViewById(R.id.masterLayout);

        workout = activity.getCurrentWorkout();
        trainingWorkout = activity.getCurrentTrainingWorkout();
        trainingSet = activity.getCurrentTrainingSet();
        workoutNumeration = activity.getCurrentWorkoutIndex() + 1;
        setNumeration = activity.getCurrentSetIndex() + 1;

        String workoutName = String.valueOf(workoutNumeration) + ". " + workout.getWorkoutName();
        workoutNameTextView = view.findViewById(R.id.workoutNameTextView);
        workoutNameTextView.setText(workoutName);

        setLinearLayout = view.findViewById(R.id.setLinearLayout);
        addPracticeDoneSetView = new AddPracticeDoneSetView(activity,setLinearLayout,workout,setNumeration,true);
        setLinearLayout.addView(addPracticeDoneSetView);

        addPracticeDoneSetView.setReps(trainingSet.getTrainingSetNumberOfReps());
        addPracticeDoneSetView.setTime(trainingSet.getTrainingSetTime());
        addPracticeDoneSetView.setWeight(trainingSet.getTrainingSetWeight());

        doneButton = view.findViewById(R.id.doneButton);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long currentTime = addPracticeDoneSetView.getTime();
                int currentReps = addPracticeDoneSetView.getReps();
                double currentWeight = addPracticeDoneSetView.getWeight();

                if (currentReps <= 0 || currentWeight < 0 || currentTime < 0 ) {
                    Snackbar.make(masterLayout, activity.getResources().getString(R.string.fill_data_errors_pause_all_sets_with_data), Snackbar.LENGTH_LONG).show();
                    return;
                }

                DoneSet doneSet = new DoneSet();
                doneSet.setDoneSetTimeInMilliseconds((int) currentTime);
                doneSet.setDoneSetWeight(currentWeight);
                doneSet.setDoneSetNumberOfReps(currentReps);
                activity.addDoneSet(doneSet);
            }
        });

        infoImageView = view.findViewById(R.id.infoImageView);
        infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllServices.getInstance(activity).getStartActivityService().startInfoTrainingWorkoutActivity(activity, trainingWorkout.getTrainingWorkoutId());
            }
        });

        return view;
    }

}