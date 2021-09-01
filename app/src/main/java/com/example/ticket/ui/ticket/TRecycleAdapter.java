package com.example.ticket.ui.ticket;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ui.pub.PRecycleHolder;
import com.example.ticket.ui.pub.Pub;

import java.util.ArrayList;

public class TRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<Ticket> listData = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item, parent, false);

        return new TRecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TRecycleHolder)holder).onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(Ticket data){
        listData.add(data);
    }

}
