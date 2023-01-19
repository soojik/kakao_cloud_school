package com.soo.film.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {

  @Before(value = "execution(* com.soo.film.service.EmployeeService.*(..)) && args(empid, fname, sname)")
  public void beforeAdvice() {

    System.out.println("메서드 호출하기 전에 호출");
  }

  @Before(value = "execution(* com.soo.film.service.EmployeeService.*(..)) && args(empid, fname, sname)")
  public void afterAdvice() {

    System.out.println("메서드 호출해서 수행한 후에 호출");
  }
}
