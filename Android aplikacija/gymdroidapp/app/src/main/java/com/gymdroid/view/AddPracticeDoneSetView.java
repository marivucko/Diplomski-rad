package com.gymdroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.services.AllServices;

@SuppressLint("ViewConstructor")
public class AddPracticeDoneSetView extends LinearLayout {

    Activity activity;
    AllServices allServices;

    TextView setTextView;
    TextView repsTextView;
    TextView timeTextView;
    TextView weightTextView;

    EditText repsEditText;
    EditText timeEditText;
    EditText weightEditText;

    Workout workout;

    int setNumber;

    public AddPracticeDoneSetView(Activity activity, ViewGroup parent, Workout workout, int setNumber, boolean showWeight) {
        super(activity);
        this.setNumber = setNumber;
        this.activity = activity;
        this.allServices = AllServices.getInstance(activity);
        this.workout = workout;

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_add_practice_done_set, parent, false);
        addView(linearLayout);

        setTextView = findViewById(R.id.setTextView);
        setTextView.setText("Set " + setNumber);

        repsTextView = findViewById(R.id.repsTextView);
        repsEditText = findViewById(R.id.repsEditText);

        weightTextView = findViewById(R.id.weightTextView);
        weightEditText = findViewById(R.id.weightEditText);
        if(workout.getWorkoutNeedWeight() == 0 || !showWeight) {
            weightTextView.setVisibility(GONE);
            weightEditText.setVisibility(GONE);
        }

        timeTextView = findViewById(R.id.timeTextView);
        timeEditText = findViewById(R.id.timeEditText);
        allServices.getFasterInputServiceTimePicker().setOnClickListeners(activity,timeEditText);
        if(workout.getWorkoutNeedTime() == 0) {
            timeTextView.setVisibility(GONE);
            timeEditText.setVisibility(GONE);
        }
    }

    public void clearData() {
        weightEditText.setText("0.0");
        repsEditText.setText("0");
        allServices.getFasterInputServiceTimePicker().setTimeEditTextTime(timeEditText,0);
    }

    public double getWeight() {
        if (workout.getWorkoutNeedWeight() == 0)
            return 0.0;
        else
            return getValue(weightEditText);
    }

    public void setWeight(double weight) {
        weightEditText.setText(String.valueOf(weight));
    }

    public int getReps() {
        return (int) getValue(repsEditText);
    }

    public void setReps(int reps) {
        repsEditText.setText(String.valueOf(reps));
    }

    public long getTime() {
        if (workout.getWorkoutNeedTime() == 0) {
            return 0;
        }
        long durationOfSet = allServices.getFasterInputServiceTimePicker().getTimeFromEditText(timeEditText);
        if(durationOfSet == -1) {
            Snackbar.make(setTextView,activity.getResources().getString(R.string.fill_data_errors_pause_all_times_must_bew_longer),Snackbar.LENGTH_SHORT).show();
        }
        return allServices.getFasterInputServiceTimePicker().getTimeFromEditText(timeEditText);
    }

    public void setTime(long time) {
        allServices.getFasterInputServiceTimePicker().setTimeEditTextTime(timeEditText,time);
    }

    private double getValue(EditText editText) {
        String weightString = editText.getText().toString();
        if(AllServices.getInstance(activity).getStringService().isStringEmpty(weightString)){
            editText.setError(activity.getResources().getString(R.string.fill_data_errors_weight_cant_be_empty));
            return -1;
        }
        try {
            return Double.parseDouble(weightString);
        } catch (Exception e) {
            editText.setError(activity.getResources().getString(R.string.fill_data_errors_not_valid_value));
            return -1;
        }
    }

}
