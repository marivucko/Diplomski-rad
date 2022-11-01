package com.gymdroid.activities.base;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.gymdroid.R;

@SuppressLint("Registered")
public class SlideInActivity extends WhiteActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition( R.anim.anim_slide_in_top, R.anim.anim_slide_out_top);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_slide_out_bottom);
    }

}
