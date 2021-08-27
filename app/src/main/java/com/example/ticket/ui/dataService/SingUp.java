package com.example.ticket.ui.dataService;

import com.example.ticket.ui.entity.Member;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SingUp {
    @POST("members/new")
    Call<Member> signUp(@Body Map<String, String> map);
}
