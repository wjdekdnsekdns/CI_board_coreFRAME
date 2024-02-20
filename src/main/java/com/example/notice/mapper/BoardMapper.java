package com.example.notice.mapper;

import com.example.notice.domain.Board;
import com.example.notice.domain.BoardDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardMapper {

    // 게시판 리스트 출력
    List<Board> getList();

    // 게시판 작성
    void write(Board board);

    // 게시판 상세 조회
    BoardDetails getBoard(int boardNum);



    // 게시판 수정 페이지
    BoardDetails getUpdateBoard(int boardNum);

    // 게시판 수정
    void boardUpdate(int boardNum ,String boardTitle, String boardContent);

    // 게시판 삭제
    void boardDelete(int boardNum);
}
