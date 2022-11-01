package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterEquipmentResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<Equipment> equipmentArrayList;

    public EnterEquipmentResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<Equipment> equipmentArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.equipmentArrayList = equipmentArrayList;
    }

    public ArrayList<Equipment> getEquipmentArrayList() {
        return equipmentArrayList;
    }

    public void setEquipmentTypeArrayList(ArrayList<Equipment> equipmentArrayList) {
        this.equipmentArrayList = equipmentArrayList;
    }
}