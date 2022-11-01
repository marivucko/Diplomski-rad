package com.gymdroid.services.websocket.core;

import android.app.Activity;

import com.gymdroid.R;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.services.BaseService;

public class WebSocketResponseService extends BaseService {

    protected boolean handleErrors(Activity activity, StatusEnum statusEnum) {
        INSTANCE.getDialogResponseService().signalizeReceived();
        System.out.println(statusEnum);
        switch (statusEnum) {
            case LOGIN_ERROR_WRONG_TYPE: {
                INSTANCE.getStartActivityService().startErrorActivity(activity,activity.getString( R.string.server_error_login_wrong_type),false);
                break;
            }
            case USER_DOES_NOT_EXIST: {
                INSTANCE.getStartActivityService().startErrorActivity(activity,activity.getString(R.string.server_error_user_not_exist),true);
                break;
            }
            case LOGIN_EMAIL_WRONG_PASSWORD: {
                INSTANCE.getStartActivityService().startErrorActivity(activity,activity.getString(R.string.server_error_login_wrong_password),false);
                break;
            }


            case REGISTER_ERROR_WRONG_TYPE: {
                INSTANCE.getStartActivityService().startErrorActivity(activity,activity.getString(R.string.server_error_register_wrong_type),false);
                break;
            }
            case REGISTER_ERROR_USER_EXIST: {
                INSTANCE.getStartActivityService().startErrorActivity(activity,activity.getString(R.string.server_error_register_user_exist),false);
                break;
            }
            case LOGIN_GOOGLE_WRONG_FIREBASE_ID: {
                INSTANCE.getStartActivityService().startErrorActivity(activity,activity.getString(R.string.server_error_login_google_wrong_firebase_id),false);
                break;
            }


            case SERVER_ERROR: {
                INSTANCE.getStartActivityService().startErrorActivity(activity,activity.getString(R.string.server_error),false);
                break;
            }

            default: {
                // TODO what?
            }
        }
        return false;
    }

}
