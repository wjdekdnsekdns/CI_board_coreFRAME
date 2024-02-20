package com.example.notice.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class Reply {
    private int reply_Num;
    private String reply_Content;
    private String reply_Password;
    private Date reply_Date;
    private int reply_Board;
}
