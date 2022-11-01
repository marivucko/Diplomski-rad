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
import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.services.AllServices;

import java.util.ArrayList;

@SuppressLint("ViewConstructor")
public class BarbellDetailsView extends LinearLayout {

    Activity activity;
    TextView equipmentNameTextView;
    TextView equipmentWeightTextView;
    TextView totalSumEditText;
    Spinner quantitySpinner;

    ArrayList<String> quantityArrayList;
    Equipment equipment;
    EquipmentType equipmentType;

    double weight = 0;
    double previousWeight = 0;

    public BarbellDetailsView(final Activity activity, ViewGroup parent, final TextView totalSumEditText, final Equipment equipment) {
        super(activity);
        this.activity = activity;
        this.equipment = equipment;
        this.equipmentType = AllServices.getInstance(activity).getDatabase().getEquipmentType(equipment);
        this.totalSumEditText = totalSumEditText;

        if(equipmentType != null) {
            LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_barbell_details, parent, false);
            addView(linearLayout);

            equipmentNameTextView = findViewById(R.id.equipmentNameTextView);
            equipmentNameTextView.setText(equipmentType.getEquipmentTypeName());

            equipmentWeightTextView = findViewById(R.id.equipmentWeightTextView);
            equipmentWeightTextView.setText(String.valueOf(equipment.getEquipmentWeight()) + activity.getResources().getString(R.string._kg));

            quantitySpinner = findViewById(R.id.quantitySpinner);
            quantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    weight = equipment.getEquipmentWeight() * Integer.parseInt(quantityArrayList.get(position));
                    double totalWeight  = Double.parseDouble(totalSumEditText.getText().toString()) + weight - previousWeight;
                    totalSumEditText.setText(String.valueOf(totalWeight));
                    previousWeight = weight;
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            fillWorkoutMuscle();
        }
    }

    private void fillWorkoutMuscle() {
        quantityArrayList = new ArrayList<>();
        for(int i = 0; i < equipment.getEquipmentCount() + 1; ) {
            quantityArrayList.add(String.valueOf(i++));
        }
        ArrayAdapter<String> workoutArrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, quantityArrayList);
        quantitySpinner.setAdapter(workoutArrayAdapter);
    }

    public double getWeight() {
        return weight;
    }
}
