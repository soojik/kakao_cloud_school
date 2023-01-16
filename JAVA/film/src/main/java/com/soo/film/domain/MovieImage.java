package com.soo.film.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
// 지연 생성이기 때문에 get을 하지 않은 상태에서 toString을 호출하면 NullPointerException 발생
@ToString(exclude = "movie")
// 부모 테이블 생성 시, 이 속성의 값을 포함시켜 생성
// 보통 1:1 관계의 테이블을 나눴을 때 자주 사용
@Embeddable
public class MovieImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ino;

    // 파일 이름 겹치지 않도록 하기 위한 속성
    private String uuid;

    // 실제 이미지 파일 이름
    private String imgName;

    // 하나의 디렉토리에 너무 많은 파일이 저장되지 않도록
    // 업로드한 날짜별로 파일 기록하기 위한 디렉토리 경로(이름)
    private String path;

    // 하나의 Movie가 여러 개의 MovieImage를 소유
    // 데이터를 불러올 때 movie를 불러오지 않고 사용할 때 불러온다
    // 외래키의 이름은 movie_mno로 만들어진다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
