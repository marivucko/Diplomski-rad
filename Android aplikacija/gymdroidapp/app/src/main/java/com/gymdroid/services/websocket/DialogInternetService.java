package com.gymdroid.services.websocket;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.gymdroid.activities.loading.WaitForInternetActivity;
import com.gymdroid.services.BaseService;

public class DialogInternetService extends BaseService {

    private DialogThread dialogThread;

    public DialogInternetService() {
        dialogThread = new DialogThread();
    }

    public boolean isNetworkConnected(Activity activity) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public boolean openInternetConnectionDialog(final Activity activity){
        boolean isNetworkConnected = isNetworkConnected(activity);
        if(!isNetworkConnected && dialogThread.isNotLoading()){
            Intent intent = new Intent(activity, WaitForInternetActivity.class);
            activity.startActivity(intent);
        }
        else {
            if (isNetworkConnected) {
                dialogThread.signalizeToStop();
            }
        }
        return isNetworkConnected;
    }

    public void forceCloseInternetDialog() {
        dialogThread.signalizeToStop();
    }

    public void waitForInternetConnection(final WaitForInternetActivity activity){
        dialogThread.setActivityAndStart(activity);
    }

}
