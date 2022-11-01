package com.gymdroid.activities.error;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.activities.base.FloatingBaseActivity;

public class ErrorActivity extends FloatingBaseActivity {

    public static final String LOGOUT = "Logout";
    public static final String ERROR_MESSAGE = "Error Message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_re_log_error);

        Intent intent = getIntent();
        String errorMessage = intent.getStringExtra(ERROR_MESSAGE);
        final boolean logout = intent.getBooleanExtra(LOGOUT,false);

        TextView errorMessageTextView = findViewById(R.id.errorMessageTextView);
        if (errorMessage.equals("The password is invalid or the user does not have a password."))
            errorMessage = "The password is invalid.";
        if (errorMessage.equals("There is no user record corresponding to this identifier. The user may have been deleted."))
            errorMessage = "There is no user record corresponding to this identifier.";
        errorMessageTextView.setText(errorMessage + "\n");

        Button goBackButton = findViewById(R.id.goBackButton);
       // if(logout) {
       //     goBackButton.setText(getString(R.string.logout));
       // }
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(logout) {
                    allServices.getConfigurationService().clearUser();
                    allServices.getStartActivityService().startSplashScreenActivity(activity);
                }
                else {
                    onBackPressed();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        allServices.getConfigurationService().clearUser();
        allServices.getStartActivityService().startSplashScreenActivity(activity);
    }
}
