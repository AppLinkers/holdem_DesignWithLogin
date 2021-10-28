package com.example.ticket.ui.dataService;

import com.example.ticket.ui.entity.HoldemPub;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DataService {


    private String Local_URL = "http://10.0.2.2:8080/"; //Local Host test
    private String AWS_URL = "http://3.21.178.170/"; // AWS EC2 URL

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(AWS_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    public SingUp singUp = retrofitClient.create(SingUp.class);
    public Login login = retrofitClient.create(Login.class);
    public Schedules schedules = retrofitClient.create(Schedules.class);
    public HoldemPubs holdemPubs = retrofitClient.create(HoldemPubs.class);
    public Tickets tickets = retrofitClient.create(Tickets.class);
    public Chat chat = retrofitClient.create(Chat.class);
}

