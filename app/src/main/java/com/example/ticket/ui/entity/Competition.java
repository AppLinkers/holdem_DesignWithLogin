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
public class Competition {

    private Long id;
    private String cmp_name;
    private String cmp_img;
    private String cmp_place;
    private int cmp_buyIn;
    private String cmp_start;
    private String cmp_end;
}
