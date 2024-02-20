package com.example.notice.service;

import com.example.notice.domain.Reply;
import com.example.notice.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 서비스 역할을 하는 것임을 명시
@RequiredArgsConstructor // Mapper 생성자를 사용할 수 있게 함
@Transactional(readOnly = true)
public class ReplyService {

    private final ReplyMapper replyMapper;

    //    댓글 전체 조쇠
    public List<Reply> getList(int boardNum) {
        return replyMapper.getReply(boardNum);
    }

    //댓글 입력
    public void replyInsert(String replyContent, String password, int boardNum) {
        replyMapper.replyInsert(replyContent, password, boardNum);
    }

    // 댓글 수정
    public void replyUpdate(String replyContent, int replyNum) {
        replyMapper.replyUpdate(replyContent, replyNum);
    }

    // 댓글 삭제
    public void replyDelete(int replyNum) {
        replyMapper.replyDelete(replyNum);
    }
}
