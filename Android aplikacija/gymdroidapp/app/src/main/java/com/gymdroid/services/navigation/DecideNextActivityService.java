package com.gymdroid.services.navigation;

import android.app.Activity;

import com.gymdroid.R;
import com.gymdroid.activities.MainActivity;
import com.gymdroid.activities.registration.LoginActivity;
import com.gymdroid.domain.beans.User;
import com.gymdroid.services.BaseService;

public class DecideNextActivityService extends BaseService {

    public String decideFlow(Activity activity) {
        if(INSTANCE.getConfigurationService().isUserLoggedIn()) {
            User user = INSTANCE.getConfigurationService().getUser();
            INSTANCE.getDialogResponseService().
                    openWaitForServerResponseDialog(activity,activity.getResources().getString( R.string.server_loading_messages_connecting_to_server));
            INSTANCE.getWebSocketRequestEnterService().enter(activity,user);
            return MainActivity.class.getSimpleName();
        }
        else {
            INSTANCE.getStartActivityService().startLoginActivity(activity);
            return LoginActivity.class.getSimpleName();
        }
    }

}
