package com.soo.film.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Employee {

  private String empId;
  private String firstName;
  private String secondName;
}
