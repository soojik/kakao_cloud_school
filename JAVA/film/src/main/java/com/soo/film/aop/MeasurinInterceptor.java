package com.soo.film.aop;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Log4j2
public class MeasurinInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) {

    log.warn("Controller가 요청을 처리하기 전에 호출");

    return true;
  }

  // Controller가 요청을 정상적으로 처리한 후 호출되는 메서드
  @Override
  public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView modelAndView) {

    log.warn("Controller가 요청을 정상적으로 처리한 후 호출");
  }

  // Controller가 요청을 처리한 후 무조건 호출되는 메서드
  @Override
  public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e) {

    log.warn("Controller가 요청을 처리한 후 무조건 호출");
  }


}
