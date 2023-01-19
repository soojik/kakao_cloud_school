package com.soo.security.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class SampleController {

  @GetMapping("/")
  public String index() {
    log.info("메인 페이지");
    return "/index";
  }

  @GetMapping("/sample/all")
  public void all() {
    log.info("모두 허용");
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/sample/member")
  public void member() {
    log.info("등록자(member) 허용");
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/sample/admin")
  public void admin() {
    log.info("관리자 허용");
  }

}
