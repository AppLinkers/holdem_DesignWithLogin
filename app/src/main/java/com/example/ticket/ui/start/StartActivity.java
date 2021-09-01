package com.example.ticket.ui.start;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void goToSignUp(View view) {
        Intent intent2 = new Intent(getApplicationContext(), SignUpPage.class);
        startActivity(intent2);

    }

    public void goToLogin(View view) {

        Intent intent1 = new Intent(getApplicationContext(), SignInPage.class);
        startActivity(intent1);
    }

}
