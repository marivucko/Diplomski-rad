package com.gymdroid.services.weight;

import android.app.Activity;

import com.gymdroid.R;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.services.BaseService;

public class UserWeightService extends BaseService {

    public boolean addUserWeight(Activity activity, UserWeight userWeight) {
        if(userWeight.getWeight() < 1) return false;

        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString( R.string.server_loading_messages_adding_new_user_weight));
        User user = INSTANCE.getConfigurationService().getUser();
        userWeight.setUserId(user.getUserId());

        INSTANCE.getWebSocketRequestOperationService().addUserWeight(activity,user,userWeight);
        return true;
    }

    public boolean deleteUserWeight(Activity activity, int index) {
        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString(R.string.server_loading_messages_deleting_user_weight));
        User user = INSTANCE.getConfigurationService().getUser();
        UserWeight userWeight = INSTANCE.getDatabase().getUserWeightArrayList().get(index);
        INSTANCE.getWebSocketRequestOperationService().deleteUserWeight(activity, user, userWeight);
        return true;
    }

}
