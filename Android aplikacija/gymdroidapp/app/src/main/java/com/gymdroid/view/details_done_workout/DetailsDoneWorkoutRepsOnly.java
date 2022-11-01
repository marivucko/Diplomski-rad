package com.gymdroid.view.details_done_workout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.services.AllServices;
import com.gymdroid.view.details_done_workout.reps.SetRepsOnlyView;

import java.util.ArrayList;

@SuppressLint("ViewConstructor")
public class DetailsDoneWorkoutRepsOnly extends LinearLayout {

    AllServices allServices;
    ArrayList<DoneSet> doneSetArrayList;

    LinearLayout doneRepsLinearLayout;
    TextView numerationTextView;
    TextView dateTextView;

    public DetailsDoneWorkoutRepsOnly(final Activity activity, ViewGroup parent, DoneWorkout doneWorkout, int numeration) {
        super(activity);

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_details_done_workout_reps_only, parent, false);
        addView(linearLayout);

        allServices = AllServices.getInstance(activity);

        numerationTextView = findViewById(R.id.numerationTextView);
        numerationTextView.setText(String.valueOf(numeration));

        doneSetArrayList = allServices.getDatabase().getDoneSetArrayList(doneWorkout.getDoneWorkoutId());
        doneRepsLinearLayout = findViewById(R.id.doneRepsLinearLayout);
        for(int i = 0; i < doneSetArrayList.size(); i++) {
            int numberOfReps = doneSetArrayList.get(i).getDoneSetNumberOfReps();
            doneRepsLinearLayout.addView(new SetRepsOnlyView(activity,parent,numberOfReps));
        }

        dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(allServices.getCalendarService().dateToStringOnlyDateDMY(doneWorkout.getDoneWorkoutDate()));

    }
    
}
