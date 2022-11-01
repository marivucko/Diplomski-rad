package com.gymdroid.view.details_done_workout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.services.AllServices;
import com.gymdroid.view.details_done_workout.reps.SetWidthWeightView;

import java.util.ArrayList;

@SuppressLint("ViewConstructor")
public class DetailsDoneWorkoutRepsWithWeight extends LinearLayout {

    AllServices allServices;
    ArrayList<DoneSet> doneSetArrayList;

    LinearLayout doneRepsLinearLayout;
    TextView numerationTextView;
    TextView dateTextView;

    public DetailsDoneWorkoutRepsWithWeight(final Activity activity, ViewGroup parent, DoneWorkout doneWorkout, int numeration) {
        super(activity);

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_details_done_workout_reps_with_weight, parent, false);
        addView(linearLayout);

        if(numeration % 2 == 0) {
            linearLayout.setBackgroundColor(Color.parseColor("#F3F0F6"));
        }

        allServices = AllServices.getInstance(activity);

        numerationTextView = findViewById(R.id.numerationTextView);
        numerationTextView.setText(String.valueOf(numeration));

        doneSetArrayList = allServices.getDatabase().getDoneSetArrayList(doneWorkout.getDoneWorkoutId());
        doneRepsLinearLayout = findViewById(R.id.doneRepsLinearLayout);
        for(int i = 0; i < doneSetArrayList.size(); i++) {
            int numberOfReps = doneSetArrayList.get(i).getDoneSetNumberOfReps();
            double weight = doneSetArrayList.get(i).getDoneSetWeight();
            doneRepsLinearLayout.addView(new SetWidthWeightView(activity,parent,numberOfReps,weight));
        }

        dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(allServices.getCalendarService().dateToStringOnlyDateDMY(doneWorkout.getDoneWorkoutDate()));

    }

}