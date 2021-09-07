package com.example.ticket.ui.dataService;

import com.example.ticket.ui.entity.TicketDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Tickets {
    @GET("/ticket/list")
    Call<List<TicketDto>> tickets();
}
