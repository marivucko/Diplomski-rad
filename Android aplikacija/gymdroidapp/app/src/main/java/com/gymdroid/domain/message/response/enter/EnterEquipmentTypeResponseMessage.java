package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterEquipmentTypeResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<EquipmentType> equipmentTypeArrayList;

    public EnterEquipmentTypeResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<EquipmentType> equipmentTypeArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.equipmentTypeArrayList = equipmentTypeArrayList;
    }

    public ArrayList<EquipmentType> getEquipmentTypeArrayList() {
        return equipmentTypeArrayList;
    }

    public void setEquipmentTypeArrayList(ArrayList<EquipmentType> equipmentTypeArrayList) {
        this.equipmentTypeArrayList = equipmentTypeArrayList;
    }
}
