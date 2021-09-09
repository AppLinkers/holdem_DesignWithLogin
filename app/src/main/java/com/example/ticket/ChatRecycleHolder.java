package com.example.ticket;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


public class ChatRecycleHolder extends RecyclerView.ViewHolder {

    public TextView chatIDTv;
    public TextView chatTv;
    public ChatRecycleHolder(View view) {
        super(view);

        chatIDTv = (TextView) view.findViewById(R.id.chatID);
        chatTv = (TextView) view.findViewById(R.id.chat);



    }

    public void onBind(Chat chat){
        chatIDTv.setText(chat.getChatID());
        chatTv.setText(chat.getChat());
    }
}
