package com.soo.secondspring.repository;

import com.soo.secondspring.domain.Memo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    Page<Memo> findByMnoBetween(Long from, Long to, Pageable pageable);

    void deleteByMnoLessThan(Long mno);

    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
    public int updateMemoText(@Param("mno") long mno,
                              @Param("memoText") String memoText);

    @Transactional
    @Modifying
    @Query("update Memo m set m.memoText = :#{#param.memoText} where m.mno = :#{#param.mno}")
    public int updateMemoTextEntity(@Param("param") Memo memo);

    @Query("select m from Memo m where m.mno > :mno")
    Page<Memo> getListWithQuery(@Param("mno") long mno,
                                Pageable pageable);

    // mno, memoText와 CURRENT_DATE를 모두 조회할 적당한 Entity가 존재하지 않는다.
    @Query("select m.mno, m.memoText, CURRENT_DATE from Memo m where m.mno > :mno")
    Page<Object []> getObjectWithQuery(@Param("mno") long mno, Pageable pageable);
}
