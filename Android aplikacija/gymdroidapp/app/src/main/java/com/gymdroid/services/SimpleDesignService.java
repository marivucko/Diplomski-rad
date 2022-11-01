package com.gymdroid.services;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SimpleDesignService extends BaseService {

    private static final int ANDROID_VERSION_SDK_23 = 23;
    private static final int ANDROID_VERSION_SDK_26 = 26;

    public SimpleDesignService(){

    }

    public void setStatusBarColorDefault(Activity activity){
        if(Build.VERSION.SDK_INT >= ANDROID_VERSION_SDK_23){
            Window window = activity.getWindow();
            int flags = window.getDecorView().getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            if(Build.VERSION.SDK_INT >= ANDROID_VERSION_SDK_26) {
                flags |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
            }
            window.getDecorView().setSystemUiVisibility(flags);
            window.setStatusBarColor(Color.parseColor("#FCFCFC"));
        }
        else{
            activity.getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    public void changeNavigationBarColor(Activity activity, int color){
        Window window = activity.getWindow();
        window.setNavigationBarColor(color);
    }

    public void setStatusAndNavigationBarFloating(Activity activity){
        activity.getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }

    public void clearStatusAndNavigationBarFloating(Activity activity) {
        activity.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );
    }

}
