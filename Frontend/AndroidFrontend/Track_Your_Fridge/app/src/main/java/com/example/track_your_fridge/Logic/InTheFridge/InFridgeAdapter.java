package com.example.track_your_fridge.Logic.InTheFridge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.track_your_fridge.R;

import java.util.ArrayList;

public class InFridgeAdapter extends RecyclerView.Adapter<InFridgeAdapter.InFridgeViewHolder> {
    ArrayList<Food>  foodList;

    public static class InFridgeViewHolder extends RecyclerView.ViewHolder{
        TextView tv_food,tv_amount,tv_unit;
        public InFridgeViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_food = itemView.findViewById((R.id.texViewFood));
            tv_amount = itemView.findViewById(R.id.texViewAmount);
            tv_unit = itemView.findViewById(R.id.texViewUnit);
        }
    }

    public InFridgeAdapter(ArrayList<Food> foodList){
        this.foodList= foodList;
    }

    @NonNull
    @Override
    public InFridgeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exapmle_item,parent,false);
        InFridgeViewHolder ifvh = new InFridgeViewHolder(view);
        return ifvh;
    }

    @Override
    public void onBindViewHolder(@NonNull InFridgeViewHolder holder, int position) {
        Food cfood = foodList.get(position);

        holder.tv_food.setText(cfood.getName());
        holder.tv_amount.setText(Integer.toString(cfood.getAmount()));
        holder.tv_unit.setText(cfood.getUnit().name());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }


}
