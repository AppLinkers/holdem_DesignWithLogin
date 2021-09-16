package com.example.ticket.ui.ticket;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Ticket {

    Long id;
    String name;
    String place;
    String price;
    String poster;
}
