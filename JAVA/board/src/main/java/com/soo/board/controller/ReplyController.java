package com.soo.board.controller;

import com.soo.board.dto.ReplyDTO;
import com.soo.board.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@Controller
@RequiredArgsConstructor
@RequestMapping("/replies")
public class ReplyController {

    private final ReplyService replyService;

    @GetMapping(value = "/board/{bno}")
    public ResponseEntity<List<ReplyDTO>> getReplies(@PathVariable("bno") Long bno) {
        log.info("bno: " + bno);

        return new ResponseEntity<>(replyService.getList(bno), HttpStatus.OK);
    }

    // 댓글 추가 요청 처리
    @PostMapping("")
    public ResponseEntity<Long> register(@RequestBody ReplyDTO dto) {
        log.info(dto);

        Long rno = replyService.register(dto);

        return new ResponseEntity<>(rno, HttpStatus.OK);
    }

    // 댓글 삭제 요청 처리
    @DeleteMapping("/{rno}")
    public ResponseEntity<String> remove(@PathVariable("rno") Long rno) {
        log.info("rno: " + rno);

        long remove_rno = replyService.remove(rno);

        return new ResponseEntity<>(remove_rno + " 삭제", HttpStatus.OK);
    }

    @PutMapping("/{rno}")
    public ResponseEntity<Long> modify(@RequestBody ReplyDTO dto, @PathVariable("rno") Long rno) {
        log.info("dto: " + dto);

        replyService.modify(dto);
        return new ResponseEntity<>(dto.getRno(), HttpStatus.OK);
    }
}
