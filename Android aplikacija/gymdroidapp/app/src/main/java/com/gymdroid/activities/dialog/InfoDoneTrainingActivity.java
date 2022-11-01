package com.gymdroid.activities.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.activities.base.DialogActivity;
import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.view.ListItemViewDoneTraining;

import java.util.ArrayList;

public class InfoDoneTrainingActivity extends DialogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_info_done_training);

        LinearLayout doneTrainingLinearLayout = findViewById(R.id.doneTrainingLinearLayout);
        TextView noteTextView = findViewById(R.id.noteTextView);

        Intent intent = activity.getIntent();
        int trainingId = intent.getIntExtra("TRAINING_ID", -1);
        if (trainingId != -1) {

            Training training = allServices.getDatabase().getTraining(trainingId);

            TextView trainingDescription = findViewById(R.id.trainingDescription);
            trainingDescription.setText(allServices.getDatabase().getTrainingInfo(trainingId));

            ArrayList<DoneTraining> doneTrainingArrayList = allServices.getDatabase().getDoneTrainingByTrainingId(trainingId);

            if (doneTrainingArrayList.size() > 0) {
                noteTextView.setVisibility(View.GONE);
                doneTrainingLinearLayout.addView(new ListItemViewDoneTraining(this, doneTrainingLinearLayout, "Date", "Time", -1 ));
                for (int i = 0; i < doneTrainingArrayList.size(); i++) {
                    DoneTraining doneTraining = doneTrainingArrayList.get(i);
                    String date = allServices.getCalendarService().dateToStringOnlyDateDMY(doneTraining.getDoneTrainingAt());
                    String time = allServices.getCalendarService().dateToTime(doneTraining.getDoneTrainingAt());
                    doneTrainingLinearLayout.addView(new ListItemViewDoneTraining(this, doneTrainingLinearLayout, date, time, i));
                }
            }
        }
        enableClose();
    }
}
