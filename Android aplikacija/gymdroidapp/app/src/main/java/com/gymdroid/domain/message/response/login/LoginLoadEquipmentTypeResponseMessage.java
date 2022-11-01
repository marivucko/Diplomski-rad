package com.gymdroid.domain.message.response.login;

import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseLargeDataMessage;

import java.util.ArrayList;

public class LoginLoadEquipmentTypeResponseMessage extends ResponseLargeDataMessage {

    private ArrayList<EquipmentType> equipmentTypeArrayList;

    public LoginLoadEquipmentTypeResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<EquipmentType> equipmentTypeArrayList) {
        super(status, user, totalArraySize, finishIndex);
        this.equipmentTypeArrayList = equipmentTypeArrayList;
    }

    public ArrayList<EquipmentType> getEquipmentTypeArrayList() {
        return equipmentTypeArrayList;
    }

    public void setEquipmentTypeArrayList(ArrayList<EquipmentType> equipmentTypeArrayList) {
        this.equipmentTypeArrayList = equipmentTypeArrayList;
    }
}
