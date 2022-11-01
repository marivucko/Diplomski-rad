package com.gymdroid.activities.training;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.activities.base.WhiteActivity;
import com.gymdroid.dao.table.TrainingTable;
import com.gymdroid.domain.beans.Training;

public class TrainingAddInformationActivity extends WhiteActivity {

    RelativeLayout masterLayout;

    Spinner intensitySpinner;
    EditText trainingNameEditText;
    EditText trainingDescriptionEditText;
    EditText timerEditText;
    Button nextButton;

    Training training = new Training();
    private boolean isNotOkToPressBackButton = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_training_add_information);
        setUpSpinner();

        masterLayout = findViewById(R.id.masterLayout);

        trainingNameEditText = findViewById(R.id.trainingNameEditText);
        trainingDescriptionEditText = findViewById(R.id.trainingDescriptionEditText);

        timerEditText = findViewById(R.id.timerEditText);
        allServices.getFasterInputServiceTimePicker().setOnClickListeners(activity,timerEditText);

        nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trainingName = trainingNameEditText.getText().toString();
                if(allServices.getStringService().isStringEmpty(trainingName)) {
                    trainingNameEditText.setError(getResources().getString(R.string.fill_data_errors_training_name_is_required));
                    return;
                }

                String trainingDescription = trainingDescriptionEditText.getText().toString();
                if(allServices.getStringService().isStringEmpty(trainingDescription)) {
                    trainingDescriptionEditText.setError(getResources().getString(R.string.fill_data_errors_training_description_is_required));
                    return;
                }

                long durationOfPauseBetweenWorkouts = allServices.getFasterInputServiceTimePicker().getTimeFromEditText(timerEditText);
                if(durationOfPauseBetweenWorkouts == -1) {
                    timerEditText.setError(getResources().getString(R.string.fill_data_errors_pause_between_workouts_is_required));
                    return;
                }

                training.setTrainingName(trainingName);
                training.setTrainingDescription(trainingDescription);
                training.setDurationOfPauseBetweenWorkouts(durationOfPauseBetweenWorkouts);
                training.setUserCreatorId(allServices.getConfigurationService().getUserId());

                allServices.getCreateTrainingService().setTraining(training);
                //allServices.getTrainingService().createTraining(activity, training);
                allServices.getStartActivityService().startTrainingAddWorkoutActivity(activity);
                finish();
            }
        });

    }

    private void setUpSpinner() {
        intensitySpinner = findViewById(R.id.intensitySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.intensity_array));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        intensitySpinner.setAdapter(adapter);
        intensitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) parent.getChildAt(0);
                if (textView != null) textView.setTextColor(getResources().getColor(R.color.colorWhite));
                switch (position) {
                    case 0: {
                        training.setTrainingIntensityLevel( TrainingTable.INTENSITY_LEVEL_BEGINNER);
                        return;
                    }
                    case 1: {
                        training.setTrainingIntensityLevel(TrainingTable.INTENSITY_LEVEL_INTERMEDIATE);
                        return;
                    }
                    case 2: {
                        training.setTrainingIntensityLevel(TrainingTable.INTENSITY_LEVEL_ADVANCED);
                        return;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
