package com.example.notice.service;

import com.example.notice.domain.Board;
import com.example.notice.domain.file.FileRequest;
import com.example.notice.mapper.FIleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service // 서비스 역할을 하는 것임을 명시
@RequiredArgsConstructor // Mapper 생성자를 사용할 수 있게 함
public class FIleService {

    private final FIleMapper fileMapper;
    private FileRequest fileRequest;

    @Transactional
    public void saveFiles(final int fileBoard, final List<FileRequest> files) {


        if (CollectionUtils.isEmpty(files)) {
            return;
        }
        for (FileRequest file : files) {
            file.setFileBoardNum(fileBoard);
            FileRequest fileRequest = FileRequest.builder()
                    .fileBoard(fileBoard)
                    .originalName(file.getOrg_File_Name())
                    .saveName(file.getStored_File_Name())
                    .size(file.getFile_Size())
                    .build();
            fileMapper.fileInsert(fileRequest);
        }
    }

    public List<FileRequest> fileList(int boardNum) {
        return fileMapper.getFile(boardNum);
    }

    @Transactional
    public void fileDelete(int fileNum) {
        fileMapper.fileDelete(fileNum);
    }

}
