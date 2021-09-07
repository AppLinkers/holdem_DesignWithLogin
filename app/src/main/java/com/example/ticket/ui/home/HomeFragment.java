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
import androidx.viewpager2.widget.ViewPager2;

import com.example.ticket.MainActivity;
import com.example.ticket.R;
import com.example.ticket.ui.pub.PubFragment;
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


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_home,container,false);

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