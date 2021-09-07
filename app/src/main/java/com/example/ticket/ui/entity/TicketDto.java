package com.example.ticket.ui.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    private Long id;
    private String ticket_name; // 티켓 대회 이름?
    private String ticket_place; // 티켓 사용 장소
    private int ticket_price; // 가격
    private int ticket_chatNum; // 해당 티켓 채팅 수
    private String ticket_poster; // 이미지
}
