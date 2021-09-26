package com.example.ticket.ui.mypage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.TicketUpload;
import com.example.ticket.ui.pub.PRecycleAdapter;
import com.example.ticket.ui.pub.Pub;

public class MypageFragment extends Fragment {

    private TextView profileName;
    private TextView profileID;
    private TextView profileLoc;
    private LinearLayout goToSell;
    private RecyclerView preferRc;
    private PreferAdapter adapter;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";

    // key for storing Member
    public static final String USER_LOGIN_ID_KEY = "user_login_id_key";
    public static final String USER_NAME = "user_name_key";
    public static final String USER_LOC = "user_loc_key";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        SharedPreferences preferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        String user_login_id = preferences.getString(USER_LOGIN_ID_KEY, "아이디");
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

    public void getData(){
        Pub pub1 = new Pub("Final Nine","강남","데일리","14:00","Hi","");
        Pub pub2 = new Pub("Battle PlayPub","홍대","대회","15:00","I'm","");
        Pub pub3 = new Pub("레인보우","건대","데일리","16:00","hungry","");

        adapter.addItem(pub1);
        adapter.addItem(pub2);
        adapter.addItem(pub3);

    }

}