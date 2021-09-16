package com.example.ticket.ui.pub;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ticket.R;

import org.jetbrains.annotations.NotNull;

public class PRecycleHolder extends RecyclerView.ViewHolder{

    public TextView name;
    public TextView locate;
    public TextView time;
    public TextView game;
    public TextView intro;
    public ImageView poster;
    public String posterSrc;

    public ImageView pub_heart;
    private boolean clicked =true;


    public PRecycleHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.pub_name);
        locate = (TextView) itemView.findViewById(R.id.pub_location);
        time = (TextView) itemView.findViewById(R.id.pub_time);
        game = (TextView) itemView.findViewById(R.id.pub_game);
        intro = (TextView) itemView.findViewById(R.id.pub_intro);
        poster = (ImageView) itemView.findViewById(R.id.pub_poster);

        pub_heart = (ImageView) itemView.findViewById(R.id.pub_heart);


        pub_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicked){
                    pub_heart.setImageResource(R.drawable.ic_heart_black);
                    clicked = false;
                }
                else {
                    clicked=true;
                    pub_heart.setImageResource(R.drawable.ic_heart_red);
                }
            }
        });

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PubDetailActivity.class);

                String passName = name.getText()+"";
                String passLocate = locate.getText()+"";
                String passTime = time.getText()+"";
                String passGame = game.getText()+"";
                String passIntro = intro.getText()+"";
//                boolean passClick = clicked;

                intent.putExtra("name", passName);
                intent.putExtra("locate", passLocate);
                intent.putExtra("time", passTime);
                intent.putExtra("game", passGame);
                intent.putExtra("intro",passIntro);
                intent.putExtra("poster", posterSrc);
//                intent.putExtra("like",passClick);
                v.getContext().startActivity(intent);
            }
        });



    }

    public void onBind(Pub data){
        Glide.with(itemView).load(data.getPoster()).into(poster);
        locate.setText(data.getPlace());
        time.setText(data.getTime());
        name.setText(data.getName());
        game.setText(data.getGame());
        intro.setText(data.getIntro());
        posterSrc = data.getPoster();

    }


}
