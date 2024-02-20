package com.example.notice.mapper;

import com.example.notice.domain.Reply;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyMapper {
    List<Reply> getReply(int boardNum);

    int replyInsert( String replyContent, String password, int boardNum);
    int replyUpdate(String replyContent, int replyNum);

    int replyDelete(int replyNum);
}
