package com.soo.board.service;

import com.soo.board.domain.Board;
import com.soo.board.domain.Reply;
import com.soo.board.dto.ReplyDTO;
import com.soo.board.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO dto) {
        Reply reply = dtoToEntity(dto);
        replyRepository.save(reply);
        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno) {
        List<Reply> result = replyRepository.findByBoardOrderByRno(Board.builder()
                .bno(bno)
                .build());
        // result 내용 정렬하기 - 수정한 시간의 내림차순
        result.sort(new Comparator<Reply>() {
            @Override
            public int compare(Reply r1, Reply r2) {
                return r2.getModDate().compareTo(r1.getModDate());
            }
        });

        log.info("result of replyService getList: " + result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList()));
        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList());
    }

    @Override
    public Long modify(ReplyDTO dto) {
        Reply reply = dtoToEntity(dto);
        replyRepository.save(reply);
        return reply.getRno();
    }

    @Override
    public Long remove(Long rno) {
        replyRepository.deleteById(rno);
        return rno;
    }
}
