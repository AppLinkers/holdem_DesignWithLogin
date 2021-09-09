package com.example.ticket.ui.ticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;

import java.util.ArrayList;

public class TRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {


    private ArrayList<Ticket> listData = new ArrayList<>();
    Context context;

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
        ((TRecycleHolder) holder).onBind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addItem(Ticket data) {
        listData.add(data);
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                ArrayList<Ticket> arrayListFilter = new ArrayList<Ticket>();

                if (constraint == null || constraint.length() == 0) {
                    results.count = listData.size();
                    results.values = listData;
                } else {
                    for (Ticket ticket : listData) {
                        if (ticket.getName().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            arrayListFilter.add(ticket);
                        }
                    }
                    results.count = arrayListFilter.size();
                    results.values = arrayListFilter;

                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                listData = (ArrayList<Ticket>) results.values;
                notifyDataSetChanged();


            }
        };
        return filter;
    }
}
