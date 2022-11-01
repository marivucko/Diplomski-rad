package com.gymdroid.service;

import com.gymdroid.dao.DoneSetHandler;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.domain.message.response.enter.EnterDoneSetResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadDoneSetResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class DoneSetService {

    public static EnterDoneSetResponseMessage enterDoneSet(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<DoneSet> doneSetArrayList;


        if (enterRequestMessage.getOperation() == Operation.INSERT)
            doneSetArrayList = DoneSetHandler.generateMissingListForInsert(lastUpdatedAt,user);
        else
            doneSetArrayList = DoneSetHandler.generateMissingListForUpdate(lastUpdatedAt,user);


        ArrayList<DoneSet> selectedUserWeightArrayList = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = doneSetArrayList.size();

        if(finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedUserWeightArrayList.add(doneSetArrayList.get(i));
        }

        return new EnterDoneSetResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedUserWeightArrayList, enterRequestMessage.getOperation()
        );
    }

    public static LoginLoadDoneSetResponseMessage loginLoadDoneSets(LoginLoadRequestMessage loginLoadRequestMessage) {

        User user = loginLoadRequestMessage.getUser();
        ArrayList<DoneSet> doneSetArrayList = DoneSetHandler.getAllUsersDoneSets(user);
        ArrayList<DoneSet> selectedDoneSets = new ArrayList<>();
        int startIndex = loginLoadRequestMessage.getStartIndex();
        int finishIndex = startIndex + loginLoadRequestMessage.getIncrement();
        int totalArraySize = doneSetArrayList.size();

        if (finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for (int i = startIndex; i < finishIndex; i++) {
            selectedDoneSets.add(doneSetArrayList.get(i));
        }

        return new LoginLoadDoneSetResponseMessage(StatusEnum.LOGIN_LOAD_SUCCESS, user, totalArraySize, finishIndex, selectedDoneSets);
    }

}
