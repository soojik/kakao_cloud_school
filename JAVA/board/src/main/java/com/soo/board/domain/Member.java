package com.soo.board.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity(name = "tbl_member")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
// Entity와 DTO의 차이
// DTO는 getter, setter 모두 설정할 수 있는 @Data 어노테이션을 붙이지만
// Entity는 값이 함부로 수정되지 않도록 따로 setter를 두지 않는다.
@Getter
public class Member extends BaseEntity {

    // id 생성 전략은
    // 현재 기본키가 String이고, 입력받는 email이기 때문에 따로 설정하지 않는다.
    @Id
    private String email;

    private String passwd;

    private String name;

}
