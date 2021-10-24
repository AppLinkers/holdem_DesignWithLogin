package com.example.ticket.ui.pub;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ticket.R;
import com.example.ticket.ui.dataService.DataService;
import org.jetbrains.annotations.NotNull;

public class PMRecycleHolder extends RecyclerView.ViewHolder {

    public TextView Menu_name;
    public TextView Menu_price;

    DataService dataService = new DataService();


    public PMRecycleHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        Menu_name = (TextView) itemView.findViewById(R.id.menu_name);
        Menu_price = (TextView) itemView.findViewById(R.id.menu_price);

    }

    public void onBind(PubMenu data) {
        Menu_name.setText(data.getMenu_name());
        Menu_price.setText(data.getMenu_price());
    }
}
