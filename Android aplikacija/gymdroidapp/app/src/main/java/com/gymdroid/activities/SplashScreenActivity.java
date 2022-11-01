package com.gymdroid.activities;

import android.os.Bundle;
import android.os.Handler;

import com.gymdroid.R;
import com.gymdroid.activities.base.FloatingBaseActivity;

public class SplashScreenActivity extends FloatingBaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_splash_screen);
        Handler handler = new Handler();

        final Runnable r = new Runnable() {
            public void run() {
                while (!database.isFinishedLoading());
                allServices.getDecideNextActivityService().decideFlow(activity);
            }
        };
        handler.postDelayed(r, 1000);
    }
}
