package com.example.ticket.ui.schedule;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ScheduleImageActivity;

import org.jetbrains.annotations.NotNull;




public class SRecycleHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView place;
    public TextView time;
    public TextView ticket;
    public ImageView poster;
    public int posterSrc;

    public SRecycleHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.contest_name);
        place = (TextView) itemView.findViewById(R.id.contest_place);
        time = (TextView) itemView.findViewById(R.id.contest_time);
        ticket = (TextView) itemView.findViewById(R.id.contest_ticket);
        poster = (ImageView) itemView.findViewById(R.id.poster);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(view.getContext(), ScheduleImageActivity.class);
                intent1.putExtra("poster", posterSrc);
                view.getContext().startActivity(intent1);
            }
        });

    }

    public void onBind(Schedule data){
        poster.setImageResource(data.getPoster());
        place.setText(data.getPlace());
        time.setText(data.getTime());
        name.setText(data.getName());
        posterSrc = data.getPoster();
    }
}
