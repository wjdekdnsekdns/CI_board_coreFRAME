package com.example.notice.domain.file;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import oracle.sql.NUMBER;

import java.sql.Date;
@Getter
@Setter
public class FileRequest  {
    private int file_Num;
    private String org_File_Name;
    private String stored_File_Name;
    private Long file_Size;
    private Date regDate;
    private int file_Board;

    @Builder
    public  FileRequest(int fileBoard, String originalName, String saveName, Long size) {
        this.file_Board = fileBoard;
        this.org_File_Name = originalName;
        this.stored_File_Name = saveName;
        this.file_Size = size;
    }

    public void setFileBoardNum(int fileBoard) {
        this.file_Board = fileBoard;
    }
}
