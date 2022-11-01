package com.gymdroid.domain.virtual;

import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;

import java.util.ArrayList;

public class Practice {

    private DoneWorkout doneWorkout;
    private ArrayList<DoneSet> doneSetArrayList;

    public Practice() {
        doneSetArrayList = new ArrayList<>();
    }

    public DoneWorkout getDoneWorkout() {
        return doneWorkout;
    }

    public void setDoneWorkout(DoneWorkout doneWorkout) {
        this.doneWorkout = doneWorkout;
    }

    public ArrayList<DoneSet> getDoneSetArrayList() {
        return doneSetArrayList;
    }

    public void addDoneSet(DoneSet doneSet) {
        this.doneSetArrayList.add(doneSet);
    }

    public void setDoneSetArrayList(ArrayList<DoneSet> doneSetArrayList) {
        this.doneSetArrayList = doneSetArrayList;
    }
}
