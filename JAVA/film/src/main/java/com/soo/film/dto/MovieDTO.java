package com.soo.film.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO {
  private Long mno;
  private String title;

  // review의 grade 평균
  private double avg;

  // 리뷰 개수
  private Long reviewCnt;

  // 등록일과 수정일
  private LocalDateTime regDate;
  private LocalDateTime modDate;

  // 영화 이미지 같이 등록
  @Builder.Default
  private List<MovieImageDTO> imageDTOList = new ArrayList<>();
}
