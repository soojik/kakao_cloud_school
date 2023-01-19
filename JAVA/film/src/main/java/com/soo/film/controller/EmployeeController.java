package com.soo.film.controller;

import com.soo.film.domain.Employee;
import com.soo.film.service.EmployeeService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.charset.Charset;

@Log4j2
@Controller
@RequiredArgsConstructor
public class EmployeeController {

  private final EmployeeService employeeService;

  @GetMapping("/add/employee")
  public ResponseEntity<Employee> addEmployee(@RequestParam("empId") String empId,
                                              @RequestParam("firstName") String firstName,
                                              @RequestParam("secondName") String secondName) {

    Employee employee = employeeService.createEmployee(empId, firstName, secondName);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

    return new ResponseEntity<>(employee, headers, HttpStatus.OK);
  }
}
