package com.soo.secondspring;

import com.soo.secondspring.domain.Memo;
import com.soo.secondspring.repository.MemoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    MemoRepository memoRepository;

    // 삽입 테스트
    @Test
    public void testInsert() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder()
                    .memoText("Sample ... " + i)
                    .build();

            memoRepository.save(memo);
        });
    }

    @Test
    public void testAll() {
        List<Memo> list = memoRepository.findAll();

        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void getById() {

        // 기본 키를 가지고 조회하면 없거나 1개의 데이터 리턴
        Optional<Memo> result = memoRepository.findById(300L);

        if (result.isPresent()) {
            System.out.println(result.get());
        } else {
            System.out.println("데이터 존재하지 않는다.");
        }
    }

    @Test
    public void updateTest() {
        Memo memo = Memo.builder()
                .mno(300L)
                .memoText("데이터 수정")
                .build();

        memoRepository.save(memo);
    }

    @Test
    public void deleteTestWithId() {
        memoRepository.deleteById(300L);
    }

    @Test
    public void deleteTestWithEntity() {
        Memo memo = Memo.builder()
                .mno(299L)
                .build();

        memoRepository.delete(memo);
    }

    @Test
    public void deleteWrongData() {
        try {
            memoRepository.deleteById(10L);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    @Test
    public void testPaging() {
        // 10개씩 0페이지
        Pageable pageable = PageRequest.of(0, 10);
        Page<Memo> result = memoRepository.findAll(pageable);

        // 전체 페이지 개수
        System.out.println(result.getTotalPages());

        // 조회된 데이터 순회
        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void testSort() {
        Sort sort = Sort.by("mno").descending();

        Pageable pageable = PageRequest.of(0, 10, sort);
        Page<Memo> result = memoRepository.findAll(pageable);
        for (Memo memo : result) {
            System.out.println(memo);
        }
    }

    @Test
    public void queryMethod2() {
        Pageable pageable = PageRequest.of(1, 5);
        Page<Memo> result = memoRepository.findByMnoBetween(200L, 250L, pageable);

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    // 특정 작업에서는 트랜잭션을 적용하지 않으면 예외 발생
    @Transactional
    // 트랜잭션이 적용되면 자동 Commit 되지 않으므로 @Commit 필요
    @Commit
    public void deleteMnoTest() {
        memoRepository.deleteByMnoLessThan(110L);
    }

    @Test
    public void testUpdateQuery() {
        System.out.println(memoRepository.updateMemoTextEntity(Memo.builder().mno(110L).memoText("문자열").build()));
        System.out.println(memoRepository.updateMemoText(110L, "문자열"));
    }

    @Test
    public void testSelectQuery() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Memo> result = memoRepository.getListWithQuery(295L, pageable);

        for (Memo memo : result.getContent()) {
            System.out.println(memo);
        }
    }

    @Test
    public void testObjectQuery() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("mno").descending());

        Page<Object[]> result = memoRepository.getObjectWithQuery(295L, pageable);

        for (Object[] memo : result.getContent()) {
            // [Ljava.lang.Object;@인스턴스ID 과 같은 형식으로 출력
            // System.out.println(memo);
            System.out.println(Arrays.toString(memo));
        }
    }
}
