package com.example.ticket.ui.schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<Schedule> listData = new ArrayList<>();


    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item, parent, false);

        return new SRecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ((SRecycleHolder)holder).onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(Schedule data){
        listData.add(data);
    }
}


