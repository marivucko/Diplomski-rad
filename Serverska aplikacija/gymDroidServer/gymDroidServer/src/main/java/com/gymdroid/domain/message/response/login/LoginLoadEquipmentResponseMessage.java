package com.gymdroid.domain.message.response.login;

import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseLargeDataMessage;

import java.util.ArrayList;

public class LoginLoadEquipmentResponseMessage extends ResponseLargeDataMessage {

    private ArrayList<Equipment> equipmentArrayList;

    public LoginLoadEquipmentResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<Equipment> equipmentArrayList) {
        super(status, user, totalArraySize, finishIndex);
        this.equipmentArrayList = equipmentArrayList;
    }

    public ArrayList<Equipment> getEquipmentArrayList() {
        return equipmentArrayList;
    }

    public void setEquipmentTypeArrayList(ArrayList<Equipment> equipmentArrayList) {
        this.equipmentArrayList = equipmentArrayList;
    }
}
