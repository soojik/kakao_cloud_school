package com.soo.board.service;

import com.soo.board.domain.Reply;
import com.soo.board.dto.BoardDTO;
import com.soo.board.dto.PageRequestDTO;
import com.soo.board.dto.PageResponseDTO;
import com.soo.board.dto.ReplyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private ReplyService replyService;

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

    @Test
    // Board가 Lazy로 늦게 가져오도록 설정되었으니, 모든 속성을 가져온 후 테스트하도록 @Transactional 어노테이션 붙이기
    @Transactional
    public void 댓글_가져오기() {
        List<ReplyDTO> list = replyService.getList(85L);
        System.out.println(list);
    }

    @Test
    public void 댓글_삽입() {
        ReplyDTO dto = ReplyDTO.builder()
                .text("댓글 삽입 테스트")
                .replyer("guest1")
                .bno(85L)
                .build();

        System.out.println(replyService.register(dto));
    }

}
