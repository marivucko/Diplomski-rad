package com.gymdroid.services.websocket;

import android.app.Activity;

import com.gymdroid.activities.loading.WaitForInternetActivity;
import com.gymdroid.activities.loading.WaitForServerResponseActivity;

import java.util.Calendar;

public class DialogThread extends Thread {

    private static int globid=1;
    private Activity activity;
    private boolean isReadyToStart;
    private int id;

    DialogThread() {
        this.isReadyToStart = false; id=globid++;
    }

    public void signalizeToStop() {
        this.isReadyToStart = false;
    }

    private void signalizeToStart() {
        this.isReadyToStart = true;
        if (this.getState() == State.NEW) {
            this.start();
        }
    }

    public boolean isNotLoading() {
        return !isReadyToStart;
    }

    public boolean setActivityAndStart(Activity activity) {
        if(activity != null && isNotLoading()) {
            this.activity = activity;
            signalizeToStart();
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        while (isReadyToStart || activity == null) {
            try {
                String s = "";
                if (activity instanceof WaitForServerResponseActivity) s+="&&&CEKA SERVER";
                if (activity instanceof WaitForInternetActivity) s+="&&&CEKA INTERNET";
                System.out.println(s + " " + id + " " + Calendar.getInstance().getTime());
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (activity instanceof WaitForServerResponseActivity) {
                    ((WaitForServerResponseActivity) activity).onSuperBackPressed();
                }
                if (activity instanceof WaitForInternetActivity) {
                    ((WaitForInternetActivity) activity).onSuperBackPressed();
                }
            }
        });
    }

}
