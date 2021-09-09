package com.example.ticket;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private EditText mycontext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mycontext = findViewById(R.id.mycontext);
        recyclerView = (RecyclerView)findViewById(R.id.rc_chat);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ChatAdapter();
        getData();
        recyclerView.setAdapter(adapter);
    }

    public void getData(){

        Chat chat1 = new Chat("jung: ", "first message");
        Chat chat2 = new Chat("jung: ", "second message");
        Chat chat3 = new Chat("ahn: ", "third message");
        Chat chat4 = new Chat("you: ", "fourth message");

        adapter.addItem(chat1);
        adapter.addItem(chat2);
        adapter.addItem(chat3);
        adapter.addItem(chat4);
    }


    public void send(View view) {
        Chat newchat = new Chat("jung: ", mycontext.getText()+"");
        adapter.addItem(newchat);
        recyclerView.setAdapter(adapter);
    }
}