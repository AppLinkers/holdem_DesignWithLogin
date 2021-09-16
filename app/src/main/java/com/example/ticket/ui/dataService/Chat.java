package com.example.ticket.ui.dataService;

import com.example.ticket.ui.entity.Message;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Chat {
    @GET("/message/{room_id}")
    Call<List<Message>> messageList(@Path("room_id") Long room_id);

    @POST("/message")
    Call<Message> message(@Body Message message);
}
