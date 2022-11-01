package com.gymdroid.domain.message.request;

import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.request.core.RequestMessage;

public class EquipmentRequestMessage extends RequestMessage {

    private Equipment equipment;

    public EquipmentRequestMessage(User user, Equipment equipment) {
        super(user);
        this.equipment = equipment;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
