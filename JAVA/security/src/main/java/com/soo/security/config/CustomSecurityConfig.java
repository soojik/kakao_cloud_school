package com.soo.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Log4j2
@RequiredArgsConstructor
public class CustomSecurityConfig {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    log.info("필터 환경 설정");

    // 인증이나 인가에 문제가 발생하면 로그인 폼 출력
    http.formLogin().loginPage("/member/login");
    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {

    // css나 JS 사용 시 테스트 가능
    return (web -> web.ignoring()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()));
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
