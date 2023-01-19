package com.soo.film.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @GetMapping("/")
  public String home() {
    return "redirect:/movie/list";
  }

  @GetMapping("/user")
  public String user() {
    return "생략";
  }
}
