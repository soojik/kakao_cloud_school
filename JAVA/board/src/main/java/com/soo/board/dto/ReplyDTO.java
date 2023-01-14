package com.soo.board.dto;

import com.soo.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    private Long rno;
    private String text;
    private String replyer;

    private Board board;

    private Long bno;

    private Timestamp regDate;
    private Timestamp modDate;
}
