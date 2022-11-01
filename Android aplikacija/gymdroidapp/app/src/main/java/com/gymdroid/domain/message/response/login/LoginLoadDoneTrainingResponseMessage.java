package com.gymdroid.domain.message.response.login;

import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseLargeDataMessage;

import java.util.ArrayList;

public class LoginLoadDoneTrainingResponseMessage extends ResponseLargeDataMessage {

    private ArrayList<DoneTraining> doneTrainingArrayList;

    public LoginLoadDoneTrainingResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<DoneTraining> doneTrainingArrayList) {
        super(status, user, totalArraySize, finishIndex);
        this.doneTrainingArrayList = doneTrainingArrayList;
    }

    public ArrayList<DoneTraining> getDoneTrainingArrayList() {
        return doneTrainingArrayList;
    }

    public void setDoneTrainingArrayList(ArrayList<DoneTraining> doneTrainingArrayList) {
        this.doneTrainingArrayList = doneTrainingArrayList;
    }
}
