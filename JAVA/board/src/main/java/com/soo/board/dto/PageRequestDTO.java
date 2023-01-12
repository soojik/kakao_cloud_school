package com.soo.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@Builder
@AllArgsConstructor
// @NoArgsConstructor는 다시 만들면 안된다.
public class PageRequestDTO {

    private int page;
    private int size;

    private String type;
    private String keyword;

    // page와 size 값이 없을 때 사용할 기본값 설정을 위한 생성자
    public PageRequestDTO() {
        this.page = 1;
        this.size = 10;
    }

    // page와 size를 가지고 Pageable 객체 생성해주는 메서드
    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
