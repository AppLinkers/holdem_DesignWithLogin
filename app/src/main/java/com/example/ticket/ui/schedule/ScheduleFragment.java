package com.example.ticket.ui.schedule;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.Competition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ScheduleFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Schedule> list = new ArrayList<>();
    private SRecycleAdapter adapter;
    String dateFil="";
    private static final String TAG = "SchedulePage";

    TextView count;

    DataService dataService = new DataService();

    TextView tv_year_month_picker;
    LinearLayout select_date;



    public static ScheduleFragment newInstance() {
        return new ScheduleFragment();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_schedule, container, false);



        //datePicker

        tv_year_month_picker = root.findViewById(R.id.tv_year_month_picker);
        select_date = root.findViewById(R.id.select_date);

        select_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YearMonthPickerDialog pd = new YearMonthPickerDialog();
                pd.setListener(d);
                pd.show(getChildFragmentManager(),"YearMonthPicker");
            }
        });

        //recyclerView
        recyclerView = root.findViewById(R.id.rcView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new SRecycleAdapter();
        getData(dateFil);
        recyclerView.setAdapter(adapter);

        count = root.findViewById(R.id.count);
        count.setText("("+adapter.getItemCount()+")");


        return root;
    }

    @SuppressLint({"StaticFieldLeak", "NewApi"})
    public void getData(String filter){

        AsyncTask<Void, Void, List<Competition>> listAPI = new AsyncTask<Void, Void, List<Competition>>() {
            @Override
            protected List<Competition> doInBackground(Void... params) {
                Call<List<Competition>> call = dataService.schedules.schedules();
                try {
                    return call.execute().body();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Competition> s) {
                super.onPostExecute(s);
            }
        }.execute();


        List<Competition> result = null;

        try {
            result = listAPI.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (result != null) {
            result.forEach(c -> {

                if(c.getCmp_start().contains(filter)){
                    Schedule schedule = new Schedule(c.getCmp_img(), c.getCmp_name(), c.getCmp_place(), Integer.toString(c.getCmp_buyIn()), c.getCmp_start());
                    adapter.addItem(schedule);
                }



            });
        }




    }





    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            Log.d("YearMonthPicker", "year = " + year + ", month = " + month + ", day = " + dayOfMonth);
            if(month < 10){
                dateFil= year+".0"+month;
                tv_year_month_picker.setText(month + "월의 대회");
                adapter = new SRecycleAdapter();
                getData(dateFil);
                recyclerView.setAdapter(adapter);
                Toast.makeText(getContext(),dateFil,Toast.LENGTH_SHORT).show();
            } else {
                dateFil= year+"."+month;
                tv_year_month_picker.setText(month + "월의 대회");
                adapter = new SRecycleAdapter();
                getData(dateFil);
                recyclerView.setAdapter(adapter);
                Toast.makeText(getContext(),dateFil,Toast.LENGTH_SHORT).show();
            }


        }
    };


}
