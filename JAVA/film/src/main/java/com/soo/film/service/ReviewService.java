package com.soo.film.service;


import com.soo.film.domain.Member;
import com.soo.film.domain.Movie;
import com.soo.film.domain.Review;
import com.soo.film.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {
  //영화 번호에 해당하는 리뷰를 전부 가져오기
  List<ReviewDTO> getList(Long mno);
  //리뷰 등록
  Long register(ReviewDTO reviewDTO);
  //리뷰 수정
  Long modify(ReviewDTO reviewDTO);
  //리뷰 삭제
  Long remove(Long rno);

  //DTO를 ENTITY로 변환해주는 메서드
  default Review dtoToEntity(ReviewDTO reviewDTO){
    Review review = Review.builder()
            .rno(reviewDTO.getRno())
            .grade(reviewDTO.getGrade())
            .text(reviewDTO.getText())
            .movie(Movie.builder().mno(
                    reviewDTO.getMno()).build())
            .member(Member.builder().mid(
                    reviewDTO.getMid()).build())
            .build();
    return review;
  }


  //ENTITY를 DTO로 변환해주는 메서드
  default ReviewDTO entityToDto(Review review){
    ReviewDTO reviewDTO = ReviewDTO.builder()
            .rno(review.getRno())
            .mno(review.getMovie().getMno())
            .mid(review.getMember().getMid())
            .email(review.getMember().getEmail())
            .nickname(review.getMember().getNickname())
            .grade(review.getGrade())
            .text(review.getText())
            .regDate(review.getRegDate())
            .modDate(review.getModate())
            .build();
    return reviewDTO;
  }
}

