package com.example.ticket.ui.schedule;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Schedule> list = new ArrayList<>();
    private SRecycleAdapter adapter;
    TextView count;

    TextView tv_year_month_picker;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_schedule, container, false);
        //recyclerView
        recyclerView = root.findViewById(R.id.rcView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new SRecycleAdapter();
        getData();
        recyclerView.setAdapter(adapter);


        count = root.findViewById(R.id.count);
        count.setText("총 "+adapter.getItemCount()+" 개 일정");

        //datePicker
        tv_year_month_picker = root.findViewById(R.id.tv_year_month_picker);

        tv_year_month_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YearMonthPickerDialog pd = new YearMonthPickerDialog();
                pd.setListener(d);
                pd.show(getChildFragmentManager(),"YearMonthPicker");
            }
        });







        return root;
    }





    public void getData(){

        Schedule apl = new Schedule(R.drawable.apl,"APL",
                "Seoul", "3", "14:00 start");

        Schedule bpp = new Schedule(R.drawable.bpp,"B.P.P",
                "Seoul", "2", "14:00 start");

        Schedule ksop = new Schedule(R.drawable.ksop,"KSOP",
                "Seoul", "2", "14:00 start");
        Schedule hpl = new Schedule(R.drawable.hpl,"HPL",
                "양양 쏠비", "2", "14:00 start");

        Schedule apl2 = new Schedule(R.drawable.apl,"APL",
                "Seoul", "3", "14:00 start");

        Schedule bpp2 = new Schedule(R.drawable.bpp,"B.P.P",
                "Seoul", "2", "14:00 start");

        Schedule ksop2 = new Schedule(R.drawable.ksop,"KSOP",
                "Seoul", "2", "14:00 start");

        adapter.addItem(apl);
        adapter.addItem(bpp);
        adapter.addItem(ksop);
        adapter.addItem(hpl);
        adapter.addItem(apl2);
        adapter.addItem(bpp2);
        adapter.addItem(ksop2);

    }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            Log.d("YearMonthPicker", "year = " + year + ", month = " + month + ", day = " + dayOfMonth);
            if(month < 10){
                tv_year_month_picker.setText(year + ".0" + month);
            } else {
                tv_year_month_picker.setText(year + "." + month);
            }


        }
    };



}
