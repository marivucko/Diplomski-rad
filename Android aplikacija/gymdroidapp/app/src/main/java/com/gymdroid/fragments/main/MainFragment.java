package com.gymdroid.fragments.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.gymdroid.R;
import com.gymdroid.dao.Database;
import com.gymdroid.services.AllServices;
import com.gymdroid.view.CardViewCreateTraining;
import com.gymdroid.view.CardViewMuscleGroup;
import com.gymdroid.view.CardViewStartTraining;

public class MainFragment extends Fragment {

    private LinearLayout muscleGroupLayout;
    private LinearLayout trainingLayout;
    private Activity activity;
    private AllServices allServices;
    private Database database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_main, container, false);

        activity = (Activity) rootView.getContext();
        allServices = AllServices.getInstance(activity);
        database = allServices.getDatabase();

        muscleGroupLayout = rootView.findViewById(R.id.muscleGroupLayout);
        for(int i = 0; i < database.getMuscleGroupArrayList().size(); i++) {
            muscleGroupLayout.addView(new CardViewMuscleGroup(activity,muscleGroupLayout,database.getMuscleGroupArrayList().get(i)));
        }

        trainingLayout = rootView.findViewById(R.id.trainingLayout);
        trainingLayout.addView(new CardViewCreateTraining(activity,trainingLayout));

        for(int i = 0; i < database.getTrainingArrayList().size(); i++) {
            trainingLayout.addView(new CardViewStartTraining(activity, trainingLayout, database.getTrainingArrayList().get(i)));
        }

        Button logout = rootView.findViewById(R.id.logout);
        logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allServices.getConfigurationService().clearUser();

                allServices.getStartActivityService().startSplashScreenActivity(activity);
            }
        });

        return rootView;
    }

}
