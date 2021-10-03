package com.example.ticket;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.Message;
import com.example.ticket.ui.entity.Room;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;
import ua.naiksoftware.stomp.dto.StompHeader;

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
    Long ticketId;

    private static final String TAG = "Chat";

    DataService dataService = new DataService();
    private StompClient stompClient;
    private List<StompHeader> headerList;

    Gson gson = new Gson();
    int chk = 0;



    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Intent intent = getIntent();
        ticketId = intent.getLongExtra("ticketId",1l);

        // initializing our shared preferences.
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // getting data from shared prefs and
        // storing it in our string variable.
        user_login_id = sharedPreferences.getString(USER_LOGIN_ID_KEY, "seungwan");
        user_id = sharedPreferences.getLong(USER_ID_KEY, 3l);

        mycontext = findViewById(R.id.mycontext);
        recyclerView = (RecyclerView)findViewById(R.id.rc_chat);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ChatAdapter();
        adapter.setID(user_login_id);
//        scrollToBottom();
        getData();
        // chat 방 입장 처리
        /**
         * 둘다 ticket id 로 처리
         */
        recyclerView.scrollToPosition(adapter.getItemCount()-1);
        this.room = new Room(ticketId,ticketId);
        initStomp();


    }

    @SuppressLint("CheckResult")
    private void initStomp() {
        stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://3.21.178.170/ws-stomp/websocket");
        stompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
                    break;
                case ERROR:
                    Log.e(TAG, "Error", lifecycleEvent.getException());
                    if(lifecycleEvent.getException().getMessage().contains("EOF")){
                    }
                    break;
                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    break;
            }
        });
        stompClient.connect();

        /**
         * test -> ticket id 로 변경
         */
        stompClient.topic("/sub/chat/room/"+ticketId.toString()).subscribe(topicMessage -> {
            Log.d(TAG, topicMessage.getPayload());
            Message message = gson.fromJson(topicMessage.getPayload(), Message.class);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Chat chat = new Chat(message.getSenderName(), message.getMessage());
                    adapter.addItem(chat);
                    recyclerView.setAdapter(adapter);
                }
            });
        });

        if (chk == 0) {
            Message message = new Message("ENTER", room, user_id, user_login_id, "ENTER");
            String enter = gson.toJson(message);
            stompClient.send("/pub/chat/message", enter).subscribe();
        }
    }


    //리사이클러뷰 데이터
    @SuppressLint({"StaticFieldLeak", "NewApi"})
    public void getData(){

        AsyncTask<Void, Void, List<Message>> listAPI = new AsyncTask<Void, Void, List<Message>>() {
            @Override
            protected List<Message> doInBackground(Void... params) {
                Call<List<Message>> call = dataService.chat.messageList(ticketId);
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
        Log.d(TAG, String.valueOf(result));

        if (result.size() > 0) {
            room = result.get(0).getRoom();
            result.forEach(c -> {
                if (c.getSenderId().equals(user_id)) {
                    chk++;
                }
                Chat chat = new Chat(c.getSenderName(), c.getMessage());
                if(user_login_id.equals("test")){
                    adapter.addItem(chat);
                }else{
                    if((c.getSenderName().equals(user_login_id))||c.getSenderName().equals("test")){
                        adapter.addItem(chat);
                    }
                }


            });
            recyclerView.setAdapter(adapter);
        }


    }

    @SuppressLint("StaticFieldLeak")
    public void send(View view) {
        Message message = new Message("TALK", room, user_id, user_login_id, mycontext.getText() + "");
        String sendMessage = gson.toJson(message);
        Log.d("send", sendMessage);
        stompClient.send("/pub/chat/message", sendMessage).subscribe();
        mycontext.setText(null);

    }
}