package com.example.android.mealplannerold.controller.adapters;

/**
 * Created by Elorri-user on 21/07/2015.
 */


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.android.mealplannerold.R;
import com.example.android.mealplannerold.controller.activities.MainActivity;

import com.example.android.mealplannerold.controller.interfaces.AppItemAdapterClickListenerInterface;
import com.example.android.mealplannerold.controller.interfaces.Item;
import com.example.android.mealplannerold.controller.holders.AppItemAdapterHolder;
import java.util.Collections;
import java.util.List;


public class AppItemAdapter extends RecyclerView.Adapter<AppItemAdapterHolder> {
    private MainActivity activity;
    private LayoutInflater inflater;
    private List<Item> data = Collections.emptyList();
    private AppItemAdapterClickListenerInterface appItemAdapterClickListenerInterface;

    public static final int SELECTION_MODE_SINGLE = 1;
    public int selectionMode;
    private AppItemAdapterHolder currentSelectedItemViewHolder = null;


    public AppItemAdapterHolder getCurrentSelectedItemViewHolder() {
        return currentSelectedItemViewHolder;
    }

    public AppItemAdapter(MainActivity context, List<Item> data, AppItemAdapterClickListenerInterface appItemAdapterClickListenerInterface, int selectionMode) {

        try {
            this.activity = context;
            this.inflater = LayoutInflater.from(context);
            this.data = data;
            this.appItemAdapterClickListenerInterface = appItemAdapterClickListenerInterface;
            this.selectionMode = selectionMode;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }


    public void setListData(List<Item> data) {
        this.data = data;
        notifyItemRangeChanged(0, data.size());
    }

    public Item removeItem(int position) {
        final Item model = data.remove(position);
        notifyItemRemoved(position);
        return model;
    }

    public void addItem(int position, Item model) {
        data.add(position, model);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final Item model = data.remove(fromPosition);
        data.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }

//    public void animateTo(List<Item> models) {
//        applyAndAnimateRemovals(models);
//        applyAndAnimateAdditions(models);
//        applyAndAnimateMovedItems(models);
//    }

//    private void applyAndAnimateRemovals(List<Item> newModels) {
//        for (int i = data.size() - 1; i >= 0; i--) {
//            final Item model = data.get(i);
//            if (!newModels.contains(model)) {
//                removeItem(i);
//            }
//        }
//    }
//
//    private void applyAndAnimateAdditions(List<Item> newModels) {
//        for (int i = 0, count = newModels.size(); i < count; i++) {
//            final Item model = newModels.get(i);
//            if (!data.contains(model)) {
//                addItem(i, model);
//            }
//        }
//    }
//
//    private void applyAndAnimateMovedItems(List<Item> newModels) {
//        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
//            final Item model = newModels.get(toPosition);
//            final int fromPosition = data.indexOf(model);
//            if (fromPosition >= 0 && fromPosition != toPosition) {
//                moveItem(fromPosition, toPosition);
//            }
//        }
//    }


    @Override
    public AppItemAdapterHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        try {
            View view = inflater.inflate(R.layout.holder_row_app_layout, viewGroup, false);
            AppItemAdapterHolder holder = new AppItemAdapterHolder(view, this, activity, appItemAdapterClickListenerInterface);
            return holder;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }

    @Override
    public void onBindViewHolder(AppItemAdapterHolder viewHolder, int i) {
        try {
            Item current = data.get(i);
            viewHolder.getIcon().setImageResource(current.getIconId());
            viewHolder.getIcon_letter().setText(current.getIconLetter());
            viewHolder.getTitle().setText(current.getTitle());
            viewHolder.getDesc().setText(current.getShortDesc());
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void delete(int position) {
        try {
            data.remove(position);
            notifyItemRemoved(position);
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }




    public void notifyItemHolderSelected(AppItemAdapterHolder holder) {
        if (selectionMode == AppItemAdapter.SELECTION_MODE_SINGLE) {
            if (currentSelectedItemViewHolder == null) {
                //Log.d("MealPlanner", "1 currentSelectedItemViewHolder :" + currentSelectedItemViewHolder + " holder :" + holder + " holder.isSelected :" + holder.isSelected);
                //aucune ligne n'est selectionn�e  laisser le toggle se faire normalement
                currentSelectedItemViewHolder = holder;
                return;
            }
            if (holder.equals(currentSelectedItemViewHolder) && (holder.isSelected())) {
                //il reselectionne la m�me ligne, apres le toggle la ligne sera deselectionn�e
                //Log.d("MealPlanner", "2 currentSelectedItemViewHolder :" + currentSelectedItemViewHolder + " holder :" + holder + " holder.isSelected :" + holder.isSelected);
                currentSelectedItemViewHolder = null;
                return;
            }
            if (holder.equals(currentSelectedItemViewHolder) && !(holder.isSelected())) {
                //il reselectionne la m�me ligne, apres le toggle la ligne sera selectionn�e
                //Log.d("MealPlanner", "3 currentSelectedItemViewHolder :" + currentSelectedItemViewHolder + " holder :" + holder + " holder.isSelected :" + holder.isSelected);
                currentSelectedItemViewHolder = holder;
                return;
            }
            //il faut faire le toggle sur la ligne precedement selectionn�e, puis faire le toggle normalement sur la ligne courante
            //Log.d("MealPlanner", "4 currentSelectedItemViewHolder :" + currentSelectedItemViewHolder + " holder :" + holder + " holder.isSelected :" + holder.isSelected());
            currentSelectedItemViewHolder.toggleSelect();
            currentSelectedItemViewHolder = holder;
        }


    }


}

