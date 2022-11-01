package com.gymdroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.gymdroid.R;
import com.gymdroid.services.AllServices;

@SuppressLint("ViewConstructor")
public class CardViewCreateTraining extends RelativeLayout {

    Button createButton;
    AllServices allServices;

    public CardViewCreateTraining(final Activity activity, final ViewGroup parent) {
        super(activity);

        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.card_view_create_training, parent, false);
        addView(relativeLayout);

        allServices = AllServices.getInstance(activity);

        createButton = findViewById(R.id.createButton);
        createButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getStartActivityService().startTrainingInformationActivity(activity);
            }
        });
    }

}
