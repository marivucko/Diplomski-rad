package com.gymdroid.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.activities.base.WhiteActivity;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.EquipmentType;

import java.util.ArrayList;

public class AddEquipmentActivity extends WhiteActivity {

    int equipmentId;

    Button saveButton;
    EditText weightEditText;
    EditText countEditText;
    Spinner equipmentSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_add_equipment);

        equipmentSpinner = findViewById(R.id.equipmentSpinner);
        saveButton = findViewById(R.id.saveButton);
        countEditText = findViewById(R.id.countEditText);
        weightEditText = findViewById(R.id.weightEditText);

        fillMuscleSpinner(database.getEquipmentTypeArrayList());
        equipmentId = database.getEquipmentTypeArrayList().get(0).getEquipmentTypeId();

        equipmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView)parent.getChildAt(0);
                if (textView != null) textView.setTextColor(getResources().getColor(R.color.colorWhite));
                equipmentId = database.getEquipmentTypeArrayList().get(position).getEquipmentTypeId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weightString = weightEditText.getText().toString();
                if(allServices.getStringService().isStringEmpty(weightString)) {
                    weightEditText.setError(activity.getResources().getString(R.string.fill_data_errors_weight_cant_be_empty));
                    return;
                }

                String countString =  countEditText.getText().toString();
                if(allServices.getStringService().isStringEmpty(countString)) {
                    countEditText.setError(activity.getResources().getString(R.string.fill_data_errors_count_cant_be_empty));
                    return;
                }

                Equipment equipment = new Equipment();
                equipment.setEquipmentTypeId(equipmentId);

                try {
                    equipment.setEquipmentWeight(Double.parseDouble(weightString));
                } catch (Exception e) {
                    weightEditText.setError(activity.getResources().getString(R.string.fill_data_errors_not_valid_value));
                    return;
                }

                try {
                    equipment.setEquipmentCount(Integer.parseInt(countString));
                } catch (Exception e) {
                    countEditText.setError(activity.getResources().getString(R.string.fill_data_errors_not_valid_value));
                    return;
                }

                allServices.getEquipmentService().addEquipment(activity, equipment);

            }
        });

    }

    private void fillMuscleSpinner(ArrayList<EquipmentType> equipmentTypeArrayList) {
        ArrayList<String> equipmentArray = new ArrayList<>();
        for(int i = 0; i < equipmentTypeArrayList.size(); i++){
            equipmentArray.add("" + equipmentTypeArrayList.get(i).getEquipmentTypeName());
        }
        ArrayAdapter<String> muscleArrayAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, equipmentArray);
        equipmentSpinner.setAdapter(muscleArrayAdapter);
    }

}
