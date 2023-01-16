package com.soo.film.repository;

import com.soo.film.domain.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    // from에 들어가는 이름은 table이 아닌 Entity 이름
    // Entity e on 외래키 지정
    // group by 함으로써 movie 정보는 하나씩
    // coalesce 함으로써 r.grade에 정보가 없다면(null) 0으로 대체해준다.
    // count할 때 distinct를 붙여 중복 결과 제거
    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(distinct r.rno) from Movie m left outer join MovieImage mi on mi.movie = m left outer join Review r on r.movie = m group by m")
    public Page<Object[]> getList(Pageable pageable);
}
