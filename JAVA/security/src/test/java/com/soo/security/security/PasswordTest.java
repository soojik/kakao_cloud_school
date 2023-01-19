package com.soo.security.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTest {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Test
  public void testEncode() {
    String password = "1111";
    String enPw = passwordEncoder.encode(password);

    System.out.println("origin password: " + password);
    System.out.println("First Encoded password: " + enPw);

    boolean result = passwordEncoder.matches(password, enPw);
    System.out.println(result);

    enPw = passwordEncoder.encode(password);
    System.out.println("Second Encoded password: " + enPw);

    result = passwordEncoder.matches(password, enPw);
    System.out.println(result);
  }

}
