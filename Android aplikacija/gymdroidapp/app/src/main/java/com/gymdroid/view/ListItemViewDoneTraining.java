package com.gymdroid.view;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gymdroid.R;
import com.gymdroid.services.AllServices;

public class ListItemViewDoneTraining extends LinearLayout {

    AllServices allServices;
    TextView numerationTextView;
    TextView timeTextView;
    TextView dateTextView;

    public ListItemViewDoneTraining(final Activity activity, ViewGroup parent, String date, String time, final int index) {
        super(activity);

        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate( R.layout.view_details_done_training, parent, false);
        addView(linearLayout);

        if(index % 2 == 0) {
            linearLayout.setBackgroundColor(Color.parseColor("#F0F2F6"));
        }

        allServices = AllServices.getInstance(activity);

        if (index != -1) {
            String numeration = String.valueOf(index + 1) + ".";
            numerationTextView = findViewById(R.id.numerationTextView);
            numerationTextView.setText(numeration);
        }

        dateTextView = findViewById(R.id.dateTextView);
        dateTextView.setText(date);

        timeTextView = findViewById(R.id.timeTextView);
        timeTextView.setText(time);

        if (index == -1) {
            dateTextView.setTypeface(dateTextView.getTypeface(), Typeface.BOLD);
            timeTextView.setTypeface(timeTextView.getTypeface(), Typeface.BOLD);
        }
    }

}