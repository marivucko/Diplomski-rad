package com.gymdroid.domain.message.request;

import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.beans.Training;
import com.gymdroid.domain.beans.User;
import com.gymdroid.domain.message.request.core.RequestMessage;

import java.util.ArrayList;
import java.util.HashMap;

public class AddMultiplePracticesRequestMessage extends RequestMessage {

    private ArrayList<DoneWorkout> doneWorkoutArrayList;
    private HashMap<Integer,ArrayList<DoneSet>> doneSetHashMap;
    private Training training;

    public AddMultiplePracticesRequestMessage(User user, ArrayList<DoneWorkout> doneWorkoutArrayList, HashMap<Integer, ArrayList<DoneSet>> doneSetHashMap, Training training) {
        super(user);
        this.doneWorkoutArrayList = doneWorkoutArrayList;
        this.doneSetHashMap = doneSetHashMap;
        this.training = training;
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

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

}
