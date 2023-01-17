package com.soo.film.service;

import com.soo.film.domain.Movie;
import com.soo.film.domain.MovieImage;
import com.soo.film.dto.MovieDTO;
import com.soo.film.repository.MovieImageRepository;
import com.soo.film.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;

  private final MovieImageRepository movieImageRepository;

  @Override
  @Transactional
  public Long register(MovieDTO dto) {
    log.warn("movieDTO: " + dto);

    Map<String, Object> entityMap = dtoToEntity(dto);
    Movie movie = (Movie) entityMap.get("movie");

    List<MovieImage> movieImageList = (List<MovieImage>) entityMap.get("imgList");

    movieRepository.save(movie);
    movieImageList.forEach(movieImage -> {
      movieImageRepository.save(movieImage);
    });

    return movie.getMno();
  }
}
