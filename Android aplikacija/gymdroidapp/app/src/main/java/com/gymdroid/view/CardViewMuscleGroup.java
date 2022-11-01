package com.gymdroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.domain.beans.MuscleGroup;
import com.gymdroid.services.AllServices;

@SuppressLint("ViewConstructor")
public class CardViewMuscleGroup extends RelativeLayout {

    Button startButton;
    TextView workoutNameTextView;
    TextView numberOfWorkoutsTextView;
    AllServices allServices;
    int numberOfWorkouts;

    public CardViewMuscleGroup(final Activity activity, final ViewGroup parent, final MuscleGroup muscleGroup) {
        super(activity);

        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.card_muscle_group_view, parent, false);
        addView(relativeLayout);

        allServices = AllServices.getInstance(activity);

        workoutNameTextView = findViewById(R.id.workoutNameTextView);
        workoutNameTextView.setText(muscleGroup.getMuscleGroupName());

        numberOfWorkouts = allServices.getDatabase().getUniqueWorkoutArrayList(muscleGroup.getMuscleGroupId()).size();
        numberOfWorkoutsTextView = findViewById(R.id.numberOfWorkoutsTextView);
        numberOfWorkoutsTextView.setText(activity.getResources().getString(R.string.number_of_workouts) + numberOfWorkouts);

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfWorkouts == 0) {
                    Snackbar.make(parent,activity.getResources().getString(R.string.server_is_empty_no_workout_for_muscle_group),Snackbar.LENGTH_SHORT).show();
                }
                else {
                    allServices.getStartActivityService().startAddPracticeActivity(activity, parent, muscleGroup.getMuscleGroupId());
                }
            }
        });

    }

}
