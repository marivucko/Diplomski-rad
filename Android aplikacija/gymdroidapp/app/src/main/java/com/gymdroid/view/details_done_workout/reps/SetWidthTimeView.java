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
public class SetWidthTimeView extends LinearLayout {

    TextView numberOfRepsTextView;
    TextView timeTextView;

    public SetWidthTimeView(Activity activity, ViewGroup parent, int numberOfReps, int time) {
        super(activity);

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_set_width_time, parent, false);
        addView(linearLayout);

        AllServices allServices = AllServices.getInstance(activity);

        numberOfRepsTextView = findViewById(R.id.numberOfRepsTextView);
        numberOfRepsTextView.setText(String.valueOf(numberOfReps));

        timeTextView = findViewById(R.id.timeTextView);
        timeTextView.setText(String.valueOf(allServices.getCalendarService().millisecondsToMinuteSecondString(time)));
    }

}
