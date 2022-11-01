package com.gymdroid.activities.training;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.activities.base.WhiteActivity;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.MuscleGroup;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.view.AddPracticeDoneSetView;
import com.gymdroid.view.BarbellDetailsView;

import java.util.ArrayList;

public class TrainingAddWorkoutActivity extends WhiteActivity {

    private static final boolean SHOW_WEIGHT = true;

    ArrayAdapter<String> workoutArrayAdapter;

    Spinner muscleGroupSpinner;
    Spinner workoutSpinner;
    EditText timerEditText;

    RelativeLayout masterLayout;
    LinearLayout setLinearLayout;

    Button setSameSetsButton;
    Button addNewSetButton;
    Button removeLastSetButton;
    Button clearSetsButton;
    Button saveButton;
    boolean selected = true;

    LinearLayout barbellLinearLayout;
    LinearLayout barbellCardLinearLayout;
    TextView totalBarbellTextView;
    Button setBarbellToFirstButton;
    Button setBarbellSameWeightButton;

    ArrayList<TrainingSet> trainingSetArrayList = new ArrayList<>();
    TrainingWorkout trainingWorkout = new TrainingWorkout();
    MuscleGroup selectedMuscleGroup;
    ArrayList<MuscleGroup> muscleGroupArrayList;
    Workout selectedWorkout = null;
    ArrayList<Workout> workoutArrayList;
    ArrayList<AddPracticeDoneSetView> addPracticeDoneSetViewArrayList;
    private boolean isNotOkToPressBackButton = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_training_add_workout);

        masterLayout = findViewById(R.id.masterLayout);

        setUpMuscleGroupSpinner();
        setUpSpinner();
        setUpSetLinearLayout();
        setBarbellLinearLayout();

        timerEditText = findViewById(R.id.timerEditText);
        allServices.getFasterInputServiceTimePicker().setOnClickListeners(activity,timerEditText);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!allServices.getFasterInputServiceWorkoutDetails().saveWorkoutDetailsDataChecker(activity,masterLayout,addPracticeDoneSetViewArrayList)) {
                    return;
                }

                String durationOfPauseBetweenSetsString = timerEditText.getText().toString();
                if(allServices.getStringService().isStringEmpty(durationOfPauseBetweenSetsString)) {
                    timerEditText.setError(getResources().getString(R.string.fill_data_errors_pause_between_sets_is_required));
                    return;
                }

                long durationOfPauseBetweenSets = allServices.getCalendarService().timeHoursMinutesSecondsToMilliSeconds(durationOfPauseBetweenSetsString);
                if(durationOfPauseBetweenSets <= 0) {
                    timerEditText.setError(getResources().getString(R.string.fill_data_errors_pause_between_sets_is_required));
                    return;
                }

                if (!selected) {
                    Snackbar.make(masterLayout,getResources().getString(R.string.snack_bar_messages_there_is_no_workout_for_this_muscle_group),Snackbar.LENGTH_LONG).show();
                    return;
                }

                for(int i = 0; i < addPracticeDoneSetViewArrayList.size(); i++) {
                    TrainingSet trainingSet = new TrainingSet();
                    trainingSet.setTrainingSetNumberOfReps(addPracticeDoneSetViewArrayList.get(i).getReps());
                    trainingSet.setTrainingSetTime(addPracticeDoneSetViewArrayList.get(i).getTime());
                    trainingSet.setTrainingSetWeight(addPracticeDoneSetViewArrayList.get(i).getWeight());
                    trainingSetArrayList.add(trainingSet);
                }

                trainingWorkout.setDurationOfPauseBetweenSets(durationOfPauseBetweenSets);
                trainingWorkout.setOrderNumber(allServices.getCreateTrainingService().generateTrainingWorkoutOrderNumber());
                allServices.getCreateTrainingService().addData(trainingWorkout,trainingSetArrayList);
                allServices.getStartActivityService().startTrainingAddFinishActivity(activity);
                finish();
            }
        });
    }

    private void setUpSetLinearLayout() {
        setSameSetsButton = findViewById(R.id.setSameSetsButton);
        setLinearLayout = findViewById(R.id.setLinearLayout);
        addNewSetButton = findViewById(R.id.addNewSetButton);
        removeLastSetButton = findViewById(R.id.removeLastSetButton);
        clearSetsButton = findViewById(R.id.clearSetsButton);
        addPracticeDoneSetViewArrayList = new ArrayList<>();
        setUpSetLinearLayoutService();
    }

    private void setUpSpinner() {
        workoutArrayList = database.getWorkoutArrayList();
        if (workoutArrayList.size() > 0)
            selectedWorkout = workoutArrayList.get(0);
        workoutSpinner = findViewById(R.id.workoutSpinner);
        new Handler().postDelayed( new Runnable() {
            public void run() {
                workoutSpinner.setSelection(0);
            }
        }, 100);
        workoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) parent.getChildAt(0);
                if (textView != null) textView.setTextColor(getResources().getColor(R.color.colorWhite));
                selectedWorkout = workoutArrayList.get(position);
                trainingWorkout.setWorkoutId(selectedWorkout.getWorkoutId());
                allServices.getFasterInputServiceWorkoutDetails().refreshWorkoutDetailsLinearLayoutEmpty(activity, setLinearLayout, addPracticeDoneSetViewArrayList, selectedWorkout, SHOW_WEIGHT);
                if (selectedWorkout.getWorkoutNeedWeight() == 0) {
                    barbellCardLinearLayout.setVisibility(View.GONE);
                }
                else {
                    if (allServices.getDatabase().getUpgradableEquipmentArrayList().size() == 0)
                        barbellCardLinearLayout.setVisibility(View.GONE);
                    else
                        barbellCardLinearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fillWorkoutMuscle(ArrayList<Workout> workoutArrayList) {
        this.workoutArrayList = workoutArrayList;
        ArrayList<String> workoutArray = new ArrayList<>();
        for(int i = 0; i < workoutArrayList.size(); i++){
            workoutArray.add("" + workoutArrayList.get(i).getWorkoutName());
        }
        if (workoutArrayAdapter != null) {
            workoutArrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, workoutArray);
            workoutArrayAdapter.notifyDataSetChanged();
            workoutSpinner.setAdapter(workoutArrayAdapter);
        }
        else {
            workoutArrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, workoutArray);
            workoutSpinner.setAdapter(workoutArrayAdapter);
        }
        if (workoutArray.size() > 0) {
            workoutSpinner.setSelection(0, false);
            selectedWorkout = workoutArrayList.get(0);
            selected = true;
        }
        else {
            selectedWorkout = null;
            selected = false;
            while (addPracticeDoneSetViewArrayList.size() >= 1) {
                int lastIndex = addPracticeDoneSetViewArrayList.size() - 1;
                setLinearLayout.removeView(addPracticeDoneSetViewArrayList.get(lastIndex));
                addPracticeDoneSetViewArrayList.remove(addPracticeDoneSetViewArrayList.get(lastIndex));
            }
        }
    }


    private void setUpMuscleGroupSpinner() {
        muscleGroupArrayList = database.getMuscleGroupArrayList();
        selectedMuscleGroup = muscleGroupArrayList.get(0);// trebalo bi all
        muscleGroupSpinner = findViewById(R.id.muscleGroupSpinner);
        muscleGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView)parent.getChildAt(0);
                if (textView != null) textView.setTextColor(getResources().getColor(R.color.colorWhite));
                if (position == 0) {
                    workoutArrayList = database.getWorkoutArrayList();
                    fillWorkoutMuscle(workoutArrayList);
                }
                else {
                    selectedMuscleGroup = muscleGroupArrayList.get(position - 1);
                    workoutArrayList = allServices.getDatabase().getUniqueWorkoutArrayList(selectedMuscleGroup.getMuscleGroupId());
                    fillWorkoutMuscle(workoutArrayList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fillMuscleGroup(muscleGroupArrayList);
    }

    private void fillMuscleGroup(ArrayList<MuscleGroup> muscleGroupArrayList) {
        ArrayList<String> muscleGroupArray = new ArrayList<>();
        muscleGroupArray.add("All Muscles");
        for(int i = 0; i < muscleGroupArrayList.size(); i++){
            muscleGroupArray.add("" + muscleGroupArrayList.get(i).getMuscleGroupName());
        }
        ArrayAdapter<String> muscleGroupArrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, muscleGroupArray);
        muscleGroupSpinner.setAdapter(muscleGroupArrayAdapter);
        muscleGroupSpinner.setSelection(0);
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

    /////////////////////////////////// services

    public void setUpSetLinearLayoutService() {
        setSameSetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double weight = addPracticeDoneSetViewArrayList.get(0).getWeight();
                int reps = addPracticeDoneSetViewArrayList.get(0).getReps();
                long time = addPracticeDoneSetViewArrayList.get(0).getTime();
                if(weight != -1) {
                    for (int i = 1; i < addPracticeDoneSetViewArrayList.size(); i++) {
                        addPracticeDoneSetViewArrayList.get(i).setWeight(weight);
                    }
                }
                if(reps != -1) {
                    for (int i = 1; i < addPracticeDoneSetViewArrayList.size(); i++) {
                        addPracticeDoneSetViewArrayList.get(i).setReps(reps);
                    }
                }
                if(time != -1) {
                    for (int i = 1; i < addPracticeDoneSetViewArrayList.size(); i++) {
                        addPracticeDoneSetViewArrayList.get(i).setTime(time);
                    }
                }
            }
        });

        addNewSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addPracticeDoneSetViewArrayList.size() < 6) {
                    addPracticeDoneSetViewArrayList.add(new AddPracticeDoneSetView(activity, setLinearLayout, selectedWorkout, addPracticeDoneSetViewArrayList.size() + 1, SHOW_WEIGHT));
                    setLinearLayout.addView(addPracticeDoneSetViewArrayList.get(addPracticeDoneSetViewArrayList.size() - 1));
                }
                else {
                    Snackbar.make(masterLayout,activity.getResources().getString(R.string.faster_input_service_snack_bar_max_6_sets),Snackbar.LENGTH_LONG).show();
                }
            }
        });

        removeLastSetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(addPracticeDoneSetViewArrayList.size() > 1) {
                    int lastIndex = addPracticeDoneSetViewArrayList.size() - 1;
                    setLinearLayout.removeView(addPracticeDoneSetViewArrayList.get(lastIndex));
                    addPracticeDoneSetViewArrayList.remove(addPracticeDoneSetViewArrayList.get(lastIndex));
                }
                else {
                    Snackbar.make(masterLayout,activity.getResources().getString(R.string.faster_input_service_snack_bar_least_one_set),Snackbar.LENGTH_LONG).show();
                }
            }
        });

        clearSetsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 0; i < addPracticeDoneSetViewArrayList.size(); i++) {
                    addPracticeDoneSetViewArrayList.get(i).clearData();
                }
            }
        });
    }

    private void setBarbellLinearLayout() {

        barbellCardLinearLayout = findViewById(R.id.barbellCardLinearLayout);
        barbellLinearLayout = findViewById(R.id.barbellLinearLayout);
        totalBarbellTextView = findViewById(R.id.totalBarbellTextView);

        ArrayList<Equipment> upgradableEquipmentArrayList = database.getUpgradableEquipmentArrayList();

        if(upgradableEquipmentArrayList.size() > 0) {
            for(int i = 0; i < upgradableEquipmentArrayList.size(); i++) {
                barbellLinearLayout.addView(new BarbellDetailsView(activity,barbellLinearLayout,totalBarbellTextView,upgradableEquipmentArrayList.get(i)));
            }

            setBarbellToFirstButton = findViewById(R.id.setBarbellToFirstButton);
            setBarbellToFirstButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double weight = Double.parseDouble(totalBarbellTextView.getText().toString());
                    addPracticeDoneSetViewArrayList.get(0).setWeight(weight);
                }
            });

            setBarbellSameWeightButton = findViewById(R.id.setBarbellSameWeightButton);
            setBarbellSameWeightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double weight = Double.parseDouble(totalBarbellTextView.getText().toString());
                    for(int i = 0; i < addPracticeDoneSetViewArrayList.size(); i++) {
                        addPracticeDoneSetViewArrayList.get(i).setWeight(weight);
                    }
                }
            });
        }
        else {
            barbellCardLinearLayout.setVisibility(View.GONE);
        }

    }

}

