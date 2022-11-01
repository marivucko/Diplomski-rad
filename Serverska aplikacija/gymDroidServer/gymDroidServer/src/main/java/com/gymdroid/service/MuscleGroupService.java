package com.gymdroid.service;

import com.gymdroid.dao.MuscleGroupHandler;
import com.gymdroid.domain.beans.MuscleGroup;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.response.enter.EnterMuscleGroupResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class MuscleGroupService {

    public static EnterMuscleGroupResponseMessage enterMuscleGroup(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<MuscleGroup> muscleGroupArrayList;

        if (enterRequestMessage.getOperation() == Operation.INSERT)
            muscleGroupArrayList = MuscleGroupHandler.generateMissingListForInsert(lastUpdatedAt);
        else
            muscleGroupArrayList = MuscleGroupHandler.generateMissingListForUpdate(lastUpdatedAt);


        ArrayList<MuscleGroup> selectedMuscleGroup = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = muscleGroupArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedMuscleGroup.add(muscleGroupArrayList.get(i));
        }

        return new EnterMuscleGroupResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedMuscleGroup, enterRequestMessage.getOperation()
        );
    }

}
