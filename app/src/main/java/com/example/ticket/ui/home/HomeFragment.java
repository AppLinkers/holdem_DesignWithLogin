package com.example.ticket.ui.home;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ticket.MainActivity;
import com.example.ticket.R;
import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.entity.Competition;
import com.example.ticket.ui.entity.HoldemPub;
import com.example.ticket.ui.pub.PubFragment;
import com.example.ticket.ui.schedule.ScheduleFragment;
import com.example.ticket.ui.ticket.TicketFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class HomeFragment extends Fragment {

    private String TAG = "Home Fragrment";
    ArrayList<Integer> image;
    private ViewFlipper viewFlipper;

    public TypedArray m_ArrayBannerImages;
    private ViewPager2 m_viewPager2Banner;
    private ViewPager2Adapter adapter;
    private ImageView goToPub;
    private ImageView gotToSchedule;

    private RecyclerView recyclerView;
    private RecyclerView recyclerView2;
    private HSRecycleAdapter HSadapter;
    private HPRecycleAdapter HPadapter;

    BottomNavigationView bottomNavigationView;

    
    LinearLayout ksop;
    LinearLayout bpp;
    LinearLayout apl;
    LinearLayout hpl;

    DataService dataService = new DataService();
    int cnt=0;
    int cnt2 = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home,container,false);

        //RecyclerView1
        recyclerView = root.findViewById(R.id.rc_home_schedule);
        recyclerView2 = root.findViewById(R.id.rc_home_pub);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView2.setLayoutManager(linearLayoutManager2);

        bottomNavigationView = root.findViewById(R.id.bottom_navigation);

        HSadapter = new HSRecycleAdapter();
        HPadapter = new HPRecycleAdapter();
        getData();
        getData2();
        recyclerView.setAdapter(HSadapter);
        recyclerView2.setAdapter(HPadapter);

        //ImageFlipper
        image = new ArrayList<>();

        imgData();

        viewFlipper = root.findViewById(R.id.image_slider);

        for(int i=0; i<image.size();i++){
            addFlipper(image.get(i));
        }

        viewFlipper.startFlipping();
        viewFlipper.setFlipInterval(5000);

        //ImageSlider
//        m_viewPager2Banner = root.findViewById(R.id.viewPagerBanner);
//        m_ArrayBannerImages = root.getResources().obtainTypedArray(R.array.bannerImages);
//
//        adapter = new ViewPager2Adapter(getContext(),m_ArrayBannerImages);
//
//        m_viewPager2Banner.setAdapter(adapter);
//        m_viewPager2Banner.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);


        goToPub = root.findViewById(R.id.goToPub);
        gotToSchedule = root.findViewById(R.id.goToSchedule);

        goToPub.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replace(PubFragment.newInstance(),R.id.navigation_pub);
            }
        });

        gotToSchedule.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replace(ScheduleFragment.newInstance(),R.id.navigation_schedule);
            }
        });
        
        
        
        ksop = root.findViewById(R.id.ksop);
        apl = root.findViewById(R.id.apl);
        hpl = root.findViewById(R.id.hpl);
        bpp = root.findViewById(R.id.bpp);


        ksop.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                click("ksop");
            }
        });

        apl.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                click("asl");
            }
        });

        bpp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                click("bpp");
            }
        });

        hpl.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
                click("hpl");
            }
        });

        bpp.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                click("bpp");
            }

        });


        return root;
    }




    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void click(String ticket){
        Bundle bundle = new Bundle();
        bundle.putString("key", ticket);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        TicketFragment ticketFragment = new TicketFragment();//프래그먼트2 선언
        ticketFragment.setArguments(bundle);//번들을 프래그먼트2로 보낼 준비
        transaction.replace(R.id.frame_container, ticketFragment);
        transaction.commit();
        ((MainActivity)getActivity()).navigationBlink(R.id.navigation_ticket);

    }


    //RecyclerView data2
    @SuppressLint({"StaticFieldLeak", "NewApi"})
    private void getData2() {

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
                if(cnt2<3) {
                    HomePub pub = new HomePub(c.getPub_name(), c.getPub_place(), "10,000", c.getPub_img());
                    HPadapter.addItem(pub);
                    cnt2+=1;
                }

            });
            cnt2=0;
        }
    }





    //RecyclerView data
    @SuppressLint({"StaticFieldLeak", "NewApi"})
    private void getData() {
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

                if(cnt<4) {
                    HomeSchdeule schedule = new HomeSchdeule(c.getCmp_name(), c.getCmp_place(), c.getCmp_start(), c.getCmp_img());
                    HSadapter.addItem(schedule);
                    cnt+=1;
                }
            });
            cnt=0;
        }
    }

    public void imgData(){
        image.add(R.drawable.bar);
        image.add(R.drawable.bar2);
        image.add(R.drawable.bar3);
    }

    public void addFlipper(int image){
        ImageView iv = new ImageView(getContext());
        iv.setBackgroundResource(image);
        viewFlipper.addView(iv);

    }



}