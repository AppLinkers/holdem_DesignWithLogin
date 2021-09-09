package com.example.ticket.ui.ticket;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ticket.ChatActivity;
import com.example.ticket.R;
import com.example.ticket.ui.pub.Pub;
import com.example.ticket.ui.pub.PubDetailActivity;

import org.jetbrains.annotations.NotNull;

public class TRecycleHolder extends RecyclerView.ViewHolder{

    public TextView name;
    public TextView place;
    public TextView price;
    public ImageView poster;



    public TRecycleHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.ticket_name);
        place = (TextView) itemView.findViewById(R.id.ticket_place);
        price = (TextView) itemView.findViewById(R.id.ticke_price);
        poster = (ImageView) itemView.findViewById(R.id.ticket_poster);


        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
               Intent intent = new Intent(v.getContext(), ChatActivity.class);
               v.getContext().startActivity(intent);
            }
        });
    }

    public void onBind(Ticket data){
        price.setText(data.getPrice());
        place.setText(data.getPlace());
        name.setText(data.getName());

        Glide.with(itemView).load(data.getPoster()).into(poster);

    }


}
