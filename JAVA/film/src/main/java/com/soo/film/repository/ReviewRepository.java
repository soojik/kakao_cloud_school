package com.soo.film.repository;

import com.soo.film.domain.Member;
import com.soo.film.domain.Movie;
import com.soo.film.domain.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    //테이블의 전체 데이터 가져오기 - Paging 가능

    //기본키들 가지고 데이터 1개 가져오기
    //데이터 삽입 과 수정(기본키들 조건으로 하는)에 사용되는 메서드 제공
    //기본키들 가지고 삭제하는 메서드 와 entity들 가지고 삭제
    //이름을 기반으로 하는 메서드 생성이 가능
    // JPQL을 이용한 쿼리 작성 가능
    // Querydsl을 이용한 쿼리 작성 가능
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    public List<Review> findByMovie(Movie movie);

    // 회원 정보로 데이터 삭제
    void deleteByMember(Member member);

    @Modifying
    @Query("update Review r set r.member=null where r.member.mid=:mid")
    void updateByMember(@Param("mid") Long mid);


}
