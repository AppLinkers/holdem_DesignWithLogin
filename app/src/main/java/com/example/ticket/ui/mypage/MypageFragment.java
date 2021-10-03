package com.example.ticket.ui.mypage;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.TicketUpload;
import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.HoldemPub;
import com.example.ticket.ui.pub.Pub;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;

public class MypageFragment extends Fragment {

    private TextView profileName;
    private TextView profileID;
    private TextView profileLoc;
    private LinearLayout goToSell;
    private RecyclerView preferRc;
    private PreferAdapter adapter;
    String user_login_id = "";

    DataService dataService = new DataService();

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing Member
    public static final String USER_LOGIN_ID_KEY = "user_login_id_key";
    public static final String USER_NAME = "user_name_key";
    public static final String USER_LOC = "user_loc_key";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences preferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        user_login_id = preferences.getString(USER_LOGIN_ID_KEY, "아이디");
        String user_name = preferences.getString(USER_NAME,"이름");
        String user_loc = preferences.getString(USER_LOC, "지역");

        View root = inflater.inflate(R.layout.fragment_mypage,container,false);

        profileName = root.findViewById(R.id.profileName);
        profileID = root.findViewById(R.id.profileID);
        profileLoc = root.findViewById(R.id.profileLoc);

        profileName.setText(user_name);
        profileID.setText(user_login_id);
        profileLoc.setText(user_loc);

        preferRc = root.findViewById(R.id.preferRc);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        preferRc.setLayoutManager(linearLayoutManager);

        adapter =new PreferAdapter();
        getData();
        preferRc.setAdapter(adapter);

        goToSell = root.findViewById(R.id.gotoSell);

        goToSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TicketUpload.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @SuppressLint({"StaticFieldLeak", "NewApi"})
    public void getData(){
<<<<<<< HEAD
        Pub pub1 = new Pub((long) 1,"Final Nine","강남","데일리","14:00","Hi","",false);
        Pub pub2 = new Pub((long) 2,"Battle PlayPub","홍대","대회","15:00","I'm","",false);
        Pub pub3 = new Pub((long) 3,"레인보우","건대","데일리","16:00","hungry","",false);
=======
        AsyncTask<Void, Void, List<HoldemPub>> listAPI = new AsyncTask<Void, Void, List<HoldemPub>>() {
            @Override
            protected List<HoldemPub> doInBackground(Void... params) {
                Call<List<HoldemPub>> call = dataService.holdemPubs.listHoldemPub(user_login_id);
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

>>>>>>> b3b5d811ef99895c272bd54ec6994af6da3f127b

        if (result != null) {
            result.forEach(c -> {
                    Pub pub = new Pub(c.getId(), c.getPub_name(), c.getPub_place(), c.getGame().games(), c.getPub_open(), c.getPub_info(), c.getPub_img(),false);
                    adapter.addItem(pub);
            });
        }

    }

}