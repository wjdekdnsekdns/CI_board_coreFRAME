package com.example.notice.controller;


import com.example.notice.domain.Board;
import com.example.notice.domain.Search;
import com.example.notice.paging.PagingResponse;
import com.example.notice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor // 추가1
public class mainController {
    private final BoardService service;

    //    =========================================================
    // 페이징 테스트
    @GetMapping("/home")
    public String manpage(@ModelAttribute("params") final Search params, Model model) {
        PagingResponse<Board> response = service.findAllBoard(params);
        model.addAttribute("response", response);
        System.out.println(response.getPagination().getLimitStart());
        System.out.println(params.getRecordSize());
        return "home";
    }
}

