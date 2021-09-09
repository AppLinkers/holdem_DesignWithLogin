package com.example.ticket.ui.home;

import android.content.res.TypedArray;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.ticket.MainActivity;
import com.example.ticket.R;
import com.example.ticket.ui.pub.PubFragment;
import com.example.ticket.ui.schedule.SRecycleAdapter;
import com.example.ticket.ui.schedule.ScheduleFragment;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

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

        return root;
    }

    //RecyclerView data2
    private void getData2() {

        HomePub h1 = new HomePub("Final Nine","강남", "4.5/5", "15,000");
        HomePub h2 = new HomePub("Battle PlayPub","홍대", "4.0/5", "10,000");
        HomePub h3 = new HomePub("레인보우","강남", "1.5/5", "15,000");
        HomePub h4 = new HomePub("Final Nine","강남", "4.5/5", "15,000");


        HPadapter.addItem(h1);
        HPadapter.addItem(h2);
        HPadapter.addItem(h3);
        HPadapter.addItem(h4);
    }

    //RecyclerView data
    private void getData() {

        HomeSchdeule l1 = new HomeSchdeule("APL","서울","7/31");
        HomeSchdeule l2 = new HomeSchdeule("JLP","부산","8/31");
        HomeSchdeule l3 = new HomeSchdeule("ROKA","수원","9/31");
        HomeSchdeule l4 = new HomeSchdeule("SSH","서울","10/31");

        HSadapter.addItem(l1);
        HSadapter.addItem(l2);
        HSadapter.addItem(l3);
        HSadapter.addItem(l4);
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