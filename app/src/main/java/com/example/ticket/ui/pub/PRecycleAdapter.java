package com.example.ticket.ui.pub;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;

import java.util.ArrayList;

public class PRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<Pub> listData = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pub_item, parent, false);

        return new PRecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PRecycleHolder)holder).onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(Pub data){
        listData.add(data);
    }

}
