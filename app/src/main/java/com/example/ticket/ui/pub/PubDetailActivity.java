package com.example.ticket.ui.pub;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ticket.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;


public class PubDetailActivity extends AppCompatActivity {
    TextView pubNameTv;
    TextView pubIntroTv;
    TextView pubLocateTv;
    TextView pubTimeTv;
    TextView pubGameTv;
    ImageView pubImageTv;
    ImageView pubHeart;
    boolean clicked;

    Toolbar toolbar;
    CollapsingToolbarLayout collapseToolbar;

    private PMRecycleAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pub_detail);



        pubNameTv = findViewById(R.id.pub_detail_name);
        pubIntroTv = findViewById(R.id.pub_detail_intro);
        pubLocateTv = findViewById(R.id.pub_detail_location);
        pubTimeTv = findViewById(R.id.pub_detail_time);
        pubImageTv = findViewById(R.id.pub_detail_image);
        pubHeart=findViewById(R.id.pub_heart);
        recyclerView = findViewById(R.id.menuRc);

        toolbar = findViewById(R.id.toolbar);
        collapseToolbar = findViewById(R.id.collapseToolbar);

//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        title_Image = findViewById(R.id.title_Img);


        Intent intent = getIntent();


        pubNameTv.setText(intent.getExtras().getString("name"));
        pubIntroTv.setText(intent.getExtras().getString("intro"));
        pubLocateTv.setText(intent.getExtras().getString("locate"));
        pubTimeTv.setText(intent.getExtras().getString("time"));
        String pubGame = intent.getExtras().getString("game");

        //툴바 옵션
//        toolbar.setTitle(intent.getExtras().getString("name"));
        collapseToolbar.setTitle(intent.getExtras().getString("name"));

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        clicked = intent.getBooleanExtra("like",true);

        if(clicked){
       //     pubHeart.setImageResource(R.drawable.ic_heart_black);
            clicked =false;
        } else {
            clicked =true;
            pubHeart.setImageResource(R.drawable.ic_heart_red);
        }

        Glide.with(this).load(intent.getExtras().getString("poster")).into(pubImageTv);
//        Glide.with(this).load(intent.getExtras().getString("poster")).into(title_Image);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PMRecycleAdapter();
        getData(pubGame);
        recyclerView.setAdapter(adapter);
    }

    private void getData(String gameData) {
        String testData = "game1 10000/game2 30000/game3 100000";
        String[] game = testData.split("/");
        for(int i=0; i<game.length;i++){
            String[] menu = game[i].split(" ");
            PubMenu pm = new PubMenu(menu[0],menu[1]);
            adapter.addItem(pm);
        }
    }

    //toolbar의 back키 눌렀을 때 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
