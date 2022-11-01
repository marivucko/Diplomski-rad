package com.gymdroid.domain.message.response;

import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

import java.util.ArrayList;

public class PracticeAddResponseMessage extends ResponseMessage {

    private ArrayList<DoneSet> doneSetArrayList;
    private DoneWorkout doneWorkout;

    public PracticeAddResponseMessage(StatusEnum status, User user, ArrayList<DoneSet> doneSetArrayList, DoneWorkout doneWorkout) {
        super(status, user);
        this.doneSetArrayList = doneSetArrayList;
        this.doneWorkout = doneWorkout;
    }

    public ArrayList<DoneSet> getDoneSetArrayList() {
        return doneSetArrayList;
    }

    public void setDoneSetArrayList(ArrayList<DoneSet> doneSetArrayList) {
        this.doneSetArrayList = doneSetArrayList;
    }

    public DoneWorkout getDoneWorkout() {
        return doneWorkout;
    }

    public void setDoneWorkout(DoneWorkout doneWorkout) {
        this.doneWorkout = doneWorkout;
    }
}
