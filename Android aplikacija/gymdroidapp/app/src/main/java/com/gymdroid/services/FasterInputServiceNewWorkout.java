package com.gymdroid.services;

import android.app.Activity;
import android.widget.LinearLayout;

import com.gymdroid.domain.beans.Muscle;
import com.gymdroid.domain.faster.ChooseMuscle;
import com.gymdroid.view.CardViewMuscleDetails;

import java.util.ArrayList;

public class FasterInputServiceNewWorkout extends BaseService {

    private static final int FIRST_CARD_POSITION = 1;

    private ArrayList<ChooseMuscle> chooseMuscleArrayList;
    private ArrayList<CardViewMuscleDetails> cardViewMuscleDetails;
    private LinearLayout muscleDetailsLinearLayout;
    private Activity activity;

    public FasterInputServiceNewWorkout() {
        chooseMuscleArrayList = new ArrayList<>();
        cardViewMuscleDetails = new ArrayList<>();
    }

    public CardViewMuscleDetails initiate(Activity activity, LinearLayout muscleDetailsLinearLayout) {
        this.activity = activity;
        this.muscleDetailsLinearLayout = muscleDetailsLinearLayout;

        ArrayList<Muscle> muscleArrayList = INSTANCE.getDatabase().getMuscleArrayList();
        for (int i = 0; i < muscleArrayList.size(); i++){
            chooseMuscleArrayList.add(new ChooseMuscle(muscleArrayList.get(i)));
        }
        CardViewMuscleDetails cardViewMuscleDetails = new CardViewMuscleDetails(activity, muscleDetailsLinearLayout, FIRST_CARD_POSITION, generateMuscles());
        chooseMuscleArrayList.get(0).chooseMuscle(FIRST_CARD_POSITION);

        this.cardViewMuscleDetails.add(cardViewMuscleDetails);
        return cardViewMuscleDetails;
    }

    public CardViewMuscleDetails createNewMuscleDetailsCard() {
        CardViewMuscleDetails cardViewMuscleDetails = new CardViewMuscleDetails(activity, muscleDetailsLinearLayout, this.cardViewMuscleDetails.size() + 1, generateMuscles());
        this.cardViewMuscleDetails.add(cardViewMuscleDetails);
        updatePreviousOnNewGeneratedCard();
        return cardViewMuscleDetails;
    }

    public CardViewMuscleDetails removeLastMuscleDetailsCard() {
        CardViewMuscleDetails cardViewMuscleDetails = this.cardViewMuscleDetails.remove(getChosenCount() - 1);
        updatePreviousOnRemovedLastCard(cardViewMuscleDetails.getPosition());
        return cardViewMuscleDetails;
    }

    public void selectNewMuscle(int cardPosition, int muscleId) {
        /// dismiss previous muscle
        for(int i = 0; i < chooseMuscleArrayList.size(); i++) {
            if(chooseMuscleArrayList.get(i).getCardPosition() == cardPosition){
                chooseMuscleArrayList.get(i).dismiss();
                break;
            }
        }
        /// choose new muscle
        for(int i = 0; i < chooseMuscleArrayList.size(); i++) {
            if(chooseMuscleArrayList.get(i).getMuscle().getMuscleId() == muscleId){
                chooseMuscleArrayList.get(i).chooseMuscle(cardPosition);
                break;
            }
        }
        updatePreviousOnNewGeneratedCard();
    }

    public boolean canMakeMoreCards() {
        return cardViewMuscleDetails.size() < chooseMuscleArrayList.size();
    }

    public boolean isOnlyOneMuscleDetailsCard() {
        return cardViewMuscleDetails.size() == 1;
    }

    public ArrayList<CardViewMuscleDetails> getCardViewMuscleDetails() {
        return cardViewMuscleDetails;
    }

    private ArrayList<Muscle> generateMuscles() {
        ArrayList<Muscle> muscleArrayList = new ArrayList<>();
        boolean isFirst = true;
        for(int i = 0; i < chooseMuscleArrayList.size(); i++) {
            if(!chooseMuscleArrayList.get(i).isChosen()) {
                muscleArrayList.add(chooseMuscleArrayList.get(i).getMuscle());
                if(isFirst) {
                    isFirst = false;
                    int cardPosition = getChosenCount() + 1;
                    chooseMuscleArrayList.get(i).chooseMuscle(cardPosition);
                }
            }
        }
        return muscleArrayList;
    }

    private int getChosenCount() {
        int count = 0;
        for (int i = 0; i < chooseMuscleArrayList.size(); i++) {
            if (chooseMuscleArrayList.get(i).isChosen())
                count++;
        }
        return count;
    }

    private void updatePreviousOnRemovedLastCard(int cardPosition) {
        for (int i = 0; i < chooseMuscleArrayList.size(); i++) {
            if (chooseMuscleArrayList.get(i).getCardPosition() == cardPosition) {
                chooseMuscleArrayList.get(i).dismiss();
            }
        }
        updatePreviousOnNewGeneratedCard();
    }

    private void updatePreviousOnNewGeneratedCard() {
        for (int i = 0; i < cardViewMuscleDetails.size(); i++) {
            CardViewMuscleDetails currentCard = cardViewMuscleDetails.get(i);
            int chosenForCurrentId = 0;
            int currentCardPosition = currentCard.getPosition();
            for(int j = 0; j < chooseMuscleArrayList.size(); j++) {
                if(chooseMuscleArrayList.get(j).getCardPosition() == currentCardPosition) {
                    chosenForCurrentId = j;
                }
            }
            ArrayList<Muscle> muscleArrayList = currentCard.getMuscleArrayList();
            muscleArrayList.clear();
            muscleArrayList.add(chooseMuscleArrayList.get(chosenForCurrentId).getMuscle());
            for(int j = 0; j < chooseMuscleArrayList.size(); j++) {
                if(!chooseMuscleArrayList.get(j).isChosen()) {
                    muscleArrayList.add(chooseMuscleArrayList.get(j).getMuscle());
                }
            }
            currentCard.fillMuscleSpinner(muscleArrayList);
        }
    }

    public void clearData() {
        chooseMuscleArrayList.clear();
        cardViewMuscleDetails.clear();
    }

}
