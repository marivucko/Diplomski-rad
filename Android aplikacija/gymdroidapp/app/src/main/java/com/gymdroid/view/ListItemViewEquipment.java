package com.gymdroid.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.domain.beans.Equipment;
import com.gymdroid.domain.beans.EquipmentType;
import com.gymdroid.services.AllServices;

@SuppressLint("ViewConstructor")
public class ListItemViewEquipment extends LinearLayout {

    AllServices allServices;
    TextView numerationTextView;
    TextView nameTextView;
    TextView weightTextView;
    TextView countTextView;
    ImageButton deleteButton;

    public ListItemViewEquipment(final Activity activity, ViewGroup parent, Equipment equipment, final int index) {
        super(activity);

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_equipment_list_item, parent, false);
        addView(linearLayout);

        if(index % 2 == 0) {
            linearLayout.setBackgroundColor(Color.parseColor("#F0F2F6"));
        }

        allServices = AllServices.getInstance(activity);

        String numeration = String.valueOf(index + 1) + ".";
        numerationTextView = findViewById(R.id.numerationTextView);
        numerationTextView.setText(numeration);

        EquipmentType equipmentType = allServices.getDatabase().getEquipmentType(equipment);
        nameTextView = findViewById(R.id.nameTextView);
        nameTextView.setText(equipmentType.getEquipmentTypeName());

        String weight = String.valueOf(equipment.getEquipmentWeight()) + activity.getResources().getString(R.string._kg);
        weightTextView = findViewById(R.id.weightTextView);
        weightTextView.setText(weight);

        countTextView = findViewById(R.id.countTextView);
        countTextView.setText(" " + String.valueOf(equipment.getEquipmentCount()));

        deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                allServices.getEquipmentService().deleteEquipment(activity, allServices.getDatabase().getEquipmentArrayList().get(index));
            }
        });
    }

}