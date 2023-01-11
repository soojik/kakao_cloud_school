package com.soo.guestbook.controller;

import com.soo.guestbook.domain.GuestBook;
import com.soo.guestbook.dto.GuestBookDTO;
import com.soo.guestbook.dto.PageRequestDTO;
import com.soo.guestbook.dto.PageResponseDTO;
import com.soo.guestbook.service.GuestBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JSONController {

    private final GuestBookService guestBookService;

    @GetMapping("/guestbook/list.json")
    public PageResponseDTO<GuestBookDTO, GuestBook> list(PageRequestDTO requestDTO) {
        return guestBookService.getList(requestDTO);
    }
}
