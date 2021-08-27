package com.example.ticket.ui.start;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket.R;
import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.Member;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPage extends AppCompatActivity {

    private static final String TAG = "SignUpPage";

    DataService dataService = new DataService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        final EditText info_user_id = findViewById(R.id.info_user_id);
        final EditText info_user_name = findViewById(R.id.info_user_name);
        final EditText info_user_pass = findViewById(R.id.info_user_pass);
        final EditText info_user_confirmPass = findViewById(R.id.info_user_confirmPass);
        final EditText info_user_phone = findViewById(R.id.info_user_phone);
        final EditText info_user_loc = findViewById(R.id.info_user_loc);

        //SignUp
        Button btn_signUp = findViewById(R.id.btn_signUp);
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (info_user_pass.getText().toString().equals(info_user_confirmPass.getText().toString())) {
                    Map<String, String> map = new HashMap<>();
                    map.put("user_id", info_user_id.getText().toString());
                    map.put("user_name", info_user_name.getText().toString());
                    map.put("user_pass", info_user_pass.getText().toString());
                    map.put("user_phone", info_user_phone.getText().toString());
                    map.put("user_loc", info_user_loc.getText().toString());
                    dataService.singUp.signUp(map).enqueue(new Callback<Member>() {
                        @Override
                        public void onResponse(Call<Member> call, Response<Member> response) {
                            if (response.code() == 500) {
                                info_user_id.setText("");
                                info_user_id.setHint("이미 존재하는 회원입니다.");
                                info_user_id.setHintTextColor(Color.RED);
                            } else {
                                Toast.makeText(SignUpPage.this, "회원 가입 완료", Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(getApplicationContext(), SignInPage.class);
                                startActivity(intent1);
                            }

                        }

                        @Override
                        public void onFailure(Call<Member> call, Throwable t) {
                            Log.d(TAG, t.toString());
                            t.printStackTrace();
                        }
                    });
                } else {
                    info_user_confirmPass.setText("");
                    info_user_confirmPass.setHint("비밀번호가 일치하지 않습니다.");
                    info_user_confirmPass.setHintTextColor(Color.RED);
                }
            }
        });


    }

    public void goToLogin(View view) {
        Intent intent = new Intent(getApplicationContext(), SignInPage.class);
        startActivity(intent);
    }
}

