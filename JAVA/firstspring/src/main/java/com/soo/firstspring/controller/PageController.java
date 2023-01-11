package com.soo.firstspring.controller;

import com.soo.firstspring.domain.SampleVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class PageController {

    private final Logger LOGGER = LoggerFactory.getLogger(PageController.class);

    @GetMapping("/")
    public String main(Model model) {
        Map<String, Object> map = new HashMap<>();

        map.put("language", "Java");
        map.put("buildtool", "Gradle");
        map.put("ide", "Intelli J");

        model.addAttribute("map", map);

        List<String> list = new ArrayList<>();

        list.add("Developer");
        list.add("Engineer");

        model.addAttribute("list", list);

        return "main";
    }

    @GetMapping("/get01")
    // 리턴 타입이 void이면 출력하는 뷰 이름은 요청 URL
    // view의 이름은 get01
    public void get01() {
        LOGGER.info("get01");
    }

    @GetMapping({"/exlink", "/exformat"})
    public void get02(Model model) {

        // 1부터 20까지의 숫자를 SampleVO 형식의 객체로 바꾼다.
        List<SampleVO> list = IntStream.range(1, 20).asLongStream().mapToObj(i -> {
            SampleVO vo = SampleVO.builder()
                    .sno(i)
                    .first("First ..")
                    .last("Last ..")
                    .regTime(LocalDateTime.now())
                    .build();

            return vo;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);
    }
}
