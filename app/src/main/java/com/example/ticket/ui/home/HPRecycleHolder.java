package com.example.ticket.ui.home;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;

public class HPRecycleHolder extends RecyclerView.ViewHolder {

    public TextView name;
    public TextView place;
    public TextView star;
    public TextView price;

    public HPRecycleHolder(@NonNull View itemView) {
        super(itemView);

        name = (TextView) itemView.findViewById(R.id.home_pub_name);
        place = (TextView) itemView.findViewById(R.id.home_pub_place);
        star = (TextView) itemView.findViewById(R.id.home_pub_star);
        price = (TextView) itemView.findViewById(R.id.home_pub_price);
    }

    public void onBind(@NonNull HomePub data){

//        Glide.with(itemView).load(data.getPoster()).into(poster);
        price.setText(data.getPrice());
        place.setText(data.getPlace());
        star.setText(data.getStar());
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
