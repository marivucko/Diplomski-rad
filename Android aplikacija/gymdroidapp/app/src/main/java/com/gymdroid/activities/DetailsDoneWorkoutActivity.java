package com.gymdroid.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.activities.base.SlideInActivity;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.view.details_done_workout.DetailsDoneWorkoutRepsOnly;
import com.gymdroid.view.details_done_workout.DetailsDoneWorkoutRepsWithTime;
import com.gymdroid.view.details_done_workout.DetailsDoneWorkoutRepsWithTimeAndWeight;
import com.gymdroid.view.details_done_workout.DetailsDoneWorkoutRepsWithWeight;

import java.util.ArrayList;

public class DetailsDoneWorkoutActivity extends SlideInActivity {

    public static final String WORKOUT_ID = "WorkoutId";

    int workoutId;
    Workout workout;

    ArrayList<DoneWorkout> doneWorkoutArrayList;
    LinearLayout doneWorkoutListLinearLayout;
    TextView tableTitle;
    TextView setDetailsTextView;
    ImageView infoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_details_done_workout);

        workoutId = getIntent().getIntExtra(WORKOUT_ID,-1);
        workout = database.getWorkout(workoutId);

        if(workout == null) {
            onBackPressed();
        }
        else {
            doneWorkoutArrayList = database.getDoneWorkoutArrayList(workoutId);

            tableTitle = findViewById(R.id.tableTitle);
            tableTitle.setText(workout.getWorkoutName());

            infoImageView = findViewById(R.id.infoImageView);
            infoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    allServices.getStartActivityService().startInfoDoneWorkoutActivity(activity,workoutId);
                }
            });

            setTextOnSetDetailsTextView();
            fillTable();
        }
    }

    private void setTextOnSetDetailsTextView() {
        setDetailsTextView = findViewById(R.id.setDetailsTextView);
        if(workout.getWorkoutNeedWeight() == 0 && workout.getWorkoutNeedTime() == 0) {
            setDetailsTextView.setText(activity.getResources().getString(R.string.table_info_reps));
            return;
        }
        if(workout.getWorkoutNeedWeight() == 0 && workout.getWorkoutNeedTime() == 1) {
            setDetailsTextView.setText(activity.getResources().getString(R.string.table_info_reps_time));
            return;
        }
        if(workout.getWorkoutNeedWeight() == 1 && workout.getWorkoutNeedTime() == 0) {
            setDetailsTextView.setText(activity.getResources().getString(R.string.table_info_reps_weight));
            return;
        }
        if(workout.getWorkoutNeedWeight() == 1 && workout.getWorkoutNeedTime() == 1) {
            setDetailsTextView.setText(activity.getResources().getString(R.string.table_info_reps_weight_time));
        }
    }

    private void fillTable() {
        doneWorkoutListLinearLayout = findViewById(R.id.doneWorkoutListLinearLayout);
        if(workout.getWorkoutNeedWeight() == 0 && workout.getWorkoutNeedTime() == 0) {
            for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
                int numeration = i + 1;
                DetailsDoneWorkoutRepsOnly detailsDoneWorkoutView = new DetailsDoneWorkoutRepsOnly(activity, doneWorkoutListLinearLayout, doneWorkoutArrayList.get(i), numeration);
                doneWorkoutListLinearLayout.addView(detailsDoneWorkoutView);
            }
            return;
        }
        if(workout.getWorkoutNeedWeight() == 0 && workout.getWorkoutNeedTime() == 1) {
            for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
                int numeration = i + 1;
                DetailsDoneWorkoutRepsWithTime doneWorkoutRepsWithTime = new DetailsDoneWorkoutRepsWithTime(activity, doneWorkoutListLinearLayout, doneWorkoutArrayList.get(i), numeration);
                doneWorkoutListLinearLayout.addView(doneWorkoutRepsWithTime);
            }
            return;
        }
        if(workout.getWorkoutNeedWeight() == 1 && workout.getWorkoutNeedTime() == 0) {
            for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
                int numeration = i + 1;
                DetailsDoneWorkoutRepsWithWeight doneWorkoutRepsWithWeight = new DetailsDoneWorkoutRepsWithWeight(activity, doneWorkoutListLinearLayout, doneWorkoutArrayList.get(i), numeration);
                doneWorkoutListLinearLayout.addView(doneWorkoutRepsWithWeight);
            }
            return;
        }
        if(workout.getWorkoutNeedWeight() == 1 && workout.getWorkoutNeedTime() == 1) {
            for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
                int numeration = i + 1;
                DetailsDoneWorkoutRepsWithTimeAndWeight detailsDoneWorkoutRepsWithTimeAndWeight = new DetailsDoneWorkoutRepsWithTimeAndWeight(activity, doneWorkoutListLinearLayout, doneWorkoutArrayList.get(i), numeration);
                doneWorkoutListLinearLayout.addView(detailsDoneWorkoutRepsWithTimeAndWeight);
            }
        }
    }

}
