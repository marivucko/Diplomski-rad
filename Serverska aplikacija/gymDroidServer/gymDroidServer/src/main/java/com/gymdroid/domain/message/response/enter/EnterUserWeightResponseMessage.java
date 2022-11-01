package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;


import java.util.ArrayList;

public class EnterUserWeightResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<UserWeight> userWeightArrayList;

    public EnterUserWeightResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<UserWeight> userWeightArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.userWeightArrayList = userWeightArrayList;
    }

    public ArrayList<UserWeight> getUserWeightArrayList() {
        return userWeightArrayList;
    }

    public void setUserWeightArrayList(ArrayList<UserWeight> userWeightArrayList) {
        this.userWeightArrayList = userWeightArrayList;
    }
}
