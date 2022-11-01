package com.gymdroid.service;

import com.gymdroid.dao.UserWeightHandler;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.enter.EnterRequestMessage;
import com.gymdroid.domain.message.request.login.LoginLoadRequestMessage;
import com.gymdroid.domain.message.request.UserWeightRequestMessage;
import com.gymdroid.domain.message.response.enter.EnterUserWeightResponseMessage;
import com.gymdroid.domain.message.response.login.LoginLoadUserWeightResponseMessage;
import com.gymdroid.domain.message.response.UserWeightResponseMessage;

import java.util.ArrayList;
import java.util.Date;

public class UserWeightService {

    public static UserWeightResponseMessage addUserWeight(UserWeightRequestMessage userWeightRequestMessage) {

        User user = userWeightRequestMessage.getUser();
        UserWeight userWeight = userWeightRequestMessage.getUserWeight();

        userWeight = UserWeightHandler.insertUserWeight(userWeight);

        return new UserWeightResponseMessage(StatusEnum.ADD_USER_WEIGHT_SUCCESS, user, userWeight);
    }

    public static UserWeightResponseMessage deleteUserWeight(UserWeightRequestMessage userWeightDeleteRequestMessage) {

        User user = userWeightDeleteRequestMessage.getUser();
        UserWeight userWeight = userWeightDeleteRequestMessage.getUserWeight();
        int weightId = userWeight.getWeightId();
        UserWeightHandler.deleteUserWeight(weightId);

        return new UserWeightResponseMessage(StatusEnum.DELETE_USER_WEIGHT_SUCCESS, user, userWeight);
    }

    public static LoginLoadUserWeightResponseMessage loginLoadUserWeight(LoginLoadRequestMessage loginLoadRequestMessage) {
        User user = loginLoadRequestMessage.getUser();
        System.out.println("!!!!!!!!!!!!!!!!!" + user.getUserId() + " " + user.getUserEmail());
        ArrayList<UserWeight> userWeightArrayList = UserWeightHandler.getAllUsersWeight(user);
        ArrayList<UserWeight> selectedUserWeigh = new ArrayList<>();
        int startIndex = loginLoadRequestMessage.getStartIndex();
        int finishIndex = startIndex + loginLoadRequestMessage.getIncrement();
        int totalArraySize = userWeightArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedUserWeigh.add(userWeightArrayList.get(i));
        }

        return new LoginLoadUserWeightResponseMessage(StatusEnum.LOGIN_LOAD_SUCCESS, user, totalArraySize, finishIndex, selectedUserWeigh );
    }

    public static EnterUserWeightResponseMessage enterUserWeight(EnterRequestMessage enterRequestMessage) {

        User user = enterRequestMessage.getUser();
        Date lastUpdatedAt = enterRequestMessage.getLastUpdatedAt();
        ArrayList<UserWeight> userWeightArrayList;

        if (enterRequestMessage.getOperation() == Operation.INSERT)
            userWeightArrayList = UserWeightHandler.generateMissingListForInsert(lastUpdatedAt,user);
        else
            userWeightArrayList = UserWeightHandler.generateMissingListForUpdate(lastUpdatedAt,user);

        ArrayList<UserWeight> selectedUserWeightArrayList = new ArrayList<>();

        int startIndex = enterRequestMessage.getStartIndex();
        int finishIndex = startIndex + enterRequestMessage.getIncrement();
        int totalArraySize = userWeightArrayList.size();

        if( finishIndex > totalArraySize) {
            finishIndex = totalArraySize;
        }

        for(int i = startIndex; i < finishIndex; i++){
            selectedUserWeightArrayList.add(userWeightArrayList.get(i));
        }

        return new EnterUserWeightResponseMessage(
                StatusEnum.ENTER_SUCCESS, user, totalArraySize, finishIndex, selectedUserWeightArrayList, enterRequestMessage.getOperation()
        );
    }

}
