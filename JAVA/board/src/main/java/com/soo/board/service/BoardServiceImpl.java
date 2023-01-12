package com.soo.board.service;

import com.soo.board.domain.Board;
import com.soo.board.domain.Member;
import com.soo.board.dto.BoardDTO;
import com.soo.board.dto.PageRequestDTO;
import com.soo.board.dto.PageResponseDTO;
import com.soo.board.repository.BoardRepository;
import com.soo.board.repository.ReplyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.function.Function;

@Log4j2
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    // 연쇄적으로 update/delete query가 일어난다면 Transaction 설정을 해줘야한다.
    @Transactional
    @Override
    public void removeWithReplies(Long bno) {
        // 댓글 먼저 삭제
        replyRepository.deleteByBno(bno);
        // 그 다음 게시글 삭제
        boardRepository.deleteById(bno);
    }

    @Override
    public PageResponseDTO<BoardDTO, Object[]> getList(PageRequestDTO requestDTO) {
        log.info("page request: " + requestDTO);

        // Entity를 DTO로 변환
        Function<Object[], BoardDTO> fn = ((entity) ->
                entityToDTO((Board) entity[0], (Member) entity[1], (Long) entity[2])
        );

        Page<Object[]> result = boardRepository.getBoardWithReplyCount(requestDTO.getPageable(Sort.by("bno").descending()));

        return new PageResponseDTO<>(result, fn);
    }

    @Override
    public BoardDTO get(Long bno) {
        Object result = boardRepository.getBoardByBno(bno);
        Object[] arr = (Object[]) result;

        return entityToDTO((Board) arr[0], (Member) arr[1], (Long) arr[2]);
    }

    @Override
    @Transactional
    public Long modify(BoardDTO dto) {
        // JPA 에서는 삽입, 수정 메서드가 동일
        // upsert(있으면 수정, 없으면 삽입)를 하고자하는 경우는 save를 호출하면 되지만
        // 명시적으로 update를 하고자하는 경우는 데이터가 있는지 확인한 후 수행하는 것이 좋다.

        // 데이터가 없다면
        if (dto == null) {
            return 0L;
        }

        // 데이터 존재 여부 확인
        Optional<Board> board = boardRepository.findById(dto.getBno());

        // 데이터가 존재한다면
        if (board.isPresent()) {

            // 게시글 제목과 내용 수정
            board.get().changeTitle(dto.getTitle());
            board.get().changeContent(dto.getContent());

            boardRepository.save(board.get());

            return board.get().getBno();
        }
        return 0L;
    }

    @Override
    public long register(BoardDTO dto) {
        log.info("Service: " + dto);

        Board board = dtoToEntity(dto);
        boardRepository.save(board);
        return board.getBno();
    }


}
