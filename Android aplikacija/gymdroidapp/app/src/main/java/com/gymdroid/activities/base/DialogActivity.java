package com.gymdroid.activities.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.gymdroid.R;


@SuppressLint("Registered")
public class DialogActivity extends FloatingBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allServices.getSimpleDesignService().setStatusAndNavigationBarFloating(activity);
        overridePendingTransition( R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.fade_out, R.anim.fade_out);
    }

    /// this activity needs to have this elements in .xml file
    protected void enableClose() {
        RelativeLayout masterLayout = findViewById(R.id.masterLayout);
        masterLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        LinearLayout dialogLayout = findViewById(R.id.dialogLayout);
        dialogLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button closeButton = findViewById(R.id.closeButton);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
