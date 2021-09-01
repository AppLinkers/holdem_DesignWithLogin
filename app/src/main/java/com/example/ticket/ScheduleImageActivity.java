package com.example.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ScheduleImageActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_image);

        iv = findViewById(R.id.detail_image);

        Intent intent = getIntent();

        String a = intent.getStringExtra("poster");

        Glide.with(this).load(a).into(iv);



    }
}