package com.soo.secondspring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tbl_memo")

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long mno;

    @Column(length = 200, nullable = false)
    private String memoText;

}
