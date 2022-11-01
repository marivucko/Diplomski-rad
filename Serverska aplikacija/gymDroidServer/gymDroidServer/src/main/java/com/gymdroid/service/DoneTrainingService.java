package com.gymdroid.service;

import com.gymdroid.dao.DoneTrainingHandler;
import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.domain.message.response.enter.EnterDoneTrainingResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadDoneTrainingResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class DoneTrainingService {

    public static EnterDoneTrainingResponseMessage enterDoneTraining(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<DoneTraining> doneTrainingArrayList;


        if (enterRequestMessage.getOperation() == Operation.INSERT)
            doneTrainingArrayList = DoneTrainingHandler.generateMissingListForInsert(lastUpdatedAt,user);
        else
            doneTrainingArrayList =DoneTrainingHandler.generateMissingListForUpdate(lastUpdatedAt,user);


        ArrayList<DoneTraining> selectedDoneTrainingArrayList = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = doneTrainingArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedDoneTrainingArrayList.add(doneTrainingArrayList.get(i));
        }

        return new EnterDoneTrainingResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedDoneTrainingArrayList, enterRequestMessage.getOperation()
        );
    }

    public static LoginLoadDoneTrainingResponseMessage loginLoadDoneTraining(LoginLoadRequestMessage loginLoadRequestMessage) {

        User user = loginLoadRequestMessage.getUser();
        ArrayList<DoneTraining> doneTrainingArrayList = DoneTrainingHandler.getAllUsersDoneTraining(user);
        ArrayList<DoneTraining> selectedDoneTrainingArrayList = new ArrayList<>();

        int startIndex = loginLoadRequestMessage.getStartIndex();
        int finishIndex = startIndex + loginLoadRequestMessage.getIncrement();
        int totalArraySize = doneTrainingArrayList.size();

        if (finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for (int i = startIndex; i < finishIndex; i++) {
            selectedDoneTrainingArrayList.add(doneTrainingArrayList.get(i));
        }

        return new LoginLoadDoneTrainingResponseMessage(StatusEnum.LOGIN_LOAD_SUCCESS, user, totalArraySize, finishIndex, selectedDoneTrainingArrayList);
    }
}
