package com.example.ticket.ui.dataService;

import com.example.ticket.ui.entity.HoldemPub;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface HoldemPubs {
    @GET("/holdem_pub/list")
    Call<List<HoldemPub>> holdemPubs();

    @POST("/holdem_pub/new_member")
    Call<HoldemPub> likeHoldemPub(@Query("holdemPub_id") Long holdemPub_id, @Query("member_id") String member_id);

    @GET("/holdem_pub/list/{member_name}")
    Call<List<HoldemPub>> listHoldemPub(@Path("member_name") String member_name);

    @POST("/holdem_pub/delete_member")
    Call<HoldemPub> unLikeHoldemPub(@Query("holdemPub_id") Long holdemPub_id, @Query("member_id") String member_id);
}
