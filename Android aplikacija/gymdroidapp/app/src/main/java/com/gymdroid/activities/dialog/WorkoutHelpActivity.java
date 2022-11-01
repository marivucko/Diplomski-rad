package com.gymdroid.activities.dialog;

import android.os.Bundle;

import com.gymdroid.R;
import com.gymdroid.activities.base.DialogActivity;

public class WorkoutHelpActivity extends DialogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_workout_help);
        enableClose();
    }
}
