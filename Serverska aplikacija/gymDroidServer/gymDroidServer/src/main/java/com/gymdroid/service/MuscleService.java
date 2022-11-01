package com.gymdroid.service;

import com.gymdroid.dao.MuscleGroupHandler;
import com.gymdroid.dao.MuscleHandler;
import com.gymdroid.dao.UserHandler;
import com.gymdroid.domain.beans.Muscle;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.domain.message.response.enter.EnterMuscleResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadMuscleResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class MuscleService {

    public static LoginLoadMuscleResponseMessage loginLoadMuscle(LoginLoadRequestMessage muscleRequestMessage) {

        User user = muscleRequestMessage.getUser();
        User userInDatabase = UserHandler.getUser(user);

        if(userInDatabase == null) {
            return new LoginLoadMuscleResponseMessage( StatusEnum.USER_DOES_NOT_EXIST, user, null, null);
        }

        return new LoginLoadMuscleResponseMessage(
                StatusEnum.LOGIN_LOAD_SUCCESS,
                user,
                MuscleHandler.getAllMuscles(),
                MuscleGroupHandler.getAllMuscleGroups()
        );
    }

    public static EnterMuscleResponseMessage enterMuscle(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<Muscle> muscleArrayList;


        if (enterRequestMessage.getOperation() == Operation.INSERT)
            muscleArrayList = MuscleHandler.generateMissingListForInsert(lastUpdatedAt);
        else
            muscleArrayList = MuscleHandler.generateMissingListForUpdate(lastUpdatedAt);


        ArrayList<Muscle> selectedMuscleArrayList = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = muscleArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            //System.out.println( "VAZNO " + muscleArrayList.get(i).getCreatedAt() );
            selectedMuscleArrayList.add(muscleArrayList.get(i));
        }

        return new EnterMuscleResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedMuscleArrayList,enterRequestMessage.getOperation()
        );
    }

}
