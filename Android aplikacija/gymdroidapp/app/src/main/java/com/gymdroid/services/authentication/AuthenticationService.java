package com.gymdroid.services.authentication;

import android.app.Activity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.gymdroid.domain.beans.User;
import com.gymdroid.services.BaseService;

public class AuthenticationService extends BaseService {

    protected FirebaseAuth firebaseAuth;
    protected FirebaseUser currentUser;

    AuthenticationService(Activity activity) {
        FirebaseApp.initializeApp(activity);
        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();
    }

    protected boolean registerUserToServer(Activity activity, User user) {
        return INSTANCE.getWebSocketRequestLoginLoadService().register(activity, user);
    }

    protected boolean loginUserToServer(Activity activity, User user) {
        return INSTANCE.getWebSocketRequestLoginLoadService().login(activity, user);
    }

    protected String goToErrorActivity(Activity activity, String errorMessage) {
        return INSTANCE.getStartActivityService().startErrorActivity(activity,errorMessage,false);
    }
}
