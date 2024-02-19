package com.example.notice.domain;

import lombok.Getter;
import lombok.Setter;
import oracle.sql.NUMBER;

import java.sql.Date;
@Getter
@Setter
public class BoardDetails {
    private int board_Num;
    private String title;
    private String content;
    private String writer;
    private Date write_Date;
    private String password;
    private int reply_Num;
    private String reply_Content;
    private String reply_Password;
    private Date reply_Date;
    private int file_Num;
    private String org_File_Name;
    private NUMBER file_Size;
}
