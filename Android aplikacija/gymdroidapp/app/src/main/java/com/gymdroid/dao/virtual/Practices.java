package com.gymdroid.dao.virtual;

import com.gymdroid.domain.beans.DoneSet;
import com.gymdroid.domain.beans.DoneWorkout;
import com.gymdroid.domain.virtual.Practice;

import java.util.ArrayList;

public class Practices {

    private ArrayList<Practice> practiceArrayList;

    public Practices(ArrayList<DoneWorkout> doneWorkoutArrayList, ArrayList<DoneSet> doneSetArrayList) {
        practiceArrayList = new ArrayList<>();
        for(int i = 0; i < doneWorkoutArrayList.size(); i++) {
            Practice practice = new Practice();
            DoneWorkout currentDoneWorkout = doneWorkoutArrayList.get(i);
            practice.setDoneWorkout(currentDoneWorkout);
            for (int j = 0; j < doneSetArrayList.size(); j++) {
                DoneSet currentDoneSet = doneSetArrayList.get(j);
                if(currentDoneWorkout.getDoneWorkoutId() == currentDoneSet.getDoneWorkoutId()) {
                    practice.addDoneSet(currentDoneSet);
                }
            }
            practiceArrayList.add(practice);
        }

        for(int i = 0; i < practiceArrayList.size(); i++) {
            for(int j = i + 1; j < practiceArrayList.size(); j++) {
                if(practiceArrayList.get(i).getDoneWorkout().getDoneWorkoutDate().before(practiceArrayList.get(j).getDoneWorkout().getDoneWorkoutDate())) {
                    Practice tempPractice = practiceArrayList.get(i);
                    practiceArrayList.set(i,practiceArrayList.get(j));
                    practiceArrayList.set(j,tempPractice);
                }
            }
        }
    }

    public void addPractice(DoneWorkout doneWorkout, ArrayList<DoneSet> doneSetArrayList) {
        Practice practice = new Practice();
        practice.setDoneWorkout(doneWorkout);
        practice.setDoneSetArrayList(doneSetArrayList);

        boolean isNotAdded = true;

        for(int i = 0; i < practiceArrayList.size(); i++) {
            if(doneWorkout.getDoneWorkoutDate().after(practiceArrayList.get(i).getDoneWorkout().getDoneWorkoutDate())) {
                practiceArrayList.add(i,practice);
                isNotAdded = false;
                break;
            }
        }

        if(isNotAdded) {
            practiceArrayList.add(practice);
        }

    }

    public ArrayList<Practice> getPracticeArrayList() {
        return practiceArrayList;
    }

}
