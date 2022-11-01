package com.gymdroid.fragments.training;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.gymdroid.R;
import com.gymdroid.activities.TrainingActivity;
import com.gymdroid.services.AllServices;

public class TrainingFinishFragment extends Fragment {

    private TrainingActivity activity;
    private AllServices allServices;
    private Button saveButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_training_finish, container, false);

        activity = (TrainingActivity) getActivity();
        allServices = AllServices.getInstance(activity);

        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getPracticeAddService().addMultiplePractices(
                        activity,
                        activity.getCurrentlyDoneSetArrayList(),
                        activity.getCurrentlyDoneWorkoutArrayList(),
                        activity.getDoneSetBreakingPoints(),
                        activity.getTraining()
                );
            }
        });

        return view;
    }

}