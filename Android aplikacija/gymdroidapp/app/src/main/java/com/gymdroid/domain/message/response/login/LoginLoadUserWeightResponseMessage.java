package com.gymdroid.domain.message.response.login;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseLargeDataMessage;

import java.util.ArrayList;

public class LoginLoadUserWeightResponseMessage extends ResponseLargeDataMessage {

    private ArrayList<UserWeight> userWeightArrayList;

    public LoginLoadUserWeightResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<UserWeight> userWeightArrayList) {
        super(status, user, totalArraySize, finishIndex);
        this.userWeightArrayList = userWeightArrayList;
    }

    public ArrayList<UserWeight> getEquipmentArrayList() {
        return userWeightArrayList;
    }

    public void setEquipmentTypeArrayList(ArrayList<UserWeight> equipmentArrayList) {
        this.userWeightArrayList = userWeightArrayList;
    }
}
