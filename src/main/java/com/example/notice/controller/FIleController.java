package com.example.notice.controller;

import com.example.notice.service.FIleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/file")
@RequiredArgsConstructor // 추가1
public class FIleController {
//    파일 삭제
    private  final FIleService fIleService;
    @PostMapping("/fileDelete")
    public String fileDelete(@RequestParam("fileNum")int fileNum,@RequestParam("boardNum")int boardNum){
        fIleService.fileDelete(fileNum);
        return "redirect:/board/boardUpdatePage/" + boardNum;
    }
}
