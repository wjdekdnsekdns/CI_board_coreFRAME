package com.example.notice.controller;


import com.example.notice.domain.Board;
import com.example.notice.domain.BoardDetails;
import com.example.notice.domain.file.FileRequest;
import com.example.notice.service.BoardService;
import com.example.notice.service.FIleService;
import com.example.notice.util.FileUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping(value = "/board")
@RequiredArgsConstructor // 추가1
public class BoardController {

    private final BoardService service;
    private final FIleService fileService;
    private final FileUtils fileUtils;

    //    게시글 작성 페이지 이동
    @GetMapping("/writePage")
    public String BoardWritePage() {
        return "BoardWrite";
    }

    // 게시글 작성
    @PostMapping("/write")
    public String write(Board board, @RequestParam("files") List<MultipartFile> files) {
        service.write(board);
        //int id = board.getBoardNum();
        int id = board.getBoard_Num();
        List<FileRequest> uploadedFiles = fileUtils.uploadFiles(files);
        fileService.saveFiles(id, uploadedFiles);
        return "redirect:/home";
    }

    // 게시글 상세 페이지 이동
    @GetMapping("/details/{boardNum}")
    public String details(@PathVariable int boardNum, Model model) {
        BoardDetails boardDetails = service.getBoard(boardNum);
        model.addAttribute("list", fileService.fileList(boardNum));
        model.addAttribute("view", boardDetails);
        return "NoticeDetails";
    }

    // 게시글 수정 페이지 이동
    @GetMapping("/boardUpdatePage/{boardNum}")
    public String boardUpdate(@PathVariable int boardNum, Model model){
        BoardDetails boardDetails = service.getUpdateBoard(boardNum);
        model.addAttribute("view", boardDetails);
        return "BoardUpdate";
    }

    // 게시글 수정
    @PostMapping("/boardUpdatePage/boardUpdate")
    public String boardUpdate(@RequestParam("boardNum") int boardNum,@RequestParam("boardTitle") String boardTitle,@RequestParam("boardContent") String boardContent){
        service.boardUpdate(boardNum,boardTitle,boardContent);
        return "redirect:/home";
    }

    // 게시글 삭제
    @PostMapping("/boardDelete")
    public String boardDelete(@RequestParam("boardNum") int boardNum){
        service.boardDelete(boardNum);
        return "redirect:/home";
    }
}
