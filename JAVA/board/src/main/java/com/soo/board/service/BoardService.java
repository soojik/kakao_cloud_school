package com.soo.board.service;

import com.soo.board.domain.Board;
import com.soo.board.domain.Member;
import com.soo.board.dto.BoardDTO;
import com.soo.board.dto.PageRequestDTO;
import com.soo.board.dto.PageResponseDTO;
import org.springframework.data.domain.jaxb.SpringDataJaxb;

public interface BoardService {

    // 게시글 번호로 댓글 삭제
    void removeWithReplies(Long bno);

    // 게시글 목록 보기
    PageResponseDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO);

    // 게시글 상세 보기
    BoardDTO get(Long bno);

    // 게시물 수정
    Long modify(BoardDTO dto);

    // 게시글 등록
    long register(BoardDTO dto);

    // DTO -> Entity 변환 메서드
    default Board dtoToEntity(BoardDTO dto) {

        Member member = Member.builder()
                .email(dto.getWriterEmail())
                .build();

        return Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
    }

    // Entity -> DTO 변환 메서드

    default BoardDTO entityToDTO(Board entity, Member member, Long replyCnt) {

        BoardDTO dto = BoardDTO.builder()
                .bno(entity.getBno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .replyCnt(replyCnt)
                .build();

        return dto;
    }
}
