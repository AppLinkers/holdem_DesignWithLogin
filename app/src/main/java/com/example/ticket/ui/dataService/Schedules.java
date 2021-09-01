package com.example.ticket.ui.dataService;

import com.example.ticket.ui.entity.Competition;
import com.example.ticket.ui.entity.Member;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Schedules {
    @GET("/competition/list")
    Call<List<Competition>> schedules();
}
