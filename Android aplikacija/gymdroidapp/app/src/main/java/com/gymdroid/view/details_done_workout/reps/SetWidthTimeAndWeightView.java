package com.gymdroid.view.details_done_workout.reps;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.services.AllServices;

@SuppressLint("ViewConstructor")
public class SetWidthTimeAndWeightView extends LinearLayout {

    TextView numberOfRepsTextView;
    TextView timeTextView;
    TextView weightTextView;

    public SetWidthTimeAndWeightView(Activity activity, ViewGroup parent, int numberOfReps, int time, double weight) {
        super(activity);

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_set_width_time_and_weight, parent, false);
        addView(linearLayout);

        AllServices allServices = AllServices.getInstance(activity);

        numberOfRepsTextView = findViewById(R.id.numberOfRepsTextView);
        numberOfRepsTextView.setText(String.valueOf(numberOfReps));

        timeTextView = findViewById(R.id.timeTextView);
        timeTextView.setText(allServices.getCalendarService().millisecondsToMinuteSecondString(time));

        weightTextView = findViewById(R.id.weightTextView);
       // weightTextView.setText(String.valueOf(weight) + activity.getResources().getString(R.string._kg));
        weightTextView.setText(allServices.getStringService().weightFormat(weight));
    }

}
