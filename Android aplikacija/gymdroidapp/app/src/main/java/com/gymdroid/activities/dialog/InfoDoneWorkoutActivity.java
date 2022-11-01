package com.gymdroid.activities.dialog;

import android.os.Bundle;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.activities.base.DialogActivity;
import com.gymdroid.domain.beans.Workout;

public class InfoDoneWorkoutActivity extends DialogActivity {

    public static final String WORKOUT_ID = "WorkoutId";

    private int workoutId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_info_done_workout);
        enableClose();

        workoutId = getIntent().getIntExtra(WORKOUT_ID,EXPECTED_ID);
        if(workoutId == EXPECTED_ID) {
            onBackPressed();
        }
        else {
            Workout workout = database.getWorkout(workoutId);

            TextView workoutNameTextView = findViewById(R.id.workoutNameTextView);
            workoutNameTextView.setText(workout.getWorkoutName());

            TextView workoutDescriptionTextView = findViewById(R.id.workoutDescriptionTextView);
            workoutDescriptionTextView.setText(workout.getWorkoutDescription() + "\n");

        }

    }
}
