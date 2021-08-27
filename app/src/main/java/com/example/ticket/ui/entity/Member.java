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
public class Member {
    private Long id;
    private String user_id;
    private String user_name;
    private String user_pass;
    private String user_phone;
    private String user_loc;

}
