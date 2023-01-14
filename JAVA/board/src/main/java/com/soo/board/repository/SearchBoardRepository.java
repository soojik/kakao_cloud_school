package com.soo.board.repository;

import com.soo.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchBoardRepository {

    Board search01();

    Page<Object[]> searchPage(String type, String keyword, Pageable pageable);
}
