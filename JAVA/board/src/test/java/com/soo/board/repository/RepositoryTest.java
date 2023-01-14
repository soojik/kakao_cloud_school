package com.soo.board.repository;

import com.soo.board.domain.Board;
import com.soo.board.domain.Member;
import com.soo.board.domain.Reply;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class RepositoryTest {

    private final Logger LOGGER = LoggerFactory.getLogger(RepositoryTest.class);

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertMember() {
        for (int i = 0; i < 100; i++) {
            Member member = Member.builder()
                    .email("soo" + i + "@kakao.com")
                    .passwd("1234")
                    .name("지수")
                    .build();

            LOGGER.info(memberRepository.save(member).toString());
        }
    }

    @Test
    public void insertBoard() {
        for (int i = 0; i < 100; i++) {
            Member member = Member.builder()
                    .email("soo" + i + "@kakao.com")
                    .build();
            Board board = Board.builder()
                    .title("제목 ... " + i)
                    .content("내용 ... " + i)
                    .writer(member)
                    .build();

            LOGGER.info(boardRepository.save(board).toString());
        }
    }

    @Test
    public void insertReply() {
        for (int i = 1; i <= 300; i++) {
            long bno = (long) (Math.random() * 100 + 1);

            Board board = Board.builder()
                    .bno(bno).build();

            Reply reply = Reply.builder()
                    .text("댓글 ... " + i)
                    .board(board)
                    .replyer("guest" + i)
                    .build();

            LOGGER.info(replyRepository.save(reply).toString());
        }
    }

    // 게시글 1개 가져오는 메서드
    @Test
    @Transactional
    public void readBoard() {
        Optional<Board> result = boardRepository.findById(100L);

        Board board = result.get();

        // 원래는 항상 board를 가져올때 member도 함께 딸려오는데
        // 필요할 때만 member 가져올 수 있도록 수정 - Board에서 Member를 lazy 설정
        // 하지만 지연로딩으로 인해 writer의 nullpointerexception로 에러
        // 해결: toString의 항목에서 writer는 제외시킨다.
        // 하지만 writer도 함께 불러오고 싶다면?
        // 해당 메서드에 @Transactional 어노테이션을 붙여
        LOGGER.info(board.toString());
        LOGGER.info(board.getWriter().toString());
    }

    // 댓글 1개 가져오는 메서드
    @Test
    public void readReply() {
        Optional<Reply> result = replyRepository.findById(5L);

        Reply reply = result.get();

        LOGGER.info(reply.toString());
        LOGGER.info(reply.getBoard().toString());
    }

    // Board 데이터 가져올 때 Writer의 데이터도 가져오기
    @Test
    public void joinTest1() {
        Object result = boardRepository.getBoardWithWriter(100L);
        // 로그 봤을 때, 결과가 [Ljava.lang.Object;@759c53e5 형식으로 나오는 것을 보니 배열로 추측된다.
        LOGGER.info(result.toString());

        // 배열로 형변환 해서 출력
        LOGGER.info(Arrays.toString((Object[]) result));

        // 첫번째는 Board, 두번쨰는 Member 데이터를 반환하는 것을 확인했으니 각각 출력
        Object[] arr = (Object[]) result;
        Board board = (Board) arr[0];
        Member member = (Member) arr[1];
        LOGGER.info("board : " + board);
        LOGGER.info("member : " + member);

    }

    // Board 데이터 가져오며 reply 데이터들 함게 가져오기
    @Test
    public void joinTest2() {
        List<Object[]> result = boardRepository.getBoardWithReply(100L);
        for (Object[] arr : result) {
            LOGGER.info(Arrays.toString(arr));
        }
    }

    // 게시글 목록과 작성자 정보 그리고 댓글의 개수를 가져오는 메서드
    @Test
    public void joinTest3() {
        // bno를 기준으로 내림차순 정렬한 데이터를 10개씩 잘라, 그 자른 첫번째 데이터 묶음을 가져오도록 Pageable 설정
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Object[]> result = boardRepository.getBoardWithReplyCount(pageable);

        Board board;
        Member member;
        long replyCnt;
        for (Object[] arr : result.getContent()) {
            // 첫번째는 Board 데이터, 두번째는 Member 데이터, 세번째는 reply 개수
            // LOGGER.info(Arrays.toString(arr));
            board = (Board) arr[0];
            member = (Member) arr[1];
            replyCnt = (Long) arr[2];
            LOGGER.info("board : " + board);
            LOGGER.info("member : " + member);
            LOGGER.info("reply count : " + replyCnt);
        }
    }

    @Test
    public void testSearch01() {
        boardRepository.search01();
    }

    @Test
    public void testeSearch() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending()
                .and(Sort.by("title").ascending()));

        String keyword = "수정";

        // 제목에 keyword가 들어가는 데이터 조회
        Page<Object[]> result = boardRepository.searchPage("t", keyword, pageable);

        for (Object[] row : result.getContent()) {
            System.out.println(Arrays.toString(row));
        }
    }

    @Test
    public void testListReply() {
        List<Reply> replyList = replyRepository.findByBoardOrderByRno(Board.builder().bno(27L).build());

        replyList.forEach(reply -> {
            System.out.println(reply);
        });
    }


}
