package com.soo.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private long bno;
    private String title;
    private String content;
    private String writerEmail;
    private String writerName;
    private long replyCnt;
    private Timestamp regDate;
    private Timestamp modDate;
}
