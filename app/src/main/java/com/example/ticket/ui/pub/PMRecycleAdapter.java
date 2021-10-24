package com.example.ticket.ui.pub;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;

import java.util.ArrayList;

public class PMRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<PubMenu> listData = new ArrayList<>();

    String userid;

    private static SharedPreferences sharedPreferences;

    // creating constant keys for shared preferences.
    public static final String SHARED_PREFS = "shared_prefs";
    // key for storing Member
    public static final String USER_LOGIN_ID_KEY = "user_login_id_key";

//    public PMRecycleAdapter(Context context) {
//        this.sharedPreferences = context.getSharedPreferences(SHARED_PREFS, context.MODE_PRIVATE);
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pub_menu_item, parent, false);

        return new PMRecycleHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((PMRecycleHolder) holder).onBind(listData.get(position));

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(PubMenu data){
        listData.add(data);
    }

}
