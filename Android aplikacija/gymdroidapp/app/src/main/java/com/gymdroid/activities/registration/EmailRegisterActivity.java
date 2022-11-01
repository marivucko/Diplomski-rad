package com.gymdroid.activities.registration;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.common.hash.Hashing;
import com.gymdroid.R;
import com.gymdroid.activities.base.FloatingBaseActivity;
import com.gymdroid.domain.RegistrationType;
import com.gymdroid.domain.beans.User;

import java.nio.charset.StandardCharsets;

public class EmailRegisterActivity extends FloatingBaseActivity {

    EditText emailEditText;
    EditText passwordEditText;
    EditText repeatPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_email_register);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        repeatPasswordEditText = findViewById(R.id.repeatPasswordEditText);

        Button registerButton = findViewById(R.id.emailRegisterButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String repeatPassword = repeatPasswordEditText.getText().toString();

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
                if(allServices.getEmailAuthService().checkIsStringEmpty(repeatPassword)) {
                    repeatPasswordEditText.setError(activity.getResources().getString(R.string.registration_errors_repeated_password_is_empty));
                    return;
                }
                if(!password.equals(repeatPassword)) {
                    repeatPasswordEditText.setError(activity.getResources().getString(R.string.registration_errors_repeated_password_incorrect));
                    return;
                }

                User user = new User();
                user.setUserEmail(email);
                user.setUserPassword(Hashing.sha256().hashString(email + password, StandardCharsets.UTF_8).toString());
                user.setRegistrationType( RegistrationType.EMAIL);

                allServices.getEmailAuthService().registerUser(activity, user);
            }
        });
    }
}
