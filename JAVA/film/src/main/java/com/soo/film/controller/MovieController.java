package com.soo.film.controller;

import com.soo.film.dto.MovieDTO;
import com.soo.film.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

  private final MovieService movieService;

  @GetMapping("/register")
  public void register() {
  }

  @PostMapping("/register")
  public String register(MovieDTO dto, RedirectAttributes rattr) {
    log.info("movieDTO: " + dto);
    Long mno = movieService.register(dto);
    rattr.addFlashAttribute("msg", mno + " 삽입 성공");
    return "redirect:/movie/list";
  }

  @GetMapping("/list")
  public void list() {}
}
