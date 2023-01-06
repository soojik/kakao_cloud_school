package com.kakao.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kakao.school.dto.ItemDTO;
import com.kakao.school.service.ItemService;

@RestController
@RequestMapping("/item")
public class JSONController {
	
	@Autowired
	private ItemService itemService;
	
	//csv 리턴하는 메서드
	@RequestMapping(value="item.csv", method=RequestMethod.GET)
	public String csv() {
		
		return "csv,xml,json";
	}
	
	//csv 리턴하는 메서드
	@RequestMapping(value="itemlistrest.json", method=RequestMethod.GET)
	public List<ItemDTO> json() {
			
		return itemService.allItem();
	}

}
