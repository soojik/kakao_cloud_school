package com.soo.firstspring.controller;

import com.soo.firstspring.dto.ParamDTO;
import org.apache.coyote.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
// 공통된 URL 설정
@RequestMapping("/api/v1/rest-api")
public class JSONController {

    private final Logger LOGGER = LoggerFactory.getLogger(JSONController.class);

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String getHello() {
        LOGGER.info("hello");
        return "GET Hello";
    }

    @GetMapping("/hello-new")
    public String getNewHello() {
        return "GET New Hello";
    }

    @GetMapping("/product/{code}")
    public String getCode(@PathVariable("code") int code) {
        return "Code is " + code;
    }

    @GetMapping("/param")
    public String getParam(HttpServletRequest request) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String organization = request.getParameter("organization");

        return name + " : " + email + " : " + organization;
    }

    @GetMapping("/param-req")
    public String getParam(@RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("organization") String organization) {

        return name + " : " + email + " : " + organization;
    }

    @GetMapping("/param-dto")
    public String getParam(ParamDTO dto) {
        return dto.getName() + " : " + dto.getEmail() + " : " + dto.getOrganization();
    }

    @PostMapping("/param-post")
    // @RequestBody가 없어도 처리는 되지만 파라미터가 아닌 body의 내용을 가져온다고 명시
    public String getParma(@RequestBody ParamDTO dto) {
        return dto.toString();
    }

    @PutMapping("/param-put")
    public String getPutParam(@RequestBody ParamDTO dto) {
        return dto.toString();
    }

    @PutMapping("/param-put-dto")
    public ParamDTO getPutParamDTO(@RequestBody ParamDTO dto) {
        return dto;
    }

    @PutMapping("/param-put-res")
    public ResponseEntity<ParamDTO> getPutParamDTOwithResEntity(@RequestBody ParamDTO dto) {

        // 상태 코드를 설정해서 결과 리턴하는 것이 가능
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @DeleteMapping("/product/{code}")
    public String DeleteProduct(@PathVariable("code") int code) {
        return code + "";
    }

    @DeleteMapping("/product")
    public String DeleteProductParam(@RequestParam("code") int code) {
        return code + "";
    }
}
