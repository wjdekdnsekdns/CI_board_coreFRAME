package com.example.notice.service;

import com.example.notice.domain.Board;
import com.example.notice.domain.BoardDetails;
import com.example.notice.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 서비스 역할을 하는 것임을 명시
@RequiredArgsConstructor // Mapper 생성자를 사용할 수 있게 함
@Transactional(readOnly = true)
public class BoardService {

    private final BoardMapper boardMapper;

    // 게시글 리스트 반환
    public List<Board> boardList() {
        return boardMapper.getList();
    }

    // 게시글 작성
    public void write(Board board){
        boardMapper.write(board);
    }

    // 게시글 상세 조회
    public BoardDetails getBoard(int boardNum) {
        return boardMapper.getBoard(boardNum);
    }

    // 게시판 상세 페이지
    public BoardDetails getUpdateBoard(int boardNum) {
        return boardMapper.getUpdateBoard(boardNum);
    }

    // 게시판 수정
    @Transactional
    public void boardUpdate(int boardNum ,String boardTitle, String boardContent){
        boardMapper.boardUpdate(boardNum,boardTitle,boardContent);
    }

    // 게시판 삭제
    @Transactional
    public void boardDelete(int boardNum ){
        boardMapper.boardDelete(boardNum);
    }
}
