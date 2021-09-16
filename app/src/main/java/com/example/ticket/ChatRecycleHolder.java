package com.example.ticket;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;


public class ChatRecycleHolder extends RecyclerView.ViewHolder {

    public TextView chatIDTv;
    public TextView chatTv;
    public LinearLayout chatItem;






    public ChatRecycleHolder(View view) {
        super(view);

        chatIDTv = (TextView) view.findViewById(R.id.chatID);
        chatTv = (TextView) view.findViewById(R.id.chat);
        chatItem = view.findViewById(R.id.chatItem);



    }

    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void onBind(Chat chat,String user){
        String chatID = chatIDTv.getText()+"";

        chatIDTv.setText(chat.getChatID());
        chatTv.setText(chat.getChat());

        if(chat.getChatID().equals(user)) {
            chatItem.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

        }else{
            chatItem.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }

    }




}
