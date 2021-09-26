package com.example.ticket.ui.mypage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ui.pub.PRecycleHolder;
import com.example.ticket.ui.pub.Pub;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PreferAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Pub> listData = new ArrayList<>();


    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.prefer_item, parent, false);

        return new PreferHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        ((PreferHolder)holder).onBind(listData.get(position));

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(Pub data){
        listData.add(data);
    }
}
