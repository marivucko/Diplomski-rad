package com.gymdroid.domain.message.response.enter;

import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.Operation;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.EnterResponseLargeDateMessage;


import java.util.ArrayList;

public class EnterDoneSetResponseMessage extends EnterResponseLargeDateMessage {

    private ArrayList<DoneSet> doneSetArrayList;

    public EnterDoneSetResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<DoneSet> doneSetArrayList, Operation operation) {
        super(status, user, totalArraySize, finishIndex, operation);
        this.doneSetArrayList = doneSetArrayList;
    }

    public ArrayList<DoneSet> getDoneSetArrayList() {
        return doneSetArrayList;
    }

    public void setDoneSetArrayList(ArrayList<DoneSet> doneSetArrayList) {
        this.doneSetArrayList = doneSetArrayList;
    }
}
