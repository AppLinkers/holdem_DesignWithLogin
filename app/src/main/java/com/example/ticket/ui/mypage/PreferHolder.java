package com.example.ticket.ui.mypage;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ui.pub.Pub;

import org.jetbrains.annotations.NotNull;

public class PreferHolder extends RecyclerView.ViewHolder {

    TextView preferPub;
    TextView preferLocate;
    public PreferHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        preferPub = itemView.findViewById(R.id.prefer);
        preferLocate = itemView.findViewById(R.id.preferLocate);
    }


    public void onBind(Pub data){

        preferPub.setText(data.getName());
        preferLocate.setText(data.getPlace());

    }
}
