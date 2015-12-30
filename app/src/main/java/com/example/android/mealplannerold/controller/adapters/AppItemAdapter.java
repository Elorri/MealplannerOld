package com.example.android.mealplannerold.controller.adapters;

/**
 * Created by Elorri-user on 21/07/2015.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.mealplannerold.R;
import com.example.android.mealplannerold.controller.interfaces.Item;

import java.util.Collections;
import java.util.List;


public class AppItemAdapter extends RecyclerView.Adapter<AppItemAdapter.MyViewHolder> {
    private Context context;
    private LayoutInflater inflater;
    private List<Item> data = Collections.emptyList();
    private ClickListener clickListener;

    public AppItemAdapter(Context context, List<Item> data, ClickListener clickListener) {
        try {
            this.context = context;
            this.inflater = LayoutInflater.from(context);
            this.data = data;
            this.clickListener = clickListener;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }


    public void setListData(List<Item> data){
        this.data=data;
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
    public void animateTo(List<Item> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }

    private void applyAndAnimateRemovals(List<Item> newModels) {
        for (int i = data.size() - 1; i >= 0; i--) {
            final Item model = data.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<Item> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Item model = newModels.get(i);
            if (!data.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<Item> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Item model = newModels.get(toPosition);
            final int fromPosition = data.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        try {
            View view = inflater.inflate(R.layout.holder_row_app_layout, viewGroup, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        try {
            Item current = data.get(i);
            viewHolder.icon.setImageResource(current.getIconId());
            viewHolder.title.setText(current.getTitle());
            viewHolder.desc.setText(current.getShortDesc());
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


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView icon;
        TextView title;
        TextView desc;


        public MyViewHolder(View itemView) {
            super(itemView);
            try {
                icon = (ImageView) itemView.findViewById(R.id.row_icon);
                title = (TextView) itemView.findViewById(R.id.row_title);
                desc = (TextView) itemView.findViewById(R.id.row_desc);

                icon.setOnClickListener(this);
                title.setOnClickListener(this);
                desc.setOnClickListener(this);
            } catch (Exception e) {
                Log.e("MealPlanner", Log.getStackTraceString(e));
            }
        }


        @Override
        public void onClick(View v) {
            try {
                Toast.makeText(context, "You selected item " + getPosition(), Toast.LENGTH_SHORT).show();
                //Avoid the below line. Only Activities or Fragments should starts others Activities. Not Adaptaters...  Use interface ClickListener instead.
                //context.startActivity(new Intent(context, ActivitySub.class));
                clickListener.itemClicked(v, getPosition());
            } catch (Exception e) {
                Log.e("MealPlanner", Log.getStackTraceString(e));
            }
        }
    }

    public interface ClickListener {
        void itemClicked(View view, int position);

    }
}

