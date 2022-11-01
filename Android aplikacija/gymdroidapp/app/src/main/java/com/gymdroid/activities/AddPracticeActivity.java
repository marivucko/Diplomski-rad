package com.gymdroid.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.activities.base.WhiteActivity;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.domain.virtual.Practice;
import com.gymdroid.view.AddPracticeDoneSetView;
import com.gymdroid.view.AddPracticeDumbbellView;
import com.gymdroid.view.BarbellDetailsView;

import java.util.ArrayList;
import java.util.Date;

public class AddPracticeActivity extends WhiteActivity {

    private static final boolean SHOW_WEIGHT = true;
    public static final String MUSCLE_GROUP_ID = "MuscleGroupId";

    ArrayList<Workout> workoutArrayList;
    ArrayList<Practice> practiceArrayList;
    ArrayList<AddPracticeDoneSetView> addPracticeDoneSetViewArrayList;

    LinearLayout masterLayout;
    LinearLayout setLinearLayout;
    LinearLayout barbellLinearLayout;
    LinearLayout barbellCardLinearLayout;
    LinearLayout dumbbellLinearLayout;
    LinearLayout dumbbellCardLinearLayout;

    RadioButton todayDateRadioButton;
    RadioButton yesterdayDateRadioButton;
    RadioButton customDate;

    EditText dayEditText;
    EditText monthEditText;
    EditText yearEditText;

    TextView totalBarbellTextView;
    TextView totalDumbbellText;

    Button setSameSetsButton;
    Button addNewSetButton;
    Button removeLastSetButton;
    Button clearSetsButton;
    Button saveButton;
    Button setBarbellToFirstButton;
    Button setDumbbellToFirstButton;
    Button setBarbellSameWeightButton;
    Button setDumbbellSameWeightButton;

    Spinner workoutSpinner;

