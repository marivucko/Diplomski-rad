package com.gymdroid.activities.base;

import android.annotation.SuppressLint;
import android.os.Bundle;

@SuppressLint("Registered")
public class FloatingBaseActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allServices.getSimpleDesignService().setStatusAndNavigationBarFloating(activity);
    }

}
