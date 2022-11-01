package com.gymdroid.services.authentication;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.common.hash.Hashing;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.gymdroid.R;
import com.gymdroid.domain.RegistrationType;
import com.gymdroid.domain.beans.User;

import java.nio.charset.StandardCharsets;

public class GoogleAuthService extends AuthenticationService {

    private static int REQUEST_CODE = 101;
    private GoogleSignInClient googleSignInClient;

    public GoogleAuthService(Activity activity) {
        super(activity);
    }

    /// initiate before google login button.
    public GoogleSignInClient initiate(Activity activity){
        FirebaseApp.initializeApp(activity);
        firebaseAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(activity.getResources().getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(activity, googleSignInOptions);
        return googleSignInClient;
    }

    public Intent registerUser(Activity activity){
        if(googleSignInClient == null) {
            initiate(activity);
        }
        Intent signInIntent = googleSignInClient.getSignInIntent();
        activity.startActivityForResult(signInIntent,REQUEST_CODE);
        return signInIntent;
    }

    public void onActivityResult(Activity activity, int requestCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(activity, account);
            } catch (ApiException e) {
                goToErrorActivity(activity,e.getMessage());
            }
        }
    }

    private void firebaseAuthWithGoogle(final Activity activity, final GoogleSignInAccount acct) {
        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString(R.string.server_loading_messages_login_in_progress));
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                        User user = new User();
                        user.setUserEmail(firebaseUser.getEmail());
                        user.setFirebaseId(firebaseUser.getUid());
                        user.setRegistrationType( RegistrationType.GOOGLE);
                        user.setUserPassword(Hashing.sha256().hashString(firebaseUser.getEmail(), StandardCharsets.UTF_8).toString());
                        registerUserToServer(activity, user);
                    } else {
                        INSTANCE.getDialogResponseService().signalizeReceived();
                        goToErrorActivity(activity,task.getException().getMessage());
                    }

                }
            });
    }

}
