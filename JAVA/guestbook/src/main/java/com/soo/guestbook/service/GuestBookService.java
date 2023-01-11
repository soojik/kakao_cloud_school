package com.soo.guestbook.service;


import com.soo.guestbook.domain.GuestBook;
import com.soo.guestbook.dto.GuestBookDTO;
import com.soo.guestbook.dto.PageRequestDTO;
import com.soo.guestbook.dto.PageResponseDTO;
import org.springframework.data.domain.PageRequest;

public interface GuestBookService {

    // 데이터삽입을 위한 메서드 - 기본키의 값 리턴
    // 매개변수는 대부분의 경우 DTO
    // 리턴 타입은 삽입된 데이터를 그대로 리턴하기도 하고
    // 성공과 실패 여부를 위해서 boolean을 리턴하기도
    // 영향 받은 행의 개수를 의미하는 int를 리턴하기도
    // 기본키의 값을 리턴하기도 한다.
    Long register(GuestBookDTO dto);

    PageResponseDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO);

    // DTO를 Entity로 변환해주는 메서드
    default GuestBook dtoToEntity(GuestBookDTO dto) {
        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno()) // 수정이나 삽입 시 필요
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

    // Entity를 DTO로 변환해주는 메서드
    default GuestBookDTO entityToDTO(GuestBook entity) {
        GuestBookDTO dto = GuestBookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();

        return dto;
    }
}
