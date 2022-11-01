package com.gymdroid.domain.message.response;

import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

public class EquipmentResponseMessage extends ResponseMessage {

    private Equipment equipment;

    public EquipmentResponseMessage(StatusEnum status, User user, Equipment equipment) {
        super(status, user);
        this.equipment = equipment;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

}
