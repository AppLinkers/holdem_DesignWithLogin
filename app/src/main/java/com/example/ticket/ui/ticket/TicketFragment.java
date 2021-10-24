package com.example.ticket.ui.ticket;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.TicketDto;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class TicketFragment extends Fragment{

    private RecyclerView recyclerView;
    private TRecycleAdapter adapter;
    private static final String TAG = "TicketPage";

    private SearchView searchView;
    ArrayList<Ticket> arrayList;

    private String key;

    DecimalFormat decimalFormat = new DecimalFormat("###,###");
    DataService dataService = new DataService();

    public static TicketFragment newInstance() {
        return new TicketFragment();
    }




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ticket, container, false);

        Window window = getActivity().getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(getContext(),R.color.white));
        }

        if (getArguments() != null)
        {
            key = getArguments().getString("key");
        }

        recyclerView = (RecyclerView) root.findViewById(R.id.rc_ticket);
        searchView = (androidx.appcompat.widget.SearchView) root.findViewById(R.id.searchView);
        arrayList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new TRecycleAdapter();
        getData();
        adapter.getFilter().filter(key);
        recyclerView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.clear();
                getData();
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return root;
    }


    //리사이클러뷰 데이터
    @SuppressLint({"StaticFieldLeak", "NewApi"})
    public void getData(){

        AsyncTask<Void, Void, List<TicketDto>> listAPI = new AsyncTask<Void, Void, List<TicketDto>>() {
            @Override
            protected List<TicketDto> doInBackground(Void... params) {
                Call<List<TicketDto>> call = dataService.tickets.tickets();
                try {
                    return call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<TicketDto> s) {
                super.onPostExecute(s);
            }
        }.execute();


        List<TicketDto> result = null;

        try {
            result = listAPI.get();
            Log.d(TAG, String.valueOf(result));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result != null) {
            result.forEach(c -> {

                String price = decimalFormat.format(c.getTicket_price());
                Ticket ticket = new Ticket(c.getId(),c.getTicket_name(), c.getTicket_place(),price,c.getTicket_poster());
                adapter.addItem(ticket);

            });
        }



    }

}