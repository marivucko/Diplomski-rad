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
public class SetWidthWeightView extends LinearLayout {

    TextView numberOfRepsTextView;
    TextView weightTextView;

    public SetWidthWeightView(Activity activity, ViewGroup parent, int numberOfReps, double weight) {
        super(activity);

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_set_width_weight, parent, false);
        addView(linearLayout);

        numberOfRepsTextView = findViewById(R.id.numberOfRepsTextView);
        numberOfRepsTextView.setText(String.valueOf(numberOfReps));

        weightTextView = findViewById(R.id.weightTextView);
        weightTextView.setText( AllServices.getInstance(activity).getStringService().weightFormat(weight));
    }

}
