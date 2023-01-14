package com.soo.board.controller;

import com.soo.board.dto.BoardDTO;
import com.soo.board.dto.PageRequestDTO;
import com.soo.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"/", "/board/list"})
    public String list(@ModelAttribute("requestDTO") PageRequestDTO requestDTO, Model model) {
        log.info("기본 목록 보기 요청");
        log.info(requestDTO);

        model.addAttribute("result", boardService.getList(requestDTO));

        return "board/list";
    }

    // 게시물 등록 화면으로 이동하는 요청 - Forwarding
    @GetMapping("/board/register")
    public void register(Model model) {
        log.info("등록 화면으로 포워딩");
    }

    // 게시물 등록하는 요청 - Redirect
    // RedirectAttributes - 1회용 세션
    @PostMapping("/board/register")
    public String register(BoardDTO dto, RedirectAttributes rattr) {
        // 파라미터 확인
        log.info("dto: " + dto);
        // 데이터 삽입
        Long bno = boardService.register(dto);

        rattr.addFlashAttribute("msg", bno + " 등록");

        return "redirect:/board/list";
    }

    // ModelAttribute는 파라미터로 사용하면 넘겨 받은 데이터를 결과에 그대로 전달할 목적으로 사용
    @GetMapping({"/board/read", "/board/modify"})
    public void read(@ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                     Long bno, Model model) {
        log.info("글 번호: " + bno);
        BoardDTO dto = boardService.get(bno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/board/modify")
    public String modify(BoardDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO requestDTO,
                         RedirectAttributes rattr) {

        log.info("dto: " + dto.toString());

        boardService.modify(dto);

        rattr.addFlashAttribute("bno", dto.getBno());
        rattr.addFlashAttribute("page", requestDTO.getPage());
        rattr.addFlashAttribute("type", requestDTO.getType());
        rattr.addFlashAttribute("keyword", requestDTO.getKeyword());

        log.info(dto.getBno());

        return "redirect:/board/read?bno=" + dto.getBno()
                + "&page=" + requestDTO.getPage()
                + "&type=" + requestDTO.getType()
                + "&keyword=" + requestDTO.getKeyword();
    }

    // requestDTO 필요 없는 이유 -> 지워지게 되면 페이지 번호가 바뀔 수도 있고 ...
    @PostMapping("/board/remove")
    public String remove(BoardDTO dto,
                         RedirectAttributes rattr) {
        log.info("removed dto: " + dto);

        boardService.removeWithReplies(dto.getBno());
        rattr.addFlashAttribute("msg", dto.getBno() + " 삭제");

        return "redirect:/board/list";

    }

}
