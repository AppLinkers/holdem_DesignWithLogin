package com.example.ticket.ui.dataService;

import com.example.ticket.ui.entity.HoldemPub;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HoldemPubs {
    @GET("/holdem_pub/list")
    Call<List<HoldemPub>> holdemPubs();
}
