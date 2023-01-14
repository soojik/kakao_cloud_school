package com.soo.board.service;

import com.soo.board.domain.Board;
import com.soo.board.domain.Reply;
import com.soo.board.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {

    Long register(ReplyDTO dto);

    List<ReplyDTO> getList(Long bno);

    Long modify(ReplyDTO dto);

    Long remove(Long rno);

    // dto to entity
    default Reply dtoToEntity(ReplyDTO dto) {
        Board board = Board.builder()
                .bno(dto.getBno())
                .build();

        return Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .board(board)
                .build();
    }

    // entity to dto
    default ReplyDTO entityToDTO(Reply entity) {

        return ReplyDTO.builder()
                .rno(entity.getRno())
                .text(entity.getText())
                .replyer(entity.getReplyer())
                .board(entity.getBoard())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

    }
}
