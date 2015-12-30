package com.example.android.mealplannerold.controller.adapters;

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

/**
 * Created by Elorri-user on 02/08/2015.
 */
public class NavigationDrawerItemAdapter extends RecyclerView.Adapter<NavigationDrawerItemAdapter.MyViewHolder>{
    private Context context;



    private  LayoutInflater inflater;
    private List<Item> data= Collections.emptyList();
    private ClickListener clickListener;



    public NavigationDrawerItemAdapter(Context context, List<Item> data, ClickListener clickListener){
        this.context=context;
        this.inflater= LayoutInflater.from(context);
        this.data=data;
        this.clickListener=clickListener;
    }

    public interface ClickListener{
        public void itemClicked(View view, int position);
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title= (TextView) itemView.findViewById(R.id.list_text);
            icon= (ImageView) itemView.findViewById(R.id.list_icon);
            icon.setOnClickListener(this);
            title.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            try{
            Toast.makeText(context, "in NavigationDrawerItemAdapter You selected item " + getPosition(), Toast.LENGTH_SHORT).show();
            clickListener.itemClicked(v, getPosition());
            } catch (Exception e) {
                Log.e("MealPlanner", Log.getStackTraceString(e));
            }
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        try{
        View view=inflater.inflate(R.layout.holder_row_navigation_drawer_layout, parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
            return null;
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        try{
        Item current=data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
        } catch (Exception e) {
            Log.e("MealPlanner", Log.getStackTraceString(e));
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
