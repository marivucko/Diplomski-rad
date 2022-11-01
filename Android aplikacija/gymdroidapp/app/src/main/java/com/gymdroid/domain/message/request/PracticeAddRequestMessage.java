package com.gymdroid.domain.message.request;

import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.request.core.RequestMessage;

import java.util.ArrayList;

public class PracticeAddRequestMessage extends RequestMessage {

    private ArrayList<DoneSet> doneSetArrayList;
    private DoneWorkout doneWorkout;

    public PracticeAddRequestMessage(User user, ArrayList<DoneSet> doneSetArrayList, DoneWorkout doneWorkout) {
        super(user);
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
