package com.gymdroid.fragments.main;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.gymdroid.R;
import com.gymdroid.dao.Database;
import com.gymdroid.services.AllServices;
import com.gymdroid.view.ListItemViewEquipment;

public class EquipmentFragment extends Fragment {

    private RelativeLayout masterLayout;
    private LinearLayout equipmentListView;
    private Activity activity;
    private AllServices allServices;
    private Database database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_equipment, container, false);

        activity = (Activity) rootView.getContext();
        allServices = AllServices.getInstance(activity);
        database = allServices.getDatabase();
        masterLayout = rootView.findViewById(R.id.masterLayout);
        equipmentListView = rootView.findViewById(R.id.equipmentListView);

        for (int i = 0; i < database.getEquipmentArrayList().size(); i++) {
            equipmentListView.addView(new ListItemViewEquipment(activity,masterLayout,database.getEquipmentArrayList().get(i),i));
        }

        Button addEquipmentButton = rootView.findViewById(R.id.addEquipmentButton);
        addEquipmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getStartActivityService().startAddEquipmentActivity(activity, masterLayout);
            }
        });

        return rootView;
    }

}
