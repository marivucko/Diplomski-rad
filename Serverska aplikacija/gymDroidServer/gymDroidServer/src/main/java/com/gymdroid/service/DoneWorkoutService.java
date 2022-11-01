package com.gymdroid.service;

import com.gymdroid.dao.DoneWorkoutHandler;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.domain.message.response.enter.EnterDoneWorkoutResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadDoneWorkoutResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class DoneWorkoutService {

    public static EnterDoneWorkoutResponseMessage enterDoneWorkout(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        System.out.println("'''''''''''''''''''''''''''''''''''''' " + lastUpdatedAt);
        ArrayList<DoneWorkout> doneWorkoutArrayList;

        if (enterRequestMessage.getOperation() == Operation.INSERT)
            doneWorkoutArrayList = DoneWorkoutHandler.generateMissingListForInsert(lastUpdatedAt,user);
        else
            doneWorkoutArrayList = DoneWorkoutHandler.generateMissingListForUpdate(lastUpdatedAt,user);


        ArrayList<DoneWorkout> selectedUserWeightArrayList = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = doneWorkoutArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedUserWeightArrayList.add(doneWorkoutArrayList.get(i));
        }

        return new EnterDoneWorkoutResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedUserWeightArrayList, enterRequestMessage.getOperation()
        );

    }

    public static LoginLoadDoneWorkoutResponseMessage loginLoadDoneWorkouts(LoginLoadRequestMessage loginLoadRequestMessage) {

        User user = loginLoadRequestMessage.getUser();
        ArrayList<DoneWorkout> doneWorkoutArrayList = DoneWorkoutHandler.getAllUsersDoneWorkouts(user);
        ArrayList<DoneWorkout> selectedDoneWorkouts = new ArrayList<>();
        int startIndex = loginLoadRequestMessage.getStartIndex();
        int finishIndex = startIndex + loginLoadRequestMessage.getIncrement();
        int totalArraySize = doneWorkoutArrayList.size();

        if (finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for (int i = startIndex; i < finishIndex; i++) {
            selectedDoneWorkouts.add(doneWorkoutArrayList.get(i));
        }

        return new LoginLoadDoneWorkoutResponseMessage(StatusEnum.LOGIN_LOAD_SUCCESS, user, totalArraySize, finishIndex, selectedDoneWorkouts);

    }
}
