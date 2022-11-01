package com.gymdroid.domain.message.response;

import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneTraining;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.response.core.ResponseMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class AddMultiplePracticesResponseMessage extends ResponseMessage {

    private ArrayList<DoneWorkout> doneWorkoutArrayList;
    private HashMap<Integer,ArrayList<DoneSet>> doneSetHashMap;
    private DoneTraining doneTraining;

    public AddMultiplePracticesResponseMessage(StatusEnum status, User user, ArrayList<DoneWorkout> doneWorkoutArrayList, HashMap<Integer, ArrayList<DoneSet>> doneSetHashMap, DoneTraining doneTraining) {
        super(status, user);
        this.doneWorkoutArrayList = doneWorkoutArrayList;
        this.doneSetHashMap = doneSetHashMap;
        this.doneTraining = doneTraining;
    }

    public ArrayList<DoneWorkout> getDoneWorkoutArrayList() {
        return doneWorkoutArrayList;
    }

    public void setDoneWorkoutArrayList(ArrayList<DoneWorkout> doneWorkoutArrayList) {
        this.doneWorkoutArrayList = doneWorkoutArrayList;
    }

    public HashMap<Integer, ArrayList<DoneSet>> getDoneSetHashMap() {
        return doneSetHashMap;
    }

    public void setDoneSetHashMap(HashMap<Integer, ArrayList<DoneSet>> doneSetHashMap) {
        this.doneSetHashMap = doneSetHashMap;
    }

    public DoneTraining getDoneTraining() {
        return doneTraining;
    }

    public void setDoneTraining(DoneTraining doneTraining) {
        this.doneTraining = doneTraining;
    }
}
