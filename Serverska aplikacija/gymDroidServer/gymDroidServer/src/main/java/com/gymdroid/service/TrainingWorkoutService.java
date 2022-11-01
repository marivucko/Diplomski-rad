package com.gymdroid.service;

import com.gymdroid.dao.TrainingWorkoutHandler;
import com.gymdroid.domain.beans.TrainingWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.response.enter.EnterTrainingWorkoutResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class TrainingWorkoutService {

    public static EnterTrainingWorkoutResponseMessage enterTrainingWorkout(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<TrainingWorkout> trainingWorkoutArrayList;


        if (enterRequestMessage.getOperation() == Operation.INSERT)
            trainingWorkoutArrayList = TrainingWorkoutHandler.generateMissingListForInsert(lastUpdatedAt,user);
        else
            trainingWorkoutArrayList = TrainingWorkoutHandler.generateMissingListForUpdate(lastUpdatedAt,user);


        ArrayList<TrainingWorkout> selectedTrainingWorkoutArrayList = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = trainingWorkoutArrayList.size();

        if(finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedTrainingWorkoutArrayList.add(trainingWorkoutArrayList.get(i));
        }

        return new EnterTrainingWorkoutResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedTrainingWorkoutArrayList, enterRequestMessage.getOperation()
        );
    }
}
