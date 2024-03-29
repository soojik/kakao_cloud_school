package com.soo.board.repository;

import com.soo.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>, SearchBoardRepository {

    // 글 번호를 가지고 데이터를 찾아오는 메서드 - 상세보기
    @Query("select b, w, count(r) from Board b " + "left join b.writer w " + "left outer join Reply r on r.board = b " + "where b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);

    // Board 데이터 가져올 때 writer 정보도 가져오는 메서드
    @Query("select b,w from Board b left join b.writer w where b.bno=:bno")
    public Object getBoardWithWriter(@Param("bno") Long bno);


    // 글번호를 받아서 Board와 그리고 Board와 관련된 Reply 정보 찾아오기
    // Board 1개에 여러개의 Reply가 존재
    // Board와 Reply를 결합한 형태의 목록으로 리턴
    @Query("select b, r from Board b left join Reply r on r.board=b where b.bno=:bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    // 게시글 목록과 작성자 정보 그리고 댓글의 개수를 가져오는 메서드
    @Query("select b, w, count(r) from Board b left join b.writer w left join Reply r on r.board = b group by b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

}
