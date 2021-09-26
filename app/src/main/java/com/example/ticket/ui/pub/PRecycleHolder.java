package com.example.ticket.ui.pub;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ticket.R;
import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.HoldemPub;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

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

    DataService dataService = new DataService();

    String user_name = "test";
    Long holdemPub_id = 0l;

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
                Toast.makeText(itemView.getContext(),user_name,Toast.LENGTH_SHORT).show();
                setData();
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
                boolean passClick = clicked;

                intent.putExtra("name", passName);
                intent.putExtra("locate", passLocate);
                intent.putExtra("time", passTime);
                intent.putExtra("game", passGame);
                intent.putExtra("intro",passIntro);
                intent.putExtra("poster", posterSrc);
                intent.putExtra("like",passClick);
                v.getContext().startActivity(intent);
            }
        });



    }
    @SuppressLint({"StaticFieldLeak", "NewApi"})
    public void setData(){
        if (clicked){
            // 싫어하기 클릭
            AsyncTask<Void, Void, HoldemPub> listAPI = new AsyncTask<Void, Void, HoldemPub>() {
                @Override
                protected HoldemPub doInBackground(Void... params) {
                    Call<HoldemPub> call = dataService.holdemPubs.unLikeHoldemPub(holdemPub_id,user_name);
                    try {
                        return call.execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(HoldemPub s) {
                    super.onPostExecute(s);
                }
            }.execute();

            pub_heart.setImageResource(R.drawable.ic_heart_black);
            clicked = false;
        }
        else {
            // 좋아하기 클릭
            AsyncTask<Void, Void, HoldemPub> listAPI = new AsyncTask<Void, Void, HoldemPub>() {
                @Override
                protected HoldemPub doInBackground(Void... params) {
                    Log.d("holdemList", holdemPub_id.toString());
                  //  Log.d("holdemList", user_name);
                    Call<HoldemPub> call = dataService.holdemPubs.likeHoldemPub(holdemPub_id,user_name);
                    try {
                        return call.execute().body();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(HoldemPub s) {
                    super.onPostExecute(s);
                }
            }.execute();

            clicked=true;
            pub_heart.setImageResource(R.drawable.ic_heart_red);
        }

    }

    public void onBind(Pub data, String id) {
        Glide.with(itemView).load(data.getPoster()).into(poster);
        locate.setText(data.getPlace());
        time.setText(data.getTime());
        name.setText(data.getName());
        game.setText(data.getGame());
        intro.setText(data.getIntro());
        posterSrc = data.getPoster();
        holdemPub_id = data.getId();
        user_name = id;

        if (data.isClicked()) {
            pub_heart.setImageResource(R.drawable.ic_heart_black);
        } else {
            pub_heart.setImageResource(R.drawable.ic_heart_red);
        }
    }


}
