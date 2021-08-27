package com.example.ticket.ui.home;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;

import org.jetbrains.annotations.NotNull;

public class ViewPager2Adapter extends RecyclerView.Adapter<ViewPager2Adapter.MyViewHolder> {

    private TypedArray mItmes;
    private Context context;

    public ViewPager2Adapter(Context context, TypedArray mItmes){
        this.context = context;
        this.mItmes = mItmes;
    }
    @NotNull
    @Override
    public ViewPager2Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.imageslide,parent,false);
        return new MyViewHolder(view);
    }

    //이미지 교체 함수
    @Override
    public void onBindViewHolder(@NonNull ViewPager2Adapter.MyViewHolder holder, int position) {
        int index = position % mItmes.length();
        Log.d("MyAdapter", "onBindViewHolder index:" + index);
        if(index >= mItmes.length()){
            index = 0;
        }
        holder.imageView.setImageResource(mItmes.getResourceId(index, -1));
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE; //슬라이드를 무한으로
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageSlideView);
        }
    }
}
