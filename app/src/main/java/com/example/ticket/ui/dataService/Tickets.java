package com.example.ticket.ui.dataService;

import com.example.ticket.ui.entity.TicketDto;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Tickets {
    @GET("/ticket/list")
    Call<List<TicketDto>> tickets();

    @POST("ticket/new")
    Call<TicketDto> addTicket(@Body Map<String, String> map);
}
