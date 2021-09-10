package com.example.ticket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.Message;
import com.example.ticket.ui.entity.Room;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private EditText mycontext;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing Member
    public static final String USER_ID_KEY = "user_id_key";
    public static final String USER_LOGIN_ID_KEY = "user_login_id_key";

    // variable for shared preferences.
    SharedPreferences sharedPreferences;
    Long user_id;
    String user_login_id;

    Room room;

    private static final String TAG = "Chat";

    DataService dataService = new DataService();



    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // initializing our shared preferences.
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        user_login_id = sharedPreferences.getString(USER_LOGIN_ID_KEY, "seungwan");
        user_id = sharedPreferences.getLong(USER_ID_KEY, 3l);

        mycontext = findViewById(R.id.mycontext);
        recyclerView = (RecyclerView)findViewById(R.id.rc_chat);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ChatAdapter();
//        scrollToBottom();
        getData();
        recyclerView.setAdapter(adapter);

    }


    //리사이클러뷰 데이터
    @SuppressLint({"StaticFieldLeak", "NewApi"})
    public void getData(){

        AsyncTask<Void, Void, List<Message>> listAPI = new AsyncTask<Void, Void, List<Message>>() {
            @Override
            protected List<Message> doInBackground(Void... params) {
                Call<List<Message>> call = dataService.chat.messageList(2l);
                try {
                    return call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Message> s) {
                super.onPostExecute(s);
            }
        }.execute();


        List<Message> result = null;

        try {
            result = listAPI.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result != null) {
            room = result.get(0).getRoom();
            result.forEach(c -> {
                String sender;
                if (c.getSenderId().equals(user_id)) {
                    sender = user_login_id;
                } else {
                    sender = c.getRoom().getBuyerLoginId();
                }
                Chat chat = new Chat(sender, c.getMessage());

                adapter.addItem(chat);

            });


        }


    }

    @SuppressLint("StaticFieldLeak")
    public void send(View view) {
        Message message = new Message("TALK",room,user_id, mycontext.getText()+"");

        AsyncTask<Void, Void, Message> listAPI = new AsyncTask<Void, Void, Message>() {
            @Override
            protected Message doInBackground(Void... params) {
                Call<Message> call = dataService.chat.message(message);
                try {
                    return call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Message s) {
                super.onPostExecute(s);
            }
        }.execute();


        Message result = null;

        try {
            result = listAPI.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result != null) {

        Chat newchat = new Chat(user_login_id, mycontext.getText()+"");
        adapter.addItem(newchat);
        recyclerView.setAdapter(adapter);

        }
    }


}