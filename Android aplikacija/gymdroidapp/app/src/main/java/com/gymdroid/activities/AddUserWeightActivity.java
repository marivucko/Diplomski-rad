package com.gymdroid.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.gymdroid.R;
import com.gymdroid.activities.base.WhiteActivity;
import com.gymdroid.domain.beans.UserWeight;

import java.util.Date;

public class AddUserWeightActivity extends WhiteActivity {

    LinearLayout masterLayout;

    RadioButton todayDateRadioButton;
    RadioButton yesterdayDateRadioButton;
    RadioButton customDate;

    EditText weightNoteEditText;
    EditText weightEditText;
    EditText dayEditText;
    EditText monthEditText;
    EditText yearEditText;

    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_user_weight);

        masterLayout = findViewById(R.id.masterLayout);
        weightNoteEditText = findViewById(R.id.noteEditText);
        weightEditText = findViewById(R.id.weightEditText);
        dayEditText = findViewById(R.id.dayEditText);
        monthEditText = findViewById(R.id.monthEditText);
        yearEditText = findViewById(R.id.yearEditText);

        todayDateRadioButton = findViewById(R.id.todayDateRadioButton);
        yesterdayDateRadioButton = findViewById(R.id.yesterdayDateRadioButton);
        customDate = findViewById(R.id.customDateRadioButton);

        allServices.getFasterInputServiceDatePicker().setClickListeners(
                activity,
                todayDateRadioButton,yesterdayDateRadioButton,customDate,
                dayEditText,monthEditText,yearEditText
        );

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weight = weightEditText.getText().toString();
                if(allServices.getStringService().isStringEmpty(weight)) {
                    weightEditText.setError(activity.getResources().getString(R.string.fill_data_errors_weight_cant_be_empty));
                    return;
                }

                Date date = allServices.getFasterInputServiceDatePicker().generateDate(
                        activity, masterLayout,
                        todayDateRadioButton,yesterdayDateRadioButton,customDate,
                        dayEditText,monthEditText,yearEditText,
                        activity.getResources().getString(R.string.future_time_weight),
                        false
                );

                if(date == null) {
                    return;
                }

                String weightNote = weightNoteEditText.getText().toString();

                UserWeight userWeight = new UserWeight();
                userWeight.setWeight(Double.parseDouble(weight));
                userWeight.setWeightDate(date);
                userWeight.setWeightNote(weightNote);

                allServices.getUserWeightService().addUserWeight(activity, userWeight);
            }
        });

    }
}
