package com.gymdroid.service;

import com.gymdroid.dao.EquipmentHandler;
import com.gymdroid.dao.UserHandler;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.EquipmentRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.domain.message.response.EquipmentResponseMessage;
import com.gymdroid.domain.message.response.enter.EnterEquipmentResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadEquipmentResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class EquipmentService {

    public static EquipmentResponseMessage addEquipment(EquipmentRequestMessage equipmentRequestMessage) {
        User user = equipmentRequestMessage.getUser();
        user = UserHandler.getUser(user);
        Equipment equipment = equipmentRequestMessage.getEquipment();

        if(UserService.isUserNotExist(user) && user != null) {
            return new EquipmentResponseMessage( StatusEnum.USER_DOES_NOT_EXIST, user, equipment);
        }

        ArrayList<Equipment> equipmentArrayList = EquipmentHandler.getAllUsersEquipment(user);
        boolean isNotExist = true;

        for(int i = 0; i < equipmentArrayList.size(); i++) {
            Equipment current = equipmentArrayList.get(i);
            if(equipment.getEquipmentTypeId() == current.getEquipmentTypeId() && equipment.getEquipmentWeight() == current.getEquipmentWeight()) {
                current.setEquipmentCount(current.getEquipmentCount() + equipment.getEquipmentCount());
                equipment = EquipmentHandler.updateEquipment(current);
                isNotExist = false;
                break;
            }
        }

        if(isNotExist) {
            equipment = EquipmentHandler.insertEquipment(equipment);
        }

        UserHandler.updateUser(user);

        System.out.println("add equipment : " + equipment.getEquipmentId());

        return new EquipmentResponseMessage(StatusEnum.ADD_EQUIPMENT_SUCCESS, user, equipment);
    }

    public static EquipmentResponseMessage deleteEquipment(EquipmentRequestMessage equipmentRequestMessage) {
        User user = equipmentRequestMessage.getUser();
        Equipment equipment = equipmentRequestMessage.getEquipment();
        EquipmentHandler.deleteEquipment(equipment.getEquipmentId());

        return new EquipmentResponseMessage(StatusEnum.DELETE_EQUIPMENT_SUCCESS, user, equipment);
    }

    public static LoginLoadEquipmentResponseMessage loginLoadEquipment(LoginLoadRequestMessage loginLoadRequestMessage) {
        User user = loginLoadRequestMessage.getUser();
        ArrayList<Equipment> equipmentArrayList = EquipmentHandler.getAllUsersEquipment(user);
        ArrayList<Equipment> selectedEquipment = new ArrayList<>();
        int startIndex = loginLoadRequestMessage.getStartIndex();
        int finishIndex = startIndex + loginLoadRequestMessage.getIncrement();
        int totalArraySize = equipmentArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedEquipment.add(equipmentArrayList.get(i));
        }

        return new LoginLoadEquipmentResponseMessage( StatusEnum.LOGIN_LOAD_SUCCESS, user, totalArraySize, finishIndex, selectedEquipment );
    }

    public static EnterEquipmentResponseMessage enterEquipment(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<Equipment> equipmentArrayList;

        if (enterRequestMessage.getOperation() == Operation.INSERT)
            equipmentArrayList = EquipmentHandler.generateMissingListForInsert(lastUpdatedAt,user);
        else
            equipmentArrayList = EquipmentHandler.generateMissingListForUpdate(lastUpdatedAt,user);


        ArrayList<Equipment> selectedEquipment = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = equipmentArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedEquipment.add(equipmentArrayList.get(i));
        }

        return new EnterEquipmentResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedEquipment, enterRequestMessage.getOperation()
        );
    }

}
