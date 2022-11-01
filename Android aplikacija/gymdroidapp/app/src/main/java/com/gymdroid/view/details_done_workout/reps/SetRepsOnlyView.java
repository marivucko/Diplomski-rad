package com.gymdroid.view.details_done_workout.reps;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gymdroid.R;

public class SetRepsOnlyView extends LinearLayout {

    TextView numberOfRepsTextView;

    public SetRepsOnlyView(Activity activity, ViewGroup parent, int numberOfReps) {
        super(activity);

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_set_reps_only, parent, false);
        addView(linearLayout);

        numberOfRepsTextView = findViewById(R.id.numberOfRepsTextView);
        numberOfRepsTextView.setText(String.valueOf(numberOfReps));
    }

}