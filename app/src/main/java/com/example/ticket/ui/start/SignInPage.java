package com.example.ticket.ui.start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ticket.MainActivity;
import com.example.ticket.R;
import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.Member;
import com.example.ticket.ui.home.HomeFragment;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInPage extends AppCompatActivity {

    private static final String TAG = "LoginPage";

    DataService dataService = new DataService();

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing Member
    public static final String USER_ID_KEY = "user_id_key";

    // variable for shared preferences.
    SharedPreferences sharedPreferences;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);

        // initializing our shared preferences.
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        user_id = sharedPreferences.getString(USER_ID_KEY, null);

        final EditText login_user_id = findViewById(R.id.login_user_id);
        final EditText login_user_pass = findViewById(R.id.login_user_pass);

        // Login
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "test");
                Map<String, String> map = new HashMap<>();
                map.put("user_id", login_user_id.getText().toString());
                map.put("user_pass", login_user_pass.getText().toString());
                dataService.login.login(map).enqueue(new Callback<Member>() {
                    @Override
                    public void onResponse(Call<Member> call, Response<Member> response) {
                        Member result = response.body();
                        Log.d(TAG, String.valueOf(result));
                        if (result.getUser_id().equals("wrong_pass")) {
                            login_user_pass.setText("");
                            login_user_pass.setHint("비밀번호가 일치하지 않습니다.");
                            login_user_pass.setHintTextColor(Color.RED);
                        } else if (result.getUser_id().equals("no_member")) {
                            login_user_id.setText("");
                            login_user_id.setHint("존재하지 않는 회원입니다.");
                            login_user_id.setHintTextColor(Color.RED);
                        } else{
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            editor.putString(USER_ID_KEY, result.getUser_id());

                            editor.apply();

                            Intent login = new Intent(getApplicationContext(), MainActivity.class);
                            Log.d(TAG, "TEST");
                            startActivity(login);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Member> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    public void goToSignUp(View view) {

        Intent intent1 = new Intent(getApplicationContext(), SignUpPage.class);
        startActivity(intent1);
    }
}
