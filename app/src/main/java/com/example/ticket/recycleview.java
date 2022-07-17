package com.example.ticket;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycleview extends RecyclerView.Adapter<recycleview.ViewHolder> {
    ArrayList<String> Data_count ;
    ArrayList<String> Data_price;

    public recycleview(ArrayList<String> count,ArrayList<String> price){
        Data_count =count;
        Data_price =price;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_name,tv_price;
        Button btn_clear;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name  = (TextView) itemView.findViewById(R.id.tv_show);
            tv_price =(TextView)itemView.findViewById(R.id.tv_price);
            btn_clear = (Button) itemView.findViewById(R.id.button8);
        }
    }

    public void remove(int position){

        Data_count.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.style,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        holder.tv_name.setText(Data_count.get(position));
        holder.tv_price.setText(Data_price.get(position));
        holder.btn_clear.setOnClickListener(v -> recycleview.this.remove(position));

    }

    @Override
    public int getItemCount() {
        return (Data_count.size());
    }

}
