package com.soo.board.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.soo.board.domain.Board;
import com.soo.board.domain.QBoard;
import com.soo.board.domain.QMember;
import com.soo.board.domain.QReply;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
// @Query가 아닌 Querydsl을 사용하는 이유?
// JPA에서 쿼리를 실행하는 방법
// 1. JPARepository가 제공하는 기본 메서드 사용
// 테이블 전체 데이터 조회, 기본키를 가지고 하나의 데이터 조회, 삽입, 기본키 이용해 조건을 만들어 데이터 수정, 기본키 이용해 데이터 삭제, entity를 이용해 데이터 삭제
// 2. Query Method: Repository 인터페이스에 하나의 테이블에 대한 검색 및 삭제 메서드를 findBy, deleteBy 등과 같이 이름으로 생성
// join이 어렵다.
// 3. @Query를 이용한 JPQL이나 Native SQL을 작성해서 사용
// Querydsl: JPQL을 문자열로 작성하지 않고 Java 코드로 작성
// 장점
// - 문자열이 아니라 메서드를 사용하기 때문에 컴파일 할 때 오류를 확인하는 것이 가능하다.
// - 자바 코드를 이용하기 떄문에 조건문을 이용할 수 있어서 동적 쿼리를 생성하는 것이 편리하다.
// - 동적 쿼리는 상황에 따라 컬럼 이름이나 테이블 이름이 변경되는 쿼리

// 2개 테이블 이상을 사용하려면 2, 3번째 방법이 좋으며, 1개 테이블을 이용한 데이터 처리는 모두 사용 가능하다.
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {

    // QuerydslRepositorySupport에 default constructor가 없기 때문에 constructor를 직접 정의해서 필요한 constructor를 호출해줘야 한다.
    // 검색에 사용할 Entity 클래스를 대입해주어야 한다.
    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search01() {
        // JPQL를 동적으로 생성해서 실행
        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        // query 작성
        JPQLQuery<Board> jpqlQuery = from(board);
        // member와 join
        // 왜래키는 board의 writer
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));

        // reply와 join
        // 외래키는 reply의 board
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        /* 결과를 객체로 반환
        // 게시글 번호 별로 묶어서 board와 member의 email 그리고 reply의 개수를 가져오기
        jpqlQuery.select(board, member.email, reply.count()).groupBy(board);

        // bno가 1인 Board 데이터 조회
        // jpqlQuery.select(board).where(board.bno.eq(1L));

        // 쿼리 실행
        List<Board> result = jpqlQuery.fetch();

        log.info("search result: " + result.toString());
        */

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());

        tuple.groupBy(board);

        List<Tuple> result = tuple.fetch();

        log.info("search result(Tuple): " + result);

        return null;
    }

    @Override
    // 검색을 위한 메서드
    public Page<Object[]> searchPage(String type, String keyword, Pageable pageable) {
        QBoard board = QBoard.board;
        QMember member = QMember.member;
        QReply reply = QReply.reply;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member, reply.count());

        BooleanBuilder booleanBuilder = new BooleanBuilder();

        // 글 번호가 0보다 큰 (조건)
        BooleanExpression expression = board.bno.gt(0L);
        booleanBuilder.and(expression);

        if (type != null) {
            String[] typeArr = type.split("");
            BooleanBuilder conditionBuilder = new BooleanBuilder();

            for (String t : typeArr) {
                switch (t) {
                    case "t":
                        conditionBuilder.or(board.title.contains(keyword));
                        break;
                    case "c":
                        conditionBuilder.or(board.content.contains(keyword));
                        break;
                    case "w":
                        conditionBuilder.or(member.email.contains(keyword));
                        break;

                }
            }

            booleanBuilder.and(conditionBuilder);
        }
        // 조건을 tuple에 적용
        tuple.where(booleanBuilder);

        // bno을 기준으로 내림차순 정렬
        tuple.orderBy(board.bno.desc());

        // 그룹화
        tuple.groupBy(board);

        // page 처리
        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        // 데이터 가져오기
        List<Tuple> result = tuple.fetch();

        // return
        return new PageImpl<Object[]>(result.stream().map(t -> t.toArray()).collect(Collectors.toList()), pageable, tuple.fetchCount());
    }

}
