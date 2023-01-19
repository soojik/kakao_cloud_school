package com.soo.film.service;

import com.soo.film.domain.Movie;
import com.soo.film.domain.Review;
import com.soo.film.dto.ReviewDTO;
import com.soo.film.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {
  private final ReviewRepository reviewRepository;

  @Override
  public List<ReviewDTO> getList(Long mno) {
    Movie movie = Movie.builder().mno(mno).build();
    List<Review> result = reviewRepository.findByMovie(movie);
    return result.stream().map(review -> entityToDto(review))
            .collect(Collectors.toList());
  }

  @Override
  public Long register(ReviewDTO reviewDTO) {
    Review review = dtoToEntity(reviewDTO);
    reviewRepository.save(review);
    return review.getRno();
  }

  @Override
  public Long modify(ReviewDTO reviewDTO) {
    Review review = dtoToEntity(reviewDTO);
    reviewRepository.save(review);
    return review.getRno();
  }

  @Override
  public Long remove(Long rno) {
    reviewRepository.deleteById(rno);
    return rno;
  }
}