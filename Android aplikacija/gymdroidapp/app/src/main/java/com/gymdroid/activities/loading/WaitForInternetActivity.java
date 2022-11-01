package com.gymdroid.activities.loading;

import android.os.Bundle;

import com.gymdroid.R;
import com.gymdroid.activities.base.DialogActivity;

public class WaitForInternetActivity extends DialogActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_wait_for_internet);
        allServices.getDialogInternetService().waitForInternetConnection( WaitForInternetActivity.this);
    }

    public void onSuperBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {

    }

}
