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
import com.gymdroid.domain.beans.UserWeight;
import com.gymdroid.services.AllServices;

@SuppressLint("ViewConstructor")
public class ListItemViewWeight extends LinearLayout {

    AllServices allServices;
    TextView numerationTextView;
    TextView weightTextView;
    TextView dateTextView;
    ImageButton detailsButton;

    public ListItemViewWeight(final Activity activity, ViewGroup parent, UserWeight userWeight, final int index) {
        super(activity);

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_weight_list_item, parent, false);
        addView(linearLayout);

        if(index % 2 == 0) {
            linearLayout.setBackgroundColor(Color.parseColor("#F0F2F6"));
        }

        allServices = AllServices.getInstance(activity);

        String numeration = String.valueOf(index + 1) + ".";
        numerationTextView = findViewById(R.id.numerationTextView);
        numerationTextView.setText(numeration);

        String weight = String.valueOf(userWeight.getWeight()) + activity.getResources().getString(R.string._kg);
        weightTextView = findViewById(R.id.weightTextView);
        weightTextView.setText(weight);

        dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(allServices.getCalendarService().dateToStringOnlyDateDMY(userWeight.getWeightDate()));

        detailsButton = findViewById(R.id.detailsButton);
        detailsButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                allServices.getStartActivityService().startUserWeightDetailsActivity(activity,index);
            }
        });
    }

}
