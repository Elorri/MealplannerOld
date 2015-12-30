package com.example.android.mealplannerold.controller.holders;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.android.mealplannerold.R;
import com.example.android.mealplannerold.controller.activities.MainActivity;
import com.example.android.mealplannerold.controller.adapters.AppItemAdapter;
import com.example.android.mealplannerold.controller.interfaces.AppItemAdapterClickListenerInterface;

/**
 * Created by Elorri-user on 03/08/2015.
 */

public class AppItemAdapterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    MainActivity activity;
    AppItemAdapter adapter;
    AppItemAdapterClickListenerInterface clickListener;
    boolean isSelected = false;
    LinearLayout layout;
    ImageView icon;
    TextView icon_letter;
    TextView title;
    TextView desc;


    public AppItemAdapterHolder(View itemView, AppItemAdapter adapter, MainActivity activity, AppItemAdapterClickListenerInterface clickListener) {
        super(itemView);
        try {
            this.activity=activity;
            this.adapter = adapter;
            this.clickListener=clickListener;
            layout = (LinearLayout) itemView.findViewById(R.id.row_layout);
            icon = (ImageView) itemView.findViewById(R.id.row_icon);
            icon_letter = (TextView) itemView.findViewById(R.id.row_icon_letter);
            title = (TextView) itemView.findViewById(R.id.row_title);
            desc = (TextView) itemView.findViewById(R.id.row_desc);

            layout.setOnClickListener(this);
            icon.setOnClickListener(this);
            icon_letter.setOnClickListener(this);
            title.setOnClickListener(this);
            desc.setOnClickListener(this);
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }

    public void toggleSelect() {
        if (isSelected == false) {
            layout.setBackgroundColor(activity.getResources().getColor(R.color.accent_light));
            isSelected = true;
        } else {
            layout.setBackgroundColor(activity.getResources().getColor(R.color.white));
            isSelected = false;
        }
    }

    public boolean isSelected() {
        return isSelected;
    }


    @Override
    public void onClick(View v) {
        try {
            adapter.notifyItemHolderSelected(this);
            toggleSelect();
            clickListener.itemClicked(v, getPosition());
            clickListener.itemDetailClicked(v, getPosition());
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }

    public ImageView getIcon() {
        return icon;
    }

    public TextView getIcon_letter() {
        return icon_letter;
    }

    public TextView getTitle() {
        return title;
    }

    public TextView getDesc() {
        return desc;
    }
}