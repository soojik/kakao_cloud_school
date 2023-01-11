package com.soo.guestbook.service;

import com.soo.guestbook.domain.GuestBook;
import com.soo.guestbook.dto.GuestBookDTO;
import com.soo.guestbook.dto.PageRequestDTO;
import com.soo.guestbook.dto.PageResponseDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

    private final Logger LOGGER = LoggerFactory.getLogger(ServiceTest.class);

    @Autowired
    private GuestBookService guestBookService;


    @Test
    public void testRegister() {
        GuestBookDTO dto = GuestBookDTO.builder()
                .title("샘플 제목")
                .content("샘플 내용")
                .writer("soo")
                .build();

        LOGGER.info(guestBookService.register(dto).toString());
    }

    @Test
    public void testList() {
        PageRequestDTO requestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<GuestBookDTO, GuestBook> result = guestBookService.getList(requestDTO);

        for (GuestBookDTO dto : result.getDtoList()) {
            LOGGER.info(dto.toString());
        }
    }

    @Test
    public void testListInformation() {
        PageRequestDTO requestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        PageResponseDTO<GuestBookDTO, GuestBook> result = guestBookService.getList(requestDTO);

        // 데이터 확인
        // 데이터 목록
        LOGGER.info(result.getDtoList().toString());
    }

}
