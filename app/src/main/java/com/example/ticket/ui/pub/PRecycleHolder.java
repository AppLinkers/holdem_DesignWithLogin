package com.example.ticket.ui.pub;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;

import org.jetbrains.annotations.NotNull;

public class PRecycleHolder extends RecyclerView.ViewHolder{

    public TextView name;
    public TextView locate;
    public TextView time;
    public TextView game;
    public String intro;


    public PRecycleHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.pub_name);
        locate = (TextView) itemView.findViewById(R.id.pub_location);
        time = (TextView) itemView.findViewById(R.id.pub_time);
        game = (TextView) itemView.findViewById(R.id.pub_game);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PubDetailActivity.class);

                String passName = name.getText()+"";
                String passLocate = locate.getText()+"";
                String passTime = time.getText()+"";
                String passGame = game.getText()+"";

                intent.putExtra("name", passName);
                intent.putExtra("locate", passLocate);
                intent.putExtra("time", passTime);
                intent.putExtra("game", passGame);
                intent.putExtra("intro",intro);

                v.getContext().startActivity(intent);
            }
        });

    }

    public void onBind(Pub data){
        locate.setText(data.getPlace());
        time.setText(data.getTime());
        name.setText(data.getName());
        game.setText(data.getGame());
        intro = data.getIntro();
    }


}
