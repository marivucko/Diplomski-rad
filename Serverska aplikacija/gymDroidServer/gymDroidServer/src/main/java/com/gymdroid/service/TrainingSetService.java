package com.gymdroid.service;

import com.gymdroid.dao.DoneSetHandler;
import com.gymdroid.dao.TrainingSetHandler;
import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.TrainingSet;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.response.enter.EnterTrainingSetResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class TrainingSetService {

    public static EnterTrainingSetResponseMessage enterTrainingSet(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<TrainingSet> trainingSetArrayList;


        if (enterRequestMessage.getOperation() == Operation.INSERT)
            trainingSetArrayList = TrainingSetHandler.generateMissingListForInsert(lastUpdatedAt,user);
        else
            trainingSetArrayList = TrainingSetHandler.generateMissingListForUpdate(lastUpdatedAt,user);


        ArrayList<TrainingSet> selectedTrainingSetArrayList = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = trainingSetArrayList.size();

        if(finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedTrainingSetArrayList.add(trainingSetArrayList.get(i));
        }

        return new EnterTrainingSetResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedTrainingSetArrayList, enterRequestMessage.getOperation()
        );
    }
}
