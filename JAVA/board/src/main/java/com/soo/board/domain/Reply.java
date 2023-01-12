package com.soo.board.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long rno;

    private String text;

    private String replyer;

    @ManyToOne
    private Board board;
}
