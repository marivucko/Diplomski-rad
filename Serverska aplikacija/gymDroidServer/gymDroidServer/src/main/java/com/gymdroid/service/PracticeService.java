package com.gymdroid.service;

import com.gymdroid.dao.DoneSetHandler;
import com.gymdroid.dao.DoneTrainingHandler;
import com.gymdroid.dao.DoneWorkoutHandler;
import com.gymdroid.domain.beans.*;
import com.gymdroid.domain.message.StatusEnum;
import com.gymdroid.domain.message.request.AddMultiplePracticesRequestMessage;
import com.gymdroid.domain.message.request.PracticeAddRequestMessage;
import com.gymdroid.domain.message.response.AddMultiplePracticesResponseMessage;
import com.gymdroid.domain.message.response.PracticeAddResponseMessage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PracticeService {

    public static PracticeAddResponseMessage addPractice(PracticeAddRequestMessage practiceAddRequestMessage) {
        User user = practiceAddRequestMessage.getUser();
        DoneWorkout doneWorkout = practiceAddRequestMessage.getDoneWorkout();
        ArrayList<DoneSet> doneSetArrayList = practiceAddRequestMessage.getDoneSetArrayList();

        DoneWorkoutHandler.insertDoneWorkout(doneWorkout);

        for (DoneSet doneSet : doneSetArrayList) {
            doneSet.setDoneWorkoutId(doneWorkout.getDoneWorkoutId());
        }

        for (DoneSet doneSet : doneSetArrayList) {
            DoneSetHandler.insertDoneSet(doneSet);
        }

        return new PracticeAddResponseMessage(StatusEnum.ADD_PRACTICE_SUCCESS,user,doneSetArrayList,doneWorkout);
    }

    public static AddMultiplePracticesResponseMessage addMultiplePractice(AddMultiplePracticesRequestMessage multiplePracticeAddRequestMessage) {

        User user = multiplePracticeAddRequestMessage.getUser();
        ArrayList<DoneWorkout> doneWorkoutArrayList = multiplePracticeAddRequestMessage.getDoneWorkoutArrayList();
        HashMap<Integer, ArrayList<DoneSet>> doneSetHashMap = multiplePracticeAddRequestMessage.getDoneSetHashMap();

        for (int i = 0; i < doneWorkoutArrayList.size(); i++) {
            DoneWorkoutHandler.insertDoneWorkout(doneWorkoutArrayList.get(i));
            for (int j = 0; j < doneSetHashMap.get(i).size(); j++) {
                DoneSet doneSet = doneSetHashMap.get(i).get(j);
                doneSet.setDoneWorkoutId(doneWorkoutArrayList.get(i).getDoneWorkoutId());
                DoneSetHandler.insertDoneSet(doneSet);
            }

        }

        Training training = multiplePracticeAddRequestMessage.getTraining();
        DoneTraining doneTraining = new DoneTraining();
        doneTraining.setTrainingId(training.getTrainingId());
        doneTraining.setDoneTrainingAt(new Date());
        doneTraining = DoneTrainingHandler.insertDoneTraining(doneTraining);

        return new AddMultiplePracticesResponseMessage(StatusEnum.ADD_MULTIPLE_PRACTICES_SUCCESS, user, doneWorkoutArrayList, doneSetHashMap, doneTraining);
    }
}
