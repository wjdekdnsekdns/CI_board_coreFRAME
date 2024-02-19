package com.example.notice.domain;


import lombok.Getter;

import lombok.Setter;

import java.sql.Date;

@Getter
@Setter

public class Board {
    private int board_Num;
    private String title;
    private String content;
    private String writer;
    private Date write_Date;
    private String password;
    private int group_Id;
    private int group_Order;
    private int depth;
    private int boardMasterId;
}
