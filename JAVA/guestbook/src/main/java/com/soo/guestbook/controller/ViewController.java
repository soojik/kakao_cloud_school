package com.soo.guestbook.controller;

import com.soo.guestbook.domain.GuestBook;
import com.soo.guestbook.dto.GuestBookDTO;
import com.soo.guestbook.dto.PageRequestDTO;
import com.soo.guestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final Logger LOGGER = LoggerFactory.getLogger(ViewController.class);

    private final GuestBookService guestBookService;

    @GetMapping("/")
    public String list() {
        LOGGER.info("메인 화면");
        return "redirect:/guestbook/list";
    }

    @GetMapping("/guestbook/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("result", guestBookService.getList(pageRequestDTO));
    }

    // 데이터 삽입을 위한
    @GetMapping("/guestbook/register")
    public void register() {
        LOGGER.info("데이터 삽입 요청");
    }

    @PostMapping("/guestbook/register")
    public String register(GuestBookDTO dto, RedirectAttributes rattr) {
        // 파라미터 확인
        LOGGER.info(dto.toString());
        Long gno = guestBookService.register(dto);

        // RedirectAttributes는 세션에 저장하는데 한 번 사용하고 자동 소멸
        // 그냥 addAttribute는 자동 소멸되지 않아 직접 삭제해줘야 한다.
        // 데이터 전송
        rattr.addFlashAttribute("msg", gno + " 등록");

        // 데이터베이스에 변경 작업을 수행하고 나면 반드시 redirect
        return "redirect:/guestbook/list";
    }
}
