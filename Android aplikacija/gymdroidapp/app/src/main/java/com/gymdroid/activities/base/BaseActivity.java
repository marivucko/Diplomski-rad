package com.gymdroid.activities.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gymdroid.dao.Database;
import com.gymdroid.services.AllServices;

@SuppressLint("Registered")
public class BaseActivity extends AppCompatActivity {

    protected static final int EXPECTED_ID = -1;

    protected AllServices allServices;
    protected Activity activity;
    protected Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        allServices = AllServices.getInstance(activity);
        database = allServices.getDatabase();
    }

    @Override
    protected void onResume() {
        super.onResume();
        allServices.getWebSocketConnectionService().initWebSocketConnection(activity);
    }

}
