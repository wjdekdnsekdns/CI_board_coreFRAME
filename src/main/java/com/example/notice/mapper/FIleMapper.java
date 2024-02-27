package com.example.notice.mapper;

import com.example.notice.domain.file.FileRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FIleMapper {

void fileInsert(FileRequest fileRequest);

    // 파일 리스트 조회
    List<FileRequest> getFile(int boardNum);

    void fileDelete(int fileNum);
    FileRequest fileDown(String fileDown);


}
