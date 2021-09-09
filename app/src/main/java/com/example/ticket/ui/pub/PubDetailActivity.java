package com.example.ticket.ui.pub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ticket.R;


public class PubDetailActivity extends AppCompatActivity {
    TextView pubNameTv;
    TextView pubIntroTv;
    TextView pubLocateTv;
    TextView pubTimeTv;
    TextView pubGameTv;
    ImageView pubImageTv;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pub_detail);

        pubNameTv = findViewById(R.id.pub_detail_name);
        pubIntroTv = findViewById(R.id.pub_detail_intro);
        pubLocateTv = findViewById(R.id.pub_detail_location);
        pubTimeTv = findViewById(R.id.pub_detail_time);
        pubGameTv = findViewById(R.id.pub_detail_game);
        pubImageTv = findViewById(R.id.pub_detail_image);

        Intent intent = getIntent();

        pubNameTv.setText(intent.getExtras().getString("name"));
        pubIntroTv.setText(intent.getExtras().getString("intro"));
        pubLocateTv.setText(intent.getExtras().getString("locate"));
        pubTimeTv.setText(intent.getExtras().getString("time"));
        pubGameTv.setText(intent.getExtras().getString("game"));

        Glide.with(this).load(intent.getExtras().getString("poster")).into(pubImageTv);
    }
}
