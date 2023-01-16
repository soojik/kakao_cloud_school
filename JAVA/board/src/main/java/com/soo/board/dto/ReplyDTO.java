package com.soo.board.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.soo.board.domain.Board;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    private Long rno;
    private String text;
    private String replyer;

    @JsonIgnore
    private Board board;

    private Long bno;

    private Timestamp regDate;
    private Timestamp modDate;
}
