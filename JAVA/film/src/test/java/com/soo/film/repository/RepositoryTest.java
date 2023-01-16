package com.soo.film.repository;

import com.soo.film.domain.Member;
import com.soo.film.domain.Movie;
import com.soo.film.domain.MovieImage;
import com.soo.film.domain.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository movieImageRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void 영화정보_삽입() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Movie movie = Movie.builder()
                    .title("Movie"+i)
                    .build();

            movieRepository.save(movie);

            // 0.0 ~ 1.0 사이의 랜덤한 실수
            // 1부터 5 사이의 수가 나오도록
            int count = (int)(Math.random() * 5) + 1;
            for (int j = 0; j < count; j++) {
                MovieImage movieImage = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();

                movieImageRepository.save(movieImage);
            }
        });


    }

    @Test
    public void 사용자_삽입() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("soo" + i + "@kakao.com")
                    .pw("1234")
                    .nickname("reviewer" + i)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void 영화리뷰_삽입() {
        IntStream.rangeClosed(1, 200).forEach(i -> {
            // 영화 번호
            Long mno = (long) (Math.random() * 100) + 1;
            // 회원 번호
            Long mid = (long) (Math.random() * 100) + 1;

            Movie movie = Movie.builder()
                    .mno(mno)
                    .build();

            Member member = Member.builder()
                    .mid(mid)
                    .build();

            Review review = Review.builder()
                    .movie(movie)
                    .member(member)
                    .grade((int) (Math.random() * 5) + 1)
                    .text("제가 " + i + "번째 관객인가요?")
                    .build();

            reviewRepository.save(review);
        });
    }

    @Test
    public void JOIN_연습() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Object[]> result = movieRepository.getList(pageable);

        for (Object[] obj : result.getContent()) {
            System.out.println(Arrays.toString(obj));
        }
    }
}
