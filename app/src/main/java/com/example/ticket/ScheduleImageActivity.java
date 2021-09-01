package com.example.ticket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class ScheduleImageActivity extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_image);

        iv = findViewById(R.id.detail_image);

        Intent intent = getIntent();

        iv.setImageResource(intent.getIntExtra("poster",0));



    }
}