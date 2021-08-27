package com.example.ticket.ui.pub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;

public class PubFragment extends Fragment {

    private Spinner spinner;
    private TextView tv_pub;
    private RecyclerView recyclerView;
    private PRecycleAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pub,container,false);
        //지역 필터
        spinner = (Spinner) root.findViewById(R.id.sp_pub);
        tv_pub = (TextView) root.findViewById(R.id.tv_pub);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_pub.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //리사이클러뷰
        recyclerView = root.findViewById(R.id.rcPub);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter =new PRecycleAdapter();
        getData();
        recyclerView.setAdapter(adapter);


        return root;
    }


    //리사이클러뷰 데이터
    public void getData(){
        Pub h1 = new Pub( "Final Nine", "강남",
                "1 데일리, 3장 몬스터, 1.5장 게임", "14:00");
        Pub h2 = new Pub( "Final Nine", "홍대",
                "1장 데일리, 3장 몬스터, 1.5장 게임", "14:00");
        Pub h3 = new Pub( "Battle Play Pub", "홍대",
                "1장 데일리", "14:00");
        Pub h4 = new Pub( "레인보우", "강남",
                "10장 메인 하이롤러, 20장 사이", "14:00");

        adapter.addItem(h1);
        adapter.addItem(h2);
        adapter.addItem(h3);
        adapter.addItem(h4);
    }
}
