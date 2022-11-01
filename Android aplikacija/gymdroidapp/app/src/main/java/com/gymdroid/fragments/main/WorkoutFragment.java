package com.gymdroid.fragments.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.gymdroid.R;
import com.gymdroid.domain.beans.Workout;
import com.gymdroid.services.AllServices;
import com.gymdroid.view.ListItemViewWorkout;

import java.util.ArrayList;

public class WorkoutFragment extends Fragment {

    private ImageView infoImageView;
    private Activity activity;
    private AllServices allServices;
    private RelativeLayout masterLayout;
    private LinearLayout workoutListLinearLayout;

    private ArrayList<Workout> workoutArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate( R.layout.fragment_workout, container, false);

        activity = (Activity) rootView.getContext();
        allServices = AllServices.getInstance(activity);
        masterLayout = rootView.findViewById(R.id.masterLayout);

        Button addWeightButton = rootView.findViewById(R.id.addWeightButton);
        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getStartActivityService().startCreateNewWorkoutActivity(activity, masterLayout);
            }
        });

        infoImageView = rootView.findViewById(R.id.infoImageView);
        infoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getStartActivityService().startWorkoutHelpActivity(activity);
            }
        });

        workoutListLinearLayout = rootView.findViewById(R.id.workoutListLinearLayout);
        workoutArrayList = allServices.getDatabase().getWorkoutArrayList();

        for(int i = 0; i < workoutArrayList.size(); i++) {
            workoutListLinearLayout.addView(new ListItemViewWorkout(activity,workoutListLinearLayout,workoutArrayList.get(i), i ));
        }
        return rootView;
    }

}
