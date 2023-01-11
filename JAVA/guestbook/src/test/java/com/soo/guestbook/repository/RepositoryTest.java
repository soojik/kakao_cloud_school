package com.soo.guestbook.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.soo.guestbook.domain.GuestBook;
import com.soo.guestbook.domain.QGuestBook;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.IntStream;

@SpringBootTest
public class RepositoryTest {

    private final Logger LOGGER = LoggerFactory.getLogger(RepositoryTest.class);

    @Autowired
    private GuestBookRepository guestBookRepository;

    @Test
    public void insertDummies() {
        IntStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook guestBook = GuestBook.builder()
                    .title("제목 ... " + i)
                    .content("내용 ... " + i)
                    .writer("user" + i)
                    .build();

            LOGGER.info(guestBookRepository.save(guestBook).toString());
        });

    }

    @Test
    public void updateDummies() {
        GuestBook guestBook = GuestBook.builder()
                .gno(301L)
                .title("제목 변경")
                .content("내용 변경")
                .writer("soo")
                .build();


        LOGGER.info(guestBookRepository.save(guestBook).toString());
    }

    @Test
    // title에 1이라는 글자가 포함된 Entity 조회
    public void testQuery() {
        // 10개씩 첫번째 페이지의 데이터를 조회
        // modDate와 같은 last modified data로 정렬하는 상황도 고려 필요
        Pageable pageable = PageRequest.of(0, 10, Sort.by("modDate").descending());

        // querydsl 수행
        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        // title에 1이 포함된 조건을 BooleanBuilder로 생성
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression expression = qGuestBook.title.contains(keyword);
        builder.and(expression);

        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        for (GuestBook book : result.getContent()) {
            LOGGER.info(book.toString());
        }
    }

    @Test
    // title 또는 content에 1이라는 글자가 포함된 Entity 조회
    public void testQuery2() {
        // 10개씩 첫번째 페이지의 데이터를 조회
        // modDate와 같은 last modified data로 정렬하는 상황도 고려 필요
        Pageable pageable = PageRequest.of(0, 10, Sort.by("modDate").descending());

        // querydsl 수행
        QGuestBook qGuestBook = QGuestBook.guestBook;

        String keyword = "1";

        // title에 1이 포함된 조건을 BooleanBuilder로 생성
        BooleanBuilder builder = new BooleanBuilder();
        BooleanExpression exTitle = qGuestBook.title.contains(keyword);
        BooleanExpression exContent = qGuestBook.content.contains(keyword);

        // title과 content에 조건을 or로 묶기
        BooleanExpression exAll = exTitle.or(exContent);

        builder.and(exAll);
        // title 또는 content에 1이 포함된 데이터 중, gno가 400보다 작은 데이터만 출력
        builder.and(qGuestBook.gno.lt(400L));

        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        for (GuestBook book : result.getContent()) {
            LOGGER.info(book.toString());
        }
    }

}
