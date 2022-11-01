package com.gymdroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.gymdroid.R;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.services.AllServices;

@SuppressLint("ViewConstructor")
public class ListItemViewWorkout extends LinearLayout {

    AllServices allServices;
    TextView numerationTextView;
    TextView nameTextView;
    TextView countTextView;
    ImageView statusImageView;
    ImageButton detailsButton;
    LinearLayout linearLayout;
    int countDoneWorkouts;

    public ListItemViewWorkout(final Activity activity, final ViewGroup parent, final Workout workout, final int index) {
        super(activity);

        linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_workout_list_item, parent, false);
        addView(linearLayout);

        if(index % 2 == 0) {
            linearLayout.setBackgroundColor(Color.parseColor("#F3F0F6"));
        }

        allServices = AllServices.getInstance(activity);

        String numeration = String.valueOf(index + 1) + ".";
        numerationTextView = findViewById(R.id.numerationTextView);
        numerationTextView.setText(numeration);

        countDoneWorkouts = allServices.getDatabase().countDoneWorkouts(workout.getWorkoutId());
        countTextView = findViewById(R.id.countTextView);
        countTextView.setText(String.valueOf(countDoneWorkouts));

        nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText(workout.getWorkoutName());

        statusImageView = findViewById(R.id.statusImageView);
        if(workout.getWorkoutStatusIsApproved() == 1) {
            statusImageView.setImageDrawable(activity.getDrawable(R.drawable.ic_status_public));
        }
        else {
            statusImageView.setImageDrawable(activity.getDrawable(R.drawable.ic_status_private));
        }

        statusImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (workout.getWorkoutStatusIsApproved() == 0) {
                    allServices.getWorkoutService().deleteWorkout(activity, workout);
                }
            }
        });

        detailsButton = findViewById(R.id.detailsButton);
        detailsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (countDoneWorkouts > 0) {
                    allServices.getStartActivityService().startDetailsDoneWorkoutActivity(activity, workout.getWorkoutId());
                }
                else {
                    Snackbar.make(linearLayout,activity.getResources().getString(R.string.server_is_empty_no_record_for_this_workout),Snackbar.LENGTH_SHORT).show();
                }
            }
        });


    }
}
