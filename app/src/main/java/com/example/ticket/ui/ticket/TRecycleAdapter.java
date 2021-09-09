package com.example.ticket.ui.ticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ui.entity.TicketDto;
import com.example.ticket.ui.pub.PRecycleHolder;
import com.example.ticket.ui.pub.Pub;

import java.util.ArrayList;
import java.util.List;

public class TRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<Ticket> listData = new ArrayList<>();
    Context context;
    ArrayList<Ticket> arrayList, arrayListFiltered;

//    public TRecycleAdapter(Context context, ArrayList<Ticket> arrayList) {
//        this.context = context;
//        this.arrayList = arrayList;
//        this.arrayListFiltered = arrayList;
//    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_item, parent, false);

        return new TRecycleHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((TRecycleHolder)holder).onBind(listData.get(position));
//        ((TRecycleHolder) holder).name.setText(arrayListFiltered.get(position).getName());
//        ((TRecycleHolder) holder).place.setText(arrayListFiltered.get(position).getPlace());
//        ((TRecycleHolder) holder).price.setText(arrayListFiltered.get(position).getPrice());
//        ((TRecycleHolder) holder).poster.setImageResource(arrayListFiltered.get(position).getPoster());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(Ticket data) {
        listData.add(data);
    }


//    @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//                FilterResults results = new FilterResults();
//
//                ArrayList<Ticket> arrayListFilter = new ArrayList<Ticket>();
//
//                if (constraint == null || constraint.length() == 0) {
//                    results.count = arrayList.size();
//                    results.values = arrayList;
//                } else {
//                    for (Ticket ticket : arrayList) {
//                        if (ticket.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
//                            arrayListFilter.add(ticket);
//                        }
//                    }
//                    results.count = arrayListFilter.size();
//                    results.values = arrayListFilter;
//
//                }
//                return results;
//            }
//
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//
//                arrayListFiltered = (ArrayList<Ticket>) results.values;
//                notifyDataSetChanged();
//
//                if(arrayListFiltered.size() == 0) {
//                    Toast.makeText(context, "Not Found", Toast.LENGTH_LONG).show();
//                }
//            }
//        };
//        return filter;
//    }
}
