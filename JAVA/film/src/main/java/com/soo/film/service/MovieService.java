package com.soo.film.service;

import com.soo.film.domain.Movie;
import com.soo.film.domain.MovieImage;
import com.soo.film.dto.MovieDTO;
import com.soo.film.dto.MovieImageDTO;
import com.soo.film.dto.PageRequestDTO;
import com.soo.film.dto.PageResponseDTO;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface MovieService {

  // 데이터 삽입을 위한 메서드
  Long register(MovieDTO dto);

  // 데이터 목록을 위한 메서드
  PageResponseDTO<MovieDTO, Object[]> getList(PageRequestDTO requestDTO);

  // 상세보기를 위한 메서드
  MovieDTO getMovie(Long mno);

  // dto to entity
  // dto가 하나의 엔티티가 아닌 movie, movieImage, 2개로 변환되어야 해서 반환 타입이 Map
  default Map<String, Object> dtoToEntity(MovieDTO dto) {
    Map<String, Object> entityMap = new HashMap<>();

    Movie movie = Movie.builder()
        .mno(dto.getMno())
        .title(dto.getTitle())
        .build();

    entityMap.put("movie", movie);

    // MovieImageDTO의 List
    List<MovieImageDTO> imageDTOList = dto.getImageDTOList();
    System.out.println("imgDTOList: " + imageDTOList.toString());

    // MovieImageDTO의 List를 MovieImage Entity의 List로 변환
    if (imageDTOList != null && imageDTOList.size() > 0) {
      List<MovieImage> movieList = imageDTOList.stream().map(movieImageDTO -> {
        MovieImage image = MovieImage.builder()
            .path(movieImageDTO.getPath())
            .imgName(movieImageDTO.getImgName())
            .uuid(movieImageDTO.getUuid())
            .movie(movie)
            .build();


        return image;
      }).collect(Collectors.toList());

      entityMap.put("imgList", movieList);
    }

    return entityMap;
  }

  // 검색 결과를 DTO로 변환해주는 메서드
  default MovieDTO entitiesToDTO(Movie movie, List<MovieImage> imgList, double avg, Long reviewCnt) {
    MovieDTO dto = MovieDTO.builder()
            .mno(movie.getMno())
            .title(movie.getTitle())
            .regDate(movie.getRegDate())
            .modDate(movie.getModate())
            .build();

    List<MovieImageDTO> movieImageDTOList = imgList.stream().map(movieImage -> {
      return MovieImageDTO.builder()
              .imgName(movieImage.getImgName())
              .path(movieImage.getPath())
              .uuid(movieImage.getUuid())
              .build();
    }).collect(Collectors.toList());

    dto.setImageDTOList(movieImageDTOList);
    dto.setAvg(avg);
    dto.setReviewCnt(reviewCnt);

    return dto;
  }
}
