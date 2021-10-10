package com.example.ticket.ui.mypage;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ui.pub.Pub;

public class MySellHolder extends RecyclerView.ViewHolder  {

    TextView sellChat;
    TextView sellLocate;
    Button delSellBtn;

    public MySellHolder(@NonNull View itemView) {
        super(itemView);

        sellChat = itemView.findViewById(R.id.sellChat);
        sellLocate = itemView.findViewById(R.id.sellLocate);
        delSellBtn = itemView.findViewById(R.id.delSellBtn);
        delSellBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    public void onBind(Pub data){

        sellChat.setText(data.getName());
        sellLocate.setText(data.getPlace());

    }
}
