package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;

import java.util.ArrayList;

public class EnterDoneTrainingResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<DoneTraining> doneTrainingArrayList;

    public EnterDoneTrainingResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<DoneTraining> doneTrainingArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.doneTrainingArrayList = doneTrainingArrayList;
    }

    public ArrayList<DoneTraining> getDoneTrainingArrayList() {
        return doneTrainingArrayList;
    }

    public void setDoneTrainingArrayList(ArrayList<DoneTraining> doneTrainingArrayList) {
        this.doneTrainingArrayList = doneTrainingArrayList;
    }
}
