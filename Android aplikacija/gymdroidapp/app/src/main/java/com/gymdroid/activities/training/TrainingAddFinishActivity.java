package com.gymdroid.activities.training;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.activities.base.WhiteActivity;
import com.gymdroid.dao.table.TrainingTable;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;

import java.util.ArrayList;
import java.util.HashMap;

public class TrainingAddFinishActivity extends WhiteActivity {

    TextView listWorkoutTextView;
    TextView trainingNameTextView;
    TextView trainingDescriptionTextView;
    TextView trainingIntensityLevelTextView;

    Button addWorkoutButton;
    Button saveButton;

    RelativeLayout masterLayout;
    private ArrayList<TrainingWorkout> trainingWorkoutArrayList;
    private HashMap<Integer,ArrayList<TrainingSet>> trainingSetHashMap;
    private Training training;
    private boolean isNotOkToPressBackButton = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_training_add_finish);

        trainingWorkoutArrayList = allServices.getCreateTrainingService().getTrainingWorkoutArrayList();
        trainingSetHashMap = allServices.getCreateTrainingService().getTrainingSetHashMap();
        training = allServices.getCreateTrainingService().getTraining();

        masterLayout = findViewById(R.id.masterLayout);

        trainingNameTextView = findViewById(R.id.trainingNameTextView);
        trainingNameTextView.setText(training.getTrainingName());

        trainingDescriptionTextView = findViewById(R.id.trainingDescriptionTextView);
        trainingDescriptionTextView.setText(training.getTrainingDescription());

        trainingIntensityLevelTextView = findViewById(R.id.trainingIntensityLevelTextView);
        trainingIntensityLevelTextView.setText( TrainingTable.getIntensityName(activity,training.getTrainingIntensityLevel()));

        listWorkoutTextView = findViewById(R.id.listWorkoutTextView);
        String workoutString = "";
        for(int i = 0; i < trainingWorkoutArrayList.size(); i++) {
            int workoutId = trainingWorkoutArrayList.get(i).getWorkoutId();
            int numeration = i+1;
            workoutString += "" + numeration + ". " + database.getWorkout(workoutId).getWorkoutName() + "\n";
        }
        listWorkoutTextView.setText(workoutString);

        addWorkoutButton = findViewById(R.id.addWorkoutButton);
        addWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getStartActivityService().startTrainingAddWorkoutActivity(activity);
            }
        });

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getTrainingService().createTraining(activity, training, trainingWorkoutArrayList, trainingSetHashMap);
                allServices.getCreateTrainingService().clearData();
                //allServices.getStartActivityService().startMainActivity(activity);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        if(isNotOkToPressBackButton) {
            Snackbar.make(masterLayout,getResources().getString(R.string.snack_bar_messages_press_again_to_go_back_delete_data),Snackbar.LENGTH_LONG).show();
            isNotOkToPressBackButton = false;
        }
        else {
            allServices.getCreateTrainingService().clearData();
            super.onBackPressed();
        }
    }
}
