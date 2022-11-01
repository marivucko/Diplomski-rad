package com.gymdroid.activities.error;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gymdroid.R;
import com.gymdroid.services.AllServices;

public class ServerIsDownActivity extends AppCompatActivity {

    protected AllServices allServices;
    protected Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_server_is_down);
        activity = this;
        allServices = AllServices.getInstance(activity);
        allServices.getSimpleDesignService().setStatusAndNavigationBarFloating(activity);

        Button goBackButton = findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getStartActivityService().startSplashScreenActivity(activity);
            }
        });

    }
}
