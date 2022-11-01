package com.gymdroid.service;

import com.gymdroid.dao.EquipmentTypeHandler;
import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.domain.message.response.enter.EnterEquipmentTypeResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadEquipmentTypeResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class EquipmentTypeService {

    public static LoginLoadEquipmentTypeResponseMessage loginLoadEquipmentTypes(LoginLoadRequestMessage loginLoadRequestMessage) {
        User user = loginLoadRequestMessage.getUser();
        ArrayList<EquipmentType> equipmentTypeArrayList = EquipmentTypeHandler.getAllEquipmentType();
        ArrayList<EquipmentType> selectedEquipmentTypeArrayList = new ArrayList<>();
        int startIndex = loginLoadRequestMessage.getStartIndex();
        int finishIndex = startIndex + loginLoadRequestMessage.getIncrement();
        int totalArraySize = equipmentTypeArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedEquipmentTypeArrayList.add(equipmentTypeArrayList.get(i));
        }

        return new LoginLoadEquipmentTypeResponseMessage( StatusEnum.LOGIN_LOAD_SUCCESS, user, totalArraySize, finishIndex, selectedEquipmentTypeArrayList );
    }

    public static EnterEquipmentTypeResponseMessage enterEquipmentType(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<EquipmentType> equipmentTypeArrayList;



        if (enterRequestMessage.getOperation() == Operation.INSERT)
            equipmentTypeArrayList = EquipmentTypeHandler.generateMissingListForInsert(lastUpdatedAt);
        else
            equipmentTypeArrayList = EquipmentTypeHandler.generateMissingListForUpdate(lastUpdatedAt);


        ArrayList<EquipmentType> selectedEquipmentType = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = equipmentTypeArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedEquipmentType.add(equipmentTypeArrayList.get(i));
        }

        return new EnterEquipmentTypeResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedEquipmentType, enterRequestMessage.getOperation()
        );
    }

}
