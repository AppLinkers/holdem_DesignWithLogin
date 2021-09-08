package com.example.ticket.ui.home;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ticket.R;
import com.example.ticket.ScheduleImageActivity;
import com.example.ticket.ui.schedule.Schedule;

import org.jetbrains.annotations.NotNull;

public class HSRecycleHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView place;
    public TextView date;

    public HSRecycleHolder(@NonNull View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.home_schedule_name);
        place = (TextView) itemView.findViewById(R.id.home_schedule_place);
        date = (TextView) itemView.findViewById(R.id.home_schedule_date);
    }

    public void onBind(@NonNull HomeSchdeule data){

//        Glide.with(itemView).load(data.getPoster()).into(poster);
        place.setText(data.getPlace());
        date.setText(data.getDate());
        name.setText(data.getName());

//
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(view.getContext(), ScheduleImageActivity.class);
//                intent1.putExtra("poster", posterSrc);
//                view.getContext().startActivity(intent1);
//            }
//        });

    }
}
