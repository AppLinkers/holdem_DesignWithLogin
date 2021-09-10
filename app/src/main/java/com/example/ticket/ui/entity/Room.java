package com.example.ticket.ui.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {

    private Long id;
    private Long ticketId;
    private Long buyerId;
    private String buyerLoginId;
}