    int workoutId;
    int muscleGroupId;
    Workout selectedWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_practice);

        masterLayout = findViewById(R.id.masterLayout);
        saveButton = findViewById(R.id.saveButton);
        muscleGroupId = getIntent().getIntExtra(MUSCLE_GROUP_ID, EXPECTED_ID);

        if(muscleGroupId == EXPECTED_ID) {
            onBackPressed();
        }
        else {

            setUpWorkoutSpinner();
            setUpDateCard();
            setUpSetLinearLayout();
            setBarbellLinearLayout();
            //setDumbbellLinearLayout();
            dumbbellCardLinearLayout = findViewById(R.id.dumbbellCardLinearLayout);
            dumbbellCardLinearLayout.setVisibility(View.GONE);

            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!allServices.getFasterInputServiceWorkoutDetails().saveWorkoutDetailsDataChecker(activity,masterLayout,addPracticeDoneSetViewArrayList)) {
                        return;
                    }

                    ArrayList<DoneSet> doneSetArrayList = new ArrayList<>();
                    for (int i = 0; i < addPracticeDoneSetViewArrayList.size(); i++) {
                        int currentReps = addPracticeDoneSetViewArrayList.get(i).getReps();
                        double currentWeight = addPracticeDoneSetViewArrayList.get(i).getWeight();
                        long currentTime = addPracticeDoneSetViewArrayList.get(i).getTime();
                        DoneSet doneSet = new DoneSet();
                        doneSet.setDoneSetNumberOfReps(currentReps);
                        doneSet.setDoneSetWeight(currentWeight);
                        doneSet.setDoneSetTimeInMilliseconds((int) currentTime);
                        doneSetArrayList.add(doneSet);
                    }

                    Date date = allServices.getFasterInputServiceDatePicker().generateDate(
                            activity,masterLayout,
                            todayDateRadioButton, yesterdayDateRadioButton, customDate,
                            dayEditText, monthEditText, yearEditText,
                            activity.getResources().getString(R.string.future_time_workout),
                            true
                    );

                    if (date == null) {
                        return;
                    }

                    DoneWorkout doneWorkout = new DoneWorkout();
                    doneWorkout.setWorkoutId(workoutId);
                    doneWorkout.setDoneWorkoutDate(date);

                    allServices.getPracticeAddService().addPractice(activity, doneSetArrayList, doneWorkout);

                }
            });
        }

    }

    private void setUpWorkoutSpinner() {
        workoutArrayList = database.getUniqueWorkoutArrayList(muscleGroupId);
        selectedWorkout = workoutArrayList.get(0);
        practiceArrayList = database.getPracticeArrayList();
        workoutSpinner = findViewById(R.id.workoutSpinner);
        workoutSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView)parent.getChildAt(0);
                if (textView != null) textView.setTextColor(getResources().getColor(R.color.colorWhite));
                selectedWorkout = workoutArrayList.get(position);
                workoutId = selectedWorkout.getWorkoutId();
                if (selectedWorkout.getWorkoutNeedWeight() == 0) {
                    barbellCardLinearLayout.setVisibility(View.GONE);
                }
                else {
                    if (allServices.getDatabase().getUpgradableEquipmentArrayList().size() == 0)
                        barbellCardLinearLayout.setVisibility(View.GONE);
                    else

                        barbellCardLinearLayout.setVisibility(View.VISIBLE);
                }
                allServices.getFasterInputServiceWorkoutDetails().refreshWorkoutDetailsLinearLayout(activity, setLinearLayout, addPracticeDoneSetViewArrayList, selectedWorkout, SHOW_WEIGHT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fillWorkoutMuscle(workoutArrayList);
        workoutId = workoutArrayList.get(0).getWorkoutId();

    }

    private void fillWorkoutMuscle(ArrayList<Workout> workoutArrayList) {

        ArrayList<String> workoutArray = new ArrayList<>();
        for(int i = 0; i < workoutArrayList.size(); i++){
            workoutArray.add("" + workoutArrayList.get(i).getWorkoutName());
        }
        ArrayAdapter<String> workoutArrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, workoutArray);
        workoutSpinner.setAdapter(workoutArrayAdapter);

    }

    private void setUpDateCard() {
        todayDateRadioButton = findViewById(R.id.todayDateRadioButton);
        yesterdayDateRadioButton = findViewById(R.id.yesterdayDateRadioButton);
        customDate = findViewById(R.id.customDateRadioButton);
        dayEditText = findViewById(R.id.dayEditText);
        monthEditText = findViewById(R.id.monthEditText);
        yearEditText = findViewById(R.id.yearEditText);

        allServices.getFasterInputServiceDatePicker().setClickListeners(
                activity,
                todayDateRadioButton,yesterdayDateRadioButton,customDate,
                dayEditText,monthEditText,yearEditText
        );

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

    private void setDumbbellLinearLayout() {

        dumbbellCardLinearLayout = findViewById(R.id.dumbbellCardLinearLayout);
        dumbbellLinearLayout = findViewById(R.id.dumbbellLinearLayout);
        totalDumbbellText = findViewById(R.id.totalDumbbellText);

        ArrayList<Equipment> dumbbellEquipmentArrayList = database.getDumbbellEquipment();

        if(dumbbellEquipmentArrayList.size() > 0) {
            dumbbellLinearLayout.addView(new AddPracticeDumbbellView(activity,barbellLinearLayout,totalDumbbellText,dumbbellEquipmentArrayList));

            setDumbbellToFirstButton = findViewById(R.id.setDumbbellToFirstButton);
            setDumbbellToFirstButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double weight = Double.parseDouble(totalDumbbellText.getText().toString());
                    addPracticeDoneSetViewArrayList.get(0).setWeight(weight);
                }
            });

            setDumbbellSameWeightButton = findViewById(R.id.setDumbbellSameWeightButton);
            setDumbbellSameWeightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    double weight = Double.parseDouble(totalDumbbellText.getText().toString());
                    for(int i = 0; i < addPracticeDoneSetViewArrayList.size(); i++) {
                        addPracticeDoneSetViewArrayList.get(i).setWeight(weight);
                    }
                }
            });

        }
        else {
            dumbbellCardLinearLayout.setVisibility(View.GONE);
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

}
