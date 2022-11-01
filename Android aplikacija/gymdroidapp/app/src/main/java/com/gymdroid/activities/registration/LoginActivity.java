package com.gymdroid.activities.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.google.android.gms.common.SignInButton;
import com.google.common.hash.Hashing;
import com.gymdroid.R;
import com.gymdroid.activities.base.FloatingBaseActivity;
import com.gymdroid.domain.RegistrationType;
import com.gymdroid.domain.beans.User;

import java.nio.charset.StandardCharsets;

public class LoginActivity extends FloatingBaseActivity {

    EditText emailEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_login);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        Button emailLoginButton = findViewById(R.id.emailLoginButton);
        emailLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                if(allServices.getEmailAuthService().checkIsStringEmpty(email)) {
                    emailEditText.setError(activity.getResources().getString(R.string.registration_errors_email_is_empty));
                    return;
                }
                if(allServices.getEmailAuthService().isEmailNotValid(email)) {
                    emailEditText.setError(activity.getResources().getString(R.string.registration_errors_wrong_email_format));
                    return;
                }
                if(allServices.getEmailAuthService().checkIsStringEmpty(password)) {
                    passwordEditText.setError(activity.getResources().getString(R.string.registration_errors_password_is_empty));
                    return;
                }

                User user = new User();
                user.setUserEmail(email);
                user.setUserPassword(Hashing.sha256().hashString(email + password, StandardCharsets.UTF_8).toString());
                user.setRegistrationType( RegistrationType.EMAIL);

                allServices.getEmailAuthService().loginUser(activity, user);
            }
        });

        allServices.getGoogleAuthService().initiate(activity);
        SignInButton googleRegisterButton = findViewById(R.id.googleRegisterButton);
        googleRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getGoogleAuthService().registerUser(activity);
            }
        });

        Button emailRegisterButton = findViewById(R.id.emailRegisterButton);
        emailRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EmailRegisterActivity.class);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        allServices.getGoogleAuthService().onActivityResult(activity,requestCode,data);
    }
}
