package com.soo.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
// 지연로딩 설정으로 toString의 nullPointerException 에러를 피하기 위해 toString에서 Member 가져오는 writer를 제외
@ToString(exclude = "writer")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bno;

    private String title;

    private String content;

    // 처음부터 가져오지 않고 사용할 때 가져온다.
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member writer;

    // title 수정하는 메서드
    public void changeTitle(String title) {
        // title이 null이거나 공백이라면 무제로 설정
        if (title == null || title.trim().length() == 0) {
            this.title = "무제";
            return;
        }
        this.title = title;
    }

    // content 수정하는 메서드
    public void changeContent(String content) {
        this.content = content;
    }

}
