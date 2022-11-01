package com.gymdroid.services.authentication;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.gymdroid.R;
import com.gymdroid.domain.beans.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailAuthService extends AuthenticationService {

    public EmailAuthService(Activity activity) {
        super(activity);
    }

    public boolean registerUser(final Activity activity,final User user) {
        if(checkIsStringEmpty(user.getUserEmail()) || checkIsStringEmpty(user.getUserPassword())) return false;
        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString( R.string.server_loading_messages_wait_for_server_response));
        firebaseAuth.createUserWithEmailAndPassword(user.getUserEmail(), user.getUserPassword())
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        user.setFirebaseId(task.getResult().getUser().getUid());
                        registerUserToServer(activity, user);
                    } else {
                        INSTANCE.getDialogResponseService().signalizeReceived();
                        goToErrorActivity(activity,task.getException().getMessage());
                    }
                }
            });
        return true;
    }

//    public boolean registerUser(final Activity activity,final User user) {
//        if(checkIsStringEmpty(user.getUserEmail()) || checkIsStringEmpty(user.getUserPassword())) return false;
//        firebaseAuth.createUserWithEmailAndPassword(user.getUserEmail(), user.getUserPassword())
//                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            user.setFirebaseId(task.getResult().getUser().getUid());
//                            INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString(R.string.server_loading_messages_wait_for_server_response));
//                            registerUserToServer(activity, user);
//                        } else {
//                            //INSTANCE.getDialogResponseService().signalizeReceived();
//                            goToErrorActivity(activity,task.getException().getMessage());
//                        }
//                    }
//                });
//        return true;
//    }

    //    public boolean loginUser(final Activity activity,final User user) {
//        if(checkIsStringEmpty(user.getUserEmail()) || checkIsStringEmpty(user.getUserPassword())) return false;
//        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString(R.string.server_loading_messages_login_in_progress));
//        firebaseAuth.signInWithEmailAndPassword(user.getUserEmail(), user.getUserPassword())
//            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        user.setFirebaseId(task.getResult().getUser().getUid());
//                        loginUserToServer(activity, user);
//                    } else {
//                        INSTANCE.getDialogResponseService().signalizeReceived();
//                        goToErrorActivity(activity,task.getException().getMessage());
//                    }
//                }
//            });
//        return true;
//    }

    public boolean loginUser(final Activity activity,final User user) {
        if(checkIsStringEmpty(user.getUserEmail()) || checkIsStringEmpty(user.getUserPassword())) return false;
        firebaseAuth.signInWithEmailAndPassword(user.getUserEmail(), user.getUserPassword())
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user.setFirebaseId(task.getResult().getUser().getUid());
                            INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString(R.string.server_loading_messages_login_in_progress));
                            loginUserToServer(activity, user);
                        }
                        else {
                            goToErrorActivity(activity,task.getException().getMessage());
                        }

                    }
                });
        return true;
    }

    public boolean checkIsStringEmpty(String data) {
        return "".equals(data) || data == null;
    }

    public boolean isEmailNotValid(String email) {
        if(checkIsStringEmpty(email)) return true;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }

}
