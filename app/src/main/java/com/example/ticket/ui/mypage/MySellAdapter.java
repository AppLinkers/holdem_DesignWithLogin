package com.example.ticket.ui.mypage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ui.pub.Pub;
import com.example.ticket.ui.ticket.Ticket;

import java.util.ArrayList;

public class MySellAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Ticket> listData = new ArrayList<>();


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mysell_item, parent, false);

        return new MySellHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MySellHolder)holder).onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(Ticket data){
        listData.add(data);
    }
}
