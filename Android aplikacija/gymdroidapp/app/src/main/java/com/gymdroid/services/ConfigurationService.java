package com.gymdroid.services;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.gymdroid.R;
import com.gymdroid.domain.RegistrationType;
import com.gymdroid.domain.beans.User;

import java.util.Date;

public class ConfigurationService extends BaseService {

    /// TODO save and get all dates
    private SharedPreferences sharedPreferences;
    private Context context;

    private int userId;
    private String userEmail;
    private String firebaseId;
    private String registrationType;
    private String registerDate;
    private User user;

    public ConfigurationService(Activity activity) {
        super();
        this.sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        this.context = activity.getApplicationContext();
    }

    public boolean setUser(User user) {
        if(checkNewUser(user)) {
            setUserId(user.getUserId());
            setUserEmail(user.getUserEmail());
            setFirebaseId(user.getFirebaseId());
            setRegistrationType(user.getRegistrationType());
            setRegisterDate(INSTANCE.getCalendarService().dateToString(user.getCreatedAt()));
            return true;
        }
        else {
            clearUser();
        }
        return false;
    }

    private boolean checkNewUser(User user) {
        if(user.getUserId() == 0) {
            return false;
        }
        if("".equals(user.getUserEmail()) || user.getUserEmail() == null) {
            return false;
        }
        if("".equals(user.getFirebaseId()) || user.getFirebaseId() == null) {
            return false;
        }
        if(!RegistrationType.EMAIL.equals(user.getRegistrationType()) && !RegistrationType.GOOGLE.equals(user.getRegistrationType())) {
            return false;
        }
        return true;
    }

    public void clearUser() {
        setUserId(0);
        setUserEmail(null);
        setFirebaseId(null);
        setRegistrationType(null);
        setRegisterDate(null);
    }

    private void setUserId(int userId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(context.getResources().getString( R.string.shared_userId), userId);
        editor.apply();
        this.userId = userId;
    }

    private void setUserEmail(String userEmail) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.shared_userEmail), userEmail);
        editor.apply();
        this.userEmail = userEmail;
    }

    private void setFirebaseId(String firebaseId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.shared_firebaseId), firebaseId);
        editor.apply();
        this.firebaseId = firebaseId;
    }

    private void setRegistrationType(String registrationType) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.shared_registrationType), registrationType);
        editor.apply();
        this.registrationType = registrationType;
    }

    private void setRegisterDate(String registerDate) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getResources().getString(R.string.shared_registerDate), registerDate);
        editor.apply();
        this.registerDate = registerDate;
    }

    public int getUserId(){
        if (userId == 0) {
            int defaultValue = 0;
            int userIdTemp = sharedPreferences.getInt(context.getResources().getString(R.string.shared_userId), defaultValue);
            if (userId != userIdTemp) {
                this.userId = userIdTemp;
            }
        }
        System.out.println("`+ " + userId);
        return userId;
    }

    public String getUserEmail() {
        if (userEmail == null) {
            String defaultValue = "";
            String userEmailTemp = sharedPreferences.getString(context.getResources().getString(R.string.shared_userEmail), defaultValue);
            if (!defaultValue.equals(userEmailTemp)) {
                this.userEmail = userEmailTemp;
            }
        }
        return userEmail;
    }

    public String getFirebaseId() {
        if (firebaseId == null) {
            String defaultValue = "";
            String firebaseIdTemp = sharedPreferences.getString(context.getResources().getString(R.string.shared_firebaseId), defaultValue);
            if (!defaultValue.equals(firebaseIdTemp)) {
                this.firebaseId = firebaseIdTemp;
            }
        }
        return firebaseId;
    }

    public String getRegistrationType() {
        if (registrationType == null) {
            String registrationType = "";
            String registrationTypeTemp = sharedPreferences.getString(context.getResources().getString(R.string.shared_registrationType), registrationType);
            if (!registrationType.equals(registrationTypeTemp)) {
                this.registrationType = registrationTypeTemp;
            }
        }
        return registrationType;
    }

    public Date getRegisterDate() {
        if (registerDate == null) {
            String registerDate = "";
            String registerDateTemp = sharedPreferences.getString(context.getResources().getString(R.string.shared_registerDate), registerDate);
            if (!registerDate.equals(registerDateTemp)) {
                this.registerDate = registerDateTemp;
            }
        }
        return INSTANCE.getCalendarService().stringToDate(registerDate);
    }

    public User getUser() {
        if(user == null) {
            user = new User();
        }
        user.setUserId(getUserId());
        user.setUserEmail(getUserEmail());
        user.setFirebaseId(getFirebaseId());
        user.setRegistrationType(getRegistrationType());
        user.setCreatedAt(getRegisterDate());
        return user;
    }

    public boolean isUserLoggedIn() {
        return getUserId() != 0
            && (!"".equals(getFirebaseId()) || getFirebaseId() != null)
            && (!"".equals(getRegistrationType()) || getRegistrationType() != null)
            && (!"".equals(getUserEmail()) || getUserEmail() != null)
        ;
    }

}
