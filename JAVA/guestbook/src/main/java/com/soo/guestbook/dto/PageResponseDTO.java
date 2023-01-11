package com.soo.guestbook.dto;

import lombok.Data;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
// DTO는 결과를 출력할 때 Data 클래스
public class PageResponseDTO<DTO, EN> {

    private List<DTO> dtoList;

    // 전체 페이지 개수
    private int totalPage;

    // 현재 페이지 번호
    private int page;
    // 하나의 페이지에 출력할 데이터 개수
    private int size;

    // 페이지 번호에서 시작하는 페이지 번호 끝나는 페이지 번호
    private int start, end;

    // 이전과 다음 여부
    private boolean prev, next;

    // 페이지 번호 목록
    private List<Integer> pageList;

    // paging 결과를 가지고 추가한 항목들을 계산해주는 메서드
    private void makePageList(Pageable pageable) {
        // 현재 페이지 번호
        // JPA는 페이지 번호가 0부터 시작하므로 + 1
        this.page = pageable.getPageNumber() + 1;
        // 페이지 당 데이터 개수
        this.size = pageable.getPageSize();

        // 임시로 마지막 페이지 번호를 생성
        // 페이지 번호를 10개 출력
        // 10으로 나누어서 소수가 있으면 올림을 하고 곱하기 10
        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
        // 시작하는 번호
        start = tempEnd - 9;
        // 이전 여부
        prev = start > 1;
        // 마지막 페이지 번호 계산
        end = totalPage > tempEnd ? tempEnd : totalPage;
        // 다음 여부
        next = totalPage > tempEnd;
        // 페이지 번호 목록
        // start부터 end까지를 stream으로 만들어서 List로 변환
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    // page에 함수를 적용해서 List로 변환해주는 메서드
    // 첫번째 매개변수 - page 단위의 entity
    // 두번쨰 매개변수 - 데이터 변환을 위한 메서드
    public PageResponseDTO(Page<EN> result, Function<EN, DTO> fn) {
        // EN(entity)과 DTO(클래스 타입)을 변환해주는 함수를 매개변수로 받아서 DTO 타입의 List로 변환해주는 것
        dtoList = result.stream()
                .map(fn)
                .collect(Collectors.toList());

        // 전체 페이지 개수
        totalPage = result.getTotalPages();
        // 페이지 목록 메서드 호출
        makePageList(result.getPageable());
    }


}
