package com.example.ticket.ui.dataService;

import com.example.ticket.ui.entity.HoldemPub;
import com.example.ticket.ui.entity.TicketDto;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface Tickets {
    @GET("/ticket/list")
    Call<List<TicketDto>> tickets();

    @Multipart
    @POST("ticket/save")
    Call<TicketDto> addTicket(@Part MultipartBody.Part multipartFile, @PartMap Map<String, RequestBody> map);

    @GET("/ticket/list/{member_id}")
    Call<List<TicketDto>> findAllByMemberId(@Path("member_id") Long member_id);

    @GET("/ticket/remove/{ticket_id}")
    Call<Void> remove(@Path("ticket_id") Long ticket_id);

}
