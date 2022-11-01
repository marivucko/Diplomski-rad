package com.gymdroid.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.activities.base.WhiteActivity;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.services.FasterInputServiceNewWorkout;
import com.gymdroid.view.CardViewMuscleDetails;

public class CreateNewWorkoutActivity extends WhiteActivity {

    private FasterInputServiceNewWorkout fasterInputServiceNewWorkout;

    EditText workoutNameEditText;
    EditText workoutDescriptionEditText;

    int workoutNeedTime = 0;
    int workoutNeedWeight = 1;

    Switch weightSwitch;

    Button addMuscleButton;
    Button removeMuscleButton;
    Button saveButton;

    LinearLayout masterLayout;
    LinearLayout muscleDetailsLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_create_new_workout);

        fasterInputServiceNewWorkout = allServices.getFasterInputServiceNewWorkout();
        masterLayout = findViewById(R.id.masterLayout);

        muscleDetailsLinearLayout = findViewById(R.id.muscleDetailsLinearLayout);
        CardViewMuscleDetails firstCardViewMuscleDetails = fasterInputServiceNewWorkout.initiate(activity,muscleDetailsLinearLayout);
        muscleDetailsLinearLayout.addView(firstCardViewMuscleDetails);

        workoutNameEditText = findViewById(R.id.workoutNameEditText);
        workoutDescriptionEditText = findViewById(R.id.workoutDescriptionEditText);

        //// withWeights
        weightSwitch = findViewById(R.id.weightSwitch);
        weightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                workoutNeedWeight = isChecked ? 1 : 0;
            }
        });

        addMuscleButton = findViewById(R.id.addMuscleButton);
        addMuscleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fasterInputServiceNewWorkout.canMakeMoreCards()) {
                    CardViewMuscleDetails cardViewMuscleDetails = fasterInputServiceNewWorkout.createNewMuscleDetailsCard();
                    muscleDetailsLinearLayout.addView(cardViewMuscleDetails);
                }
                else {
                    Snackbar.make(masterLayout, R.string.new_workout_activity_no_more_muscles, Snackbar.LENGTH_LONG).show();
                }
            }
        });

        removeMuscleButton = findViewById(R.id.removeMuscleButton);
        removeMuscleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fasterInputServiceNewWorkout.isOnlyOneMuscleDetailsCard()){
                    Snackbar.make(masterLayout, R.string.new_workout_at_least_one_muscle, Snackbar.LENGTH_LONG).show();
                }
                else{
                    CardViewMuscleDetails cardViewMuscleDetails = fasterInputServiceNewWorkout.removeLastMuscleDetailsCard();
                    muscleDetailsLinearLayout.removeView(cardViewMuscleDetails);
                }
            }
        });

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String workoutName = workoutNameEditText.getText().toString();
                if(allServices.getStringService().isStringEmpty(workoutName)){
                    workoutNameEditText.setError(activity.getResources().getString(R.string.fill_data_errors_workout_must_have_name));
                    return;
                }

                String workoutDescription = workoutDescriptionEditText.getText().toString();
                if(allServices.getStringService().isStringEmpty(workoutDescription)){
                    workoutDescriptionEditText.setError(activity.getResources().getString(R.string.fill_data_errors_workout_must_have_description));
                    return;
                }

                Workout workout = new Workout();
                workout.setWorkoutName(workoutName);
                workout.setWorkoutDescription(workoutDescription);
//                workout.setWorkoutStatusIsApproved(Workout.WORKOUT_STATUS_NOT_APPROVED);
//                workout.setWorkoutStatusWaitApproval(requestPublic);
                workout.setWorkoutStatusIsApproved(0);
                workout.setWorkoutStatusWaitApproval(Workout.WORKOUT_STATUS_WAIT_APPROVAL);
                workout.setWorkoutNeedTime(workoutNeedTime);
                workout.setWorkoutNeedWeight(workoutNeedWeight);

                allServices.getWorkoutService().createWorkout(activity, workout);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fasterInputServiceNewWorkout.clearData();
    }
}
