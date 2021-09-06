package com.example.ticket.ui.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class HoldemPub {

    private Long id;
    private String pub_name;
    private String pub_info;
    private String pub_open;
    private String pub_place;
    private String pub_img;
    private Game game;

}
