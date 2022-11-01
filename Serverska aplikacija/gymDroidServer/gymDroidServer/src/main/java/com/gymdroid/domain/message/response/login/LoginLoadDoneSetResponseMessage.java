package com.gymdroid.domain.message.response.login;

import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseLargeDataMessage;

import java.util.ArrayList;

public class LoginLoadDoneSetResponseMessage extends ResponseLargeDataMessage {

    private ArrayList<DoneSet> doneSetArrayList;

    public LoginLoadDoneSetResponseMessage(StatusEnum status, User user, int totalArraySize, int finishIndex, ArrayList<DoneSet> doneSetArrayList) {
        super(status, user, totalArraySize, finishIndex);
        this.doneSetArrayList = doneSetArrayList;
    }

    public ArrayList<DoneSet> getDoneSetArrayList() {
        return doneSetArrayList;
    }

    public void setDoneSetArrayList(ArrayList<DoneSet> doneSetArrayList) {
        this.doneSetArrayList = doneSetArrayList;
    }
}
