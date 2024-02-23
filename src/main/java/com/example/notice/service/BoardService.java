package com.example.notice.service;

import com.example.notice.domain.Board;
import com.example.notice.domain.BoardDetails;
import com.example.notice.domain.Search;
import com.example.notice.mapper.BoardMapper;
import com.example.notice.paging.Pagination;
import com.example.notice.paging.PagingResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service // 서비스 역할을 하는 것임을 명시
@RequiredArgsConstructor // Mapper 생성자를 사용할 수 있게 함
@Transactional(readOnly = true)
public class BoardService {

    private final BoardMapper boardMapper;

    // 게시글 리스트 반환
    public PagingResponse<Board> findAllBoard(final Search params) {
        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        int count = boardMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 Search 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<Board> list = boardMapper.getList(params);

        return new PagingResponse<>(list, pagination);
    }

    public List<Board> CommentList() {
        return boardMapper.getCommentList();
    }
    // 게시글 작성
    public void write(Board board) {
        boardMapper.write(board);
    }

    public void commentWrite(Board board, BoardDetails parent) {
        boardMapper.commentWrite(board, parent);
    }

    public void commentUpdate(Board board, BoardDetails parent) {
        boardMapper.commentUpdate(board, parent);
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
    public void boardUpdate(int boardNum, String boardTitle, String boardContent) {
        boardMapper.boardUpdate(boardNum, boardTitle, boardContent);
    }

    // 게시판 삭제
    @Transactional
    public void boardDelete(int boardNum) {
        boardMapper.boardDelete(boardNum);
    }
}
