package com.gymdroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.domain.beans.Muscle;
import com.gymdroid.services.AllServices;
import com.gymdroid.services.FasterInputServiceNewWorkout;

import java.util.ArrayList;

@SuppressLint("ViewConstructor")
public class CardViewMuscleDetails extends RelativeLayout {

    private static final ArrayList<Integer> priorityArray;
    static {
        priorityArray = new ArrayList<>();
        priorityArray.add(3);
        priorityArray.add(2);
        priorityArray.add(1);
    }

    Activity activity;
    AllServices allServices;
    FasterInputServiceNewWorkout fasterInputServiceNewWorkout;
    ArrayList<Muscle> muscleArrayList;

    TextView cardTitleTextView;
    Spinner muscleSpinner;
    Spinner muscleTargetSpinner;

    private int position;
    private int muscleId = 0;
    private int previousId = 0;
    private int priority;

    public CardViewMuscleDetails(Context context, ViewGroup parent, final int position, ArrayList<Muscle> muscleArrayListNew) {
        super(context);
        this.position = position;
        this.muscleArrayList = muscleArrayListNew;

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.card_muscle_details, parent, false);
        addView(linearLayout);

        /// call necessary services
        activity = (Activity) context;
        allServices = AllServices.getInstance(activity);
        fasterInputServiceNewWorkout = allServices.getFasterInputServiceNewWorkout();

        /// set title of card
        final String cardTitle = activity.getString(R.string.muscle_card_title) + " " + position;
        cardTitleTextView = findViewById(R.id.cardTitleTextView);
        cardTitleTextView.setText(cardTitle);

        /// set muscle spinner data
        fillMuscleSpinner(muscleArrayList);
        muscleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                muscleId = muscleArrayList.get(i).getMuscleId();
                if(previousId != muscleId) {
                    fasterInputServiceNewWorkout.selectNewMuscle(position, muscleId);
                    previousId = muscleId;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /// set muscle target spinner data
        muscleTargetSpinner = findViewById(R.id.muscleTargetSpinner);
        ArrayList<String> muscleTarget = new ArrayList<>();
        muscleTarget.add(activity.getResources().getString(R.string.target_primary));
        muscleTarget.add(activity.getResources().getString(R.string.target_secondary));
        muscleTarget.add(activity.getResources().getString(R.string.target_ternary));
        setSpinnerData(muscleTargetSpinner, muscleTarget);
        muscleTargetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i < priorityArray.size()) {
                    priority = priorityArray.get(i);
                }
                else {
                    priority = 3;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void fillMuscleSpinner(ArrayList<Muscle> muscleArrayList) {
        this.muscleArrayList = muscleArrayList;
        muscleSpinner = findViewById(R.id.muscleSpinner);
        ArrayList<String> muscleArray = new ArrayList<>();
        for(int i = 0; i < muscleArrayList.size(); i++){
            muscleArray.add(muscleArrayList.get(i).getMuscleName());
        }
        setSpinnerData(muscleSpinner, muscleArray);
    }

    private void setSpinnerData(Spinner spinner, ArrayList<String> spinnerArray) {
        ArrayAdapter<String> muscleArrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerArray);
        spinner.setAdapter(muscleArrayAdapter);
    }

    public int getMuscleId() {
        return muscleId;
    }

    public int getPriority() {
        return priority;
    }

    public int getPosition() {
        return position;
    }

    public ArrayList<Muscle> getMuscleArrayList() {
        return muscleArrayList;
    }

}
