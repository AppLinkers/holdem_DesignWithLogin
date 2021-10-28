package com.example.ticket.ui.mypage;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticket.R;
import com.example.ticket.ui.dataService.DataService;
import com.example.ticket.ui.ticket.Ticket;

import java.io.IOException;

import retrofit2.Call;

public class MySellHolder extends RecyclerView.ViewHolder  {

    DataService dataService = new DataService();
    MypageFragment mypageFragment = new MypageFragment();

    Long ticket_id = 0l;

    TextView sellChat;
    TextView sellLocate;
    Button delSellBtn;

    public MySellHolder(@NonNull View itemView) {
        super(itemView);

        sellChat = itemView.findViewById(R.id.sellChat);
        sellLocate = itemView.findViewById(R.id.sellLocate);
        delSellBtn = itemView.findViewById(R.id.delSellBtn);
        delSellBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"StaticFieldLeak", "NewApi"})
            @Override
            public void onClick(View view) {
                AsyncTask<Void, Void, Void> listAPI = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... params) {
                        Call<Void> call = dataService.tickets.remove(ticket_id);
                        try {
                            return call.execute().body();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void s) {
                        super.onPostExecute(s);
                    }
                }.execute();


            }
        });
    }


    public void onBind(Ticket data){

        sellChat.setText(data.getName());
        sellLocate.setText(data.getPlace());
        ticket_id = data.getId();

    }
}
