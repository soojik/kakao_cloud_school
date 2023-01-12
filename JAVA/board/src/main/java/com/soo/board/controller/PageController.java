package com.soo.board.controller;

import com.soo.board.dto.PageRequestDTO;
import com.soo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
@RequiredArgsConstructor
public class PageController {

    private final BoardService boardService;

    @GetMapping({"/", "/board/list"})
    public String list(PageRequestDTO requestDTO, Model model) {
        log.info("기본 목록 보기 요청");

        model.addAttribute("result", boardService.getList(requestDTO));

        return "board/list";
    }

}
