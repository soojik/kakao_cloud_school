package com.soo.guestbook.service;

import com.soo.guestbook.domain.GuestBook;
import com.soo.guestbook.dto.GuestBookDTO;
import com.soo.guestbook.dto.PageRequestDTO;
import com.soo.guestbook.dto.PageResponseDTO;
import com.soo.guestbook.repository.GuestBookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {

    private final Logger LOGGER = LoggerFactory.getLogger(GuestBookServiceImpl.class);

    private final GuestBookRepository guestBookRepository;

    @Override
    public Long register(GuestBookDTO dto) {
        // 파라미터가 제대로 넘어오는지 확인
        LOGGER.info("삽입 데이터: " + dto.toString());
        GuestBook entity = dtoToEntity(dto);
        // save할 때 설정한 내역을 가지고 필요한 데이터를 설정
        // gno, regDate, modDate가 설정된다.
        guestBookRepository.save(entity);
        // entity 데이터는 실제 메모리에 있는 데이터이기 때문에
        // 기본키 반환
        return entity.getGno();
    }

    @Override
    public PageResponseDTO<GuestBookDTO, GuestBook> getList(PageRequestDTO requestDTO) {
        // Repository에게 넘겨줄 Pageable 객체를 생성
        // gno의 내림차순으로 정렬
        Pageable pageable = requestDTO.getPageable(Sort.by("gno").descending());

        // 데이터 찾아오기
        Page<GuestBook> result = guestBookRepository.findAll(pageable);

        // Function<> : 받은 데이터 목록을 순회하며 제공된 메서드가 리턴하는 목록으로 변경해주는 람다
        Function<GuestBook, GuestBookDTO> fn = (entity -> entityToDTO(entity));

        return new PageResponseDTO<>(result, fn);

    }
}
