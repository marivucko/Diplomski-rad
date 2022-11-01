package com.gymdroid.activities.base;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

@SuppressLint("Registered")
public class WhiteActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allServices.getSimpleDesignService().setStatusBarColorDefault(activity);
        allServices.getSimpleDesignService().changeNavigationBarColor(activity,Color.parseColor("#FCFCFC"));
    }

}
