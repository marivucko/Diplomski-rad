package com.gymdroid.domain.faster;

import com.gymdroid.domain.beans.Muscle;

public class ChooseMuscle {

    private Muscle muscle;
    private boolean isChosen;
    private int cardPosition;

    public ChooseMuscle(Muscle muscle) {
        this.muscle = muscle;
        this.isChosen = false;
        this.cardPosition = 0;
    }

    public Muscle getMuscle() {
        return muscle;
    }

    public void setMuscle(Muscle muscle) {
        this.muscle = muscle;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public int getCardPosition() {
        return cardPosition;
    }

    public void chooseMuscle(int cardPosition) {
        isChosen = true;
        this.cardPosition = cardPosition;
    }

    public void dismiss() {
        isChosen = false;
        this.cardPosition = 0;
    }

}
