package com.example.notice.controller;


import com.example.notice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor // 추가1
public class mainController {
    private final BoardService service;
    @GetMapping("/home")
    public String manpage(Model model) {
        model.addAttribute("list", service.boardList());
        return "home";
    }
}

