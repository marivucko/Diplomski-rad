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
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.services.AllServices;
import com.gymdroid.view.ListItemViewWeight;

import java.util.ArrayList;

public class WeightFragment extends Fragment {

    private LinearLayout weightListLinearLayout;
    private Activity activity;
    private AllServices allServices;
    private ArrayList<UserWeight> userWeightArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_weight, container, false);

        activity = (Activity) rootView.getContext();
        allServices = AllServices.getInstance(activity);

        Button addWeightButton = rootView.findViewById(R.id.addWeightButton);
        addWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getStartActivityService().startAddUserWeightActivity(activity);
            }
        });

        weightListLinearLayout = rootView.findViewById(R.id.weightListLinearLayout);
        userWeightArrayList = allServices.getDatabase().getUserWeightArrayList();

        for(int i = 0; i < userWeightArrayList.size(); i++) {
            weightListLinearLayout.addView(new ListItemViewWeight(activity,weightListLinearLayout,userWeightArrayList.get(i), i ));
        }

        return rootView;
    }

}
