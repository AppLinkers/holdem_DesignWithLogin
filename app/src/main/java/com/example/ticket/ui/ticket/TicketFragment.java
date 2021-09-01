package com.example.ticket.ui.ticket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.databinding.FragmentTicketBinding;
import com.example.ticket.ui.home.HomeFragment;
import com.example.ticket.ui.pub.PRecycleAdapter;
import com.example.ticket.ui.pub.Pub;

public class TicketFragment extends Fragment {

    private RecyclerView recyclerView;
    private TRecycleAdapter adapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_ticket, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.rc_ticket);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new TRecycleAdapter();
        getData();
        recyclerView.setAdapter(adapter);

        return root;
    }

    public void getData() {
        Ticket t1 = new Ticket("ASL 티켓", "서울", "200,000");
        Ticket t2 = new Ticket("BPP 티켓", "강원", "100,000");
        Ticket t3 = new Ticket("KSOP 티켓", "부산", "150,000");
        Ticket t4 = new Ticket("ASL 티켓", "하남", "100,000");
        Ticket t5 = new Ticket("LCK 티켓", "해남", "200,000");

        adapter.addItem(t1);
        adapter.addItem(t2);
        adapter.addItem(t3);
        adapter.addItem(t4);
        adapter.addItem(t5);
    }
}