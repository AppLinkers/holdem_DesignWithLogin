package com.example.ticket.ui.pub;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.Competition;
import com.example.ticket.ui.entity.Game;
import com.example.ticket.ui.entity.HoldemPub;
import com.example.ticket.ui.schedule.Schedule;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PubFragment extends Fragment {

    private Spinner spinner;
    private TextView tv_pub;
    private RecyclerView recyclerView;
    private PRecycleAdapter adapter;
    private String pubFilteredKey="";

    private String TAG = "PubFragment";

    DataService dataService = new DataService();

    public static PubFragment newInstance() {
        return new PubFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_pub,container,false);
        //지역 필터
        spinner = (Spinner) root.findViewById(R.id.sp_pub);
        tv_pub = (TextView) root.findViewById(R.id.tv_pub);

        recyclerView = root.findViewById(R.id.rcPub);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter =new PRecycleAdapter();
        getData(pubFilteredKey);
        recyclerView.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pubFilteredKey = parent.getItemAtPosition(position).toString();
                tv_pub.setText(pubFilteredKey);
                adapter =new PRecycleAdapter();
                getData(pubFilteredKey);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        return root;
    }


    //리사이클러뷰 데이터
    @SuppressLint({"StaticFieldLeak", "NewApi"})
    public void getData(String filteredKey){

        AsyncTask<Void, Void, List<HoldemPub>> listAPI = new AsyncTask<Void, Void, List<HoldemPub>>() {
            @Override
            protected List<HoldemPub> doInBackground(Void... params) {
                Call<List<HoldemPub>> call = dataService.holdemPubs.holdemPubs();
                try {
                    return call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<HoldemPub> s) {
                super.onPostExecute(s);
            }
        }.execute();


        List<HoldemPub> result = null;

        try {
            result = listAPI.get();
            Log.d(TAG, String.valueOf(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result != null) {
            result.forEach(c -> {

                if(filteredKey.equals("전국")){
                    Pub pub = new Pub(c.getPub_name(), c.getPub_place(), c.getGame().games(), c.getPub_open(), c.getPub_info(), c.getPub_img());
                    adapter.addItem(pub);
                }
                else if(c.getPub_place().contains(filteredKey)) {
                    Pub pub = new Pub(c.getPub_name(), c.getPub_place(), c.getGame().games(), c.getPub_open(), c.getPub_info(), c.getPub_img());
                    adapter.addItem(pub);
                }

            });
        }



    }
}
