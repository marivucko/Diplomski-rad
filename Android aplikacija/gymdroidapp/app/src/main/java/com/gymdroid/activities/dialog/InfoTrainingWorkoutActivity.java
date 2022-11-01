package com.gymdroid.activities.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.activities.base.DialogActivity;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.Workout;


public class InfoTrainingWorkoutActivity extends DialogActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_info_training_workout);

        Intent intent = activity.getIntent();
        int trainingWorkoutId = intent.getIntExtra("TRAINING_WORKOUT_ID", -1);
        if (trainingWorkoutId != -1) {
            TrainingWorkout trainingWorkout = allServices.getDatabase().getTrainingWorkout(trainingWorkoutId);
            if (trainingWorkout != null) {
                Workout workout = allServices.getDatabase().getWorkout(trainingWorkout.getWorkoutId());

                TextView trainingWorkoutName = findViewById(R.id.trainingWorkoutName);
                trainingWorkoutName.setText(workout.getWorkoutName());

                TextView trainingWorkoutDescription = findViewById(R.id.trainingWorkoutDescription);
                trainingWorkoutDescription.setText(workout.getWorkoutDescription() + "\n");
            }
        }

        enableClose();
    }
}
