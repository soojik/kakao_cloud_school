package com.kakao.school;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kakao.school.dto.ItemDTO;
import com.kakao.school.service.ItemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
	
	private final ItemService itemService;

	// 로그를 기록하기 위한 객체
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "", method=RequestMethod.GET)
	public String home(Model model) {
		return "itemHome";
	}
	
	@RequestMapping(value="all", method = RequestMethod.GET)
	public String getAllItem(Model model) {
		List<ItemDTO> list = itemService.allItem();
		
		model.addAttribute("list", list);
		
		return "allItem";
	}

}
