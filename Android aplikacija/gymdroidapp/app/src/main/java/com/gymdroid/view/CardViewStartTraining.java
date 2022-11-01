package com.gymdroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.services.AllServices;

@SuppressLint("ViewConstructor")
public class CardViewStartTraining extends RelativeLayout {

    ImageView infoImageView;
    ImageButton startButton;
    ImageButton deleteButton;
    TextView trainingNameTextView;
    TextView trainingDescriptionTextView;

    public CardViewStartTraining(final Activity activity, final ViewGroup parent, final Training training) {
        super(activity);

        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.card_start_training_view, parent, false);
        addView(relativeLayout);

        trainingNameTextView = findViewById(R.id.trainingNameTextView);
        trainingNameTextView.setText(training.getTrainingName());

        trainingDescriptionTextView = findViewById(R.id.trainingDescriptionTextView);
        trainingDescriptionTextView.setText(training.getTrainingDescription());

        infoImageView = findViewById(R.id.infoImageView);
        infoImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AllServices.getInstance(activity).getStartActivityService().startInfoDoneTrainingActivity(activity, training.getTrainingId());
            }
        });

        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AllServices.getInstance(activity).getStartActivityService().startTrainingActivity(activity,training.getTrainingId());
            }
        });

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AllServices.getInstance(activity).getTrainingService().deleteTraining(activity, training);
            }
        });



    }

}
