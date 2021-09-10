package com.example.ticket.ui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Message {

    private Long id;
    private String messageType;
    private Room room;
    private Long senderId;
    private String message;

    public Message(String messageType, Room room, Long senderId, String message) {
        this.messageType = messageType;
        this.room = room;
        this.senderId = senderId;
        this.message = message;
    }
}
