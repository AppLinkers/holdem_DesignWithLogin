package com.example.ticket.ui.pub;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class Pub {

    Long id;
    String name;
    String place;
    String game;
    String time;
    String intro;
    String poster;

    boolean clicked;

}
