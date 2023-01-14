package com.soo.board.service;

import com.soo.board.dto.BoardDTO;
import com.soo.board.dto.PageRequestDTO;
import com.soo.board.dto.PageResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    public void 게시글_등록() {
        BoardDTO dto = BoardDTO.builder()
                .writerEmail("soo1@kakao.com")
                .title("등록 테스트")
                .content("등록 테스트 1")
                .build();

        System.out.println(boardService.register(dto));
    }

    @Test
    public void 게시글_목록_가져오기() {
        PageRequestDTO requestDTO = new PageRequestDTO();

        PageResponseDTO<BoardDTO, Object[]> result = boardService.getList(requestDTO);
        System.out.println(result);
    }

    @Test
    public void 게시글_가져오기() {
        Long bno = 100L;
        BoardDTO dto = boardService.get(bno);

        System.out.println(dto);
    }

    @Test
    public void 게시글_삭제() {
        boardService.removeWithReplies(50L);
    }

    @Test
    public void 게시글_수정() {
        BoardDTO dto = BoardDTO.builder()
                .bno(100L)
                .title("제목 변경")
                .content("내용 변경")
                .build();

        System.out.println(boardService.modify(dto));
    }
}
