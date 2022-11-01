package com.gymdroid.activities.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.activities.base.DialogActivity;

import java.util.Date;

public class UserWeightDetailsActivity extends DialogActivity {

    public static final String INDEX = "index";
    public static final int expectedIndex = -1;

    TextView dateTextView;
    TextView weightTextView;
    TextView notesShowTextView;
    TextView notesTextView;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_user_weight_details);
        enableClose();

        index = getIntent().getIntExtra(INDEX, expectedIndex);
        if(index == expectedIndex) {
            onBackPressed();
        }

        Date date = database.getUserWeightArrayList().get(index).getWeightDate();
        String dateString = " " + allServices.getCalendarService().dateToStringOnlyDateDMY(date);
        dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(dateString);

        String weight = " " + String.valueOf(database.getUserWeightArrayList().get(index).getWeight()) + getResources().getString(R.string._kg);
        weightTextView = findViewById(R.id.weightTextView);
        weightTextView.setText(weight);

        String notes = database.getUserWeightArrayList().get(index).getWeightNote();
        notesTextView = findViewById(R.id.notesTextView);
        if(allServices.getStringService().isStringEmpty(notes)) {
            notesShowTextView = findViewById(R.id.notesShowTextView);
            notesShowTextView.setVisibility(View.GONE);
            notesTextView.setVisibility(View.GONE);
        }
        else {
            notesTextView.setText(" " + notes);
        }

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allServices.getUserWeightService().deleteUserWeight(activity, index);
            }
        });

    }

}
