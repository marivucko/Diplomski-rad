package com.gymdroid.activities.loading;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.activities.base.DialogActivity;

public class WaitForServerResponseActivity extends DialogActivity {

    public static final String LOADING_MESSAGE = "LoadingMessage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_wait_for_server_response);

        Intent intent = getIntent();
        String loadingMessage = intent.getStringExtra(LOADING_MESSAGE);

        TextView loadingMessageTextView = findViewById(R.id.loadingMessageTextView);
        loadingMessageTextView.setText(loadingMessage);

        allServices.getDialogResponseService().waitForResponse(this);
    }

    public void onSuperBackPressed(){
        super.onBackPressed();
    }

    @Override
    public void onBackPressed() {

    }

}
