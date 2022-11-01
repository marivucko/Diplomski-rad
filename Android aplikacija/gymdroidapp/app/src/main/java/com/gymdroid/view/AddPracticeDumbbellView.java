package com.gymdroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.domain.beans.Equipment;

import java.util.ArrayList;

@SuppressLint("ViewConstructor")
public class AddPracticeDumbbellView extends LinearLayout {

    Activity activity;
    TextView totalSumEditText;
    Spinner quantitySpinner;

    ArrayList<String> quantityArrayList;
    ArrayList<Equipment> equipmentArrayList;

    double weight = 0;
    double previousWeight = 0;

    public AddPracticeDumbbellView(final Activity activity, ViewGroup parent, final TextView totalSumEditText, ArrayList<Equipment> equipmentArrayList) {
        super(activity);
        this.activity = activity;
        this.totalSumEditText = totalSumEditText;
        this.equipmentArrayList = equipmentArrayList;

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_add_practice_dumbbell, parent, false);
        addView(linearLayout);

        quantitySpinner = findViewById(R.id.quantitySpinner);
        quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedWeight = quantityArrayList.get(position).replace(activity.getResources().getString(R.string._kg), "");
                weight = Double.parseDouble(selectedWeight);
                double totalWeight  = Double.parseDouble(totalSumEditText.getText().toString()) + weight - previousWeight;
                totalSumEditText.setText(String.valueOf(totalWeight));
                previousWeight = weight;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fillEquipmentArrayList();
    }

    private void fillEquipmentArrayList() {
        quantityArrayList = new ArrayList<>();
        quantityArrayList.add("0.0 " + activity.getResources().getString(R.string._kg));
        for(int i = 0; i < equipmentArrayList.size(); i++) {
            String currentWeight = String.valueOf(equipmentArrayList.get(i).getEquipmentWeight()) + activity.getResources().getString(R.string._kg);
            boolean notExist = true;
            for(int j = 0; j < i; j++) {
                if(currentWeight.equals(String.valueOf(equipmentArrayList.get(j).getEquipmentWeight()) + activity.getResources().getString(R.string._kg))){
                    notExist = false;
                    break;
                }
            }
            if(notExist) {
                quantityArrayList.add(currentWeight);
            }
        }
        ArrayAdapter<String> workoutArrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, quantityArrayList);
        quantitySpinner.setAdapter(workoutArrayAdapter);
    }

    public double getWeight() {
        return weight;
    }
}
