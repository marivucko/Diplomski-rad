package com.gymdroid.services.equipment;

import android.app.Activity;

import com.gymdroid.R;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.User;
import com.gymdroid.services.BaseService;

public class EquipmentService extends BaseService {

    public boolean addEquipment(Activity activity, Equipment equipment) {
        if(equipment.getEquipmentCount() == 0) return false;
        if(equipment.getEquipmentTypeId() == 0) return false;
        if(equipment.getEquipmentWeight() < 0) return false;

        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString( R.string.server_loading_messages_adding_new_equipment));

        User user = INSTANCE.getConfigurationService().getUser();
        equipment.setUserOwnerId(user.getUserId());

        INSTANCE.getWebSocketRequestOperationService().addEquipment(activity,user,equipment);
        return true;
    }

    public boolean deleteEquipment (Activity activity, Equipment equipment) {
        INSTANCE.getDialogResponseService().openWaitForServerResponseDialog(activity,activity.getResources().getString(R.string.server_loading_messages_deleting_equipment));
        User user = INSTANCE.getConfigurationService().getUser();
        INSTANCE.getWebSocketRequestOperationService().deleteEquipment(activity, user, equipment);
        return true;
    }

}
