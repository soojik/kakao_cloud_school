package com.soo.film.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
  private Long rno;
  private Long mno;
  private Long mid;
  private String nickname;
  private String email;
  private int grade;
  private String text;

  private LocalDateTime regDate;
  private LocalDateTime modDate;
}
