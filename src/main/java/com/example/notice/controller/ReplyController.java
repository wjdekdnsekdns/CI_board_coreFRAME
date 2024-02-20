package com.example.notice.controller;

import com.example.notice.domain.Reply;
import com.example.notice.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/reply")
@RequiredArgsConstructor // 추가1
public class ReplyController {
    private final ReplyService replyService;

    @PostMapping("/replyWrite")
    public String replyWrite(@RequestParam("replyContent") String replyContent, @RequestParam("password") String password, @RequestParam("boardNum") int boardNum) {

        replyService.replyInsert(replyContent, password, boardNum);
        return "redirect:/board/details/" + boardNum;
    }

    @PostMapping("/replyUpdate")
    public String replyUpdate(@RequestParam("replyContent") String replyContent, @RequestParam("replyNum") int replyNum, @RequestParam("boardNum") int boardNum) {
        replyService.replyUpdate(replyContent, replyNum);
        return "redirect:/board/details/" + boardNum;
    }

    //    댓글 삭제
    @PostMapping("/replyDelete")
    public String replyDelete(@RequestParam("replyNum") int replyNum, @RequestParam("boardNum") int boardNum) {
        System.out.println("replyNum : " + replyNum);
        System.out.println("boardNum : " + boardNum);
        replyService.replyDelete(replyNum);
        return "redirect:/board/details/" + boardNum;
    }
}
