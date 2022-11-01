package com.gymdroid.fragments.training;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gymdroid.R;
import com.gymdroid.activities.TrainingActivity;

public class TrainingPauseBetweenWorkoutsFragment extends Fragment {

    private FrameLayout masterLayout;
    private TrainingActivity activity;
    private TextView timerTextView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_pause_between_workouts, container, false);
        activity = (TrainingActivity) getActivity();
        timerTextView = view.findViewById(R.id.timerTextView);
        long pauseDuration = activity.getTraining().getDurationOfPauseBetweenWorkouts();
        activity.getAllServices().getTrainingPauseService().startTrainingPause(activity,timerTextView,pauseDuration);

        masterLayout = view.findViewById(R.id.masterLayout);
        masterLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                activity.getAllServices().getTrainingPauseService().stopTrainingPause(activity);
                return true;
            }
        });

        return view;
    }

}