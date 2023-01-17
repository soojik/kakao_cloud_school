package com.soo.film.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO {
  private Long mno;
  private String title;

  // 영화 이미지 같이 등록
  @Builder.Default
  private List<MovieImageDTO> imageDTOList = new ArrayList<>();
}
