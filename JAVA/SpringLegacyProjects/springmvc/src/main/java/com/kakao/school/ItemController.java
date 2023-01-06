package com.kakao.school;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kakao.school.dto.ItemDTO;
import com.kakao.school.dto.Member;
import com.kakao.school.service.ItemService;
import com.kakao.school.validation.MemberValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
	
	private final ItemService itemService;

	// 로그를 기록하기 위한 객체
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public String getAllItem(Model model) {
		List<ItemDTO> list = itemService.allItem();
		
		model.addAttribute("list", list);
		
		return "itemHome";
	}
	
	@RequestMapping(value="detail/{code}", method = RequestMethod.GET)
	public String detailItem(Model model, @PathVariable("code") int code) {
		ItemDTO dto = itemService.getItem(code);
		
		model.addAttribute("item", dto);
		
		return "detailItem";
	}
	
	// item.xls 요청이 왔을 때 excel 이라는 뷰로 출력
	@RequestMapping(value="item.xls", method=RequestMethod.GET)
	public String excel(Model model) {
		
		List<ItemDTO> list = itemService.allItem();
		model.addAttribute("list", list);
		
		return "excel";
	}

	// item.pdf 요청이 왔을 때 excel 이라는 뷰로 출력
	@RequestMapping(value="item.pdf", method=RequestMethod.GET)
	public String pdf(Model model) {
		
		List<ItemDTO> list = itemService.allItem();
		model.addAttribute("list", list);
		
		return "pdf";
	}
	
	// json 요청 처리
	@RequestMapping(value="itemlist.json", method=RequestMethod.GET)
	public String jsonReport(Model model) {
		
		List<ItemDTO> list = itemService.allItem();
		
		model.addAttribute("list", list);
		
		return "itemlist";
	}
	
	@RequestMapping(value="exception", method=RequestMethod.GET)
	public String input(Model model) {
		return "input";
	}
	

	@RequestMapping(value="exception", method=RequestMethod.POST)
	public String input(Model model, @RequestParam("num1") int num1, @RequestParam("num2") int num2) {
		
		int result = num1 + num2;
		
		model.addAttribute("result", result);

		return "result";
	}
	
	// @ModelAttribute가 붙은 메서드는 리턴하는 데이터를 '모든 뷰'에게 전송
	@ModelAttribute("loginTypes")
	public List<String> loginTypes() {
		List<String> list = new ArrayList<>();
		
		list.add("일반 회원");
		list.add("기업 회원");
		list.add("비회원");
		
		return list;
	}
	
	// 예외 발생했을 때 처리하기 위한 메서드
	@ExceptionHandler(Exception.class)
	public String handleExcpetion(Model model, Exception e) {
		
		model.addAttribute("result", e.getLocalizedMessage());
		
		return "error/exception";
	}
	
	@RequestMapping(value="message", method=RequestMethod.GET)
	// 유효성 검사에 실패했을 때 이전 입력한 내용을 가지고 loginform으로 가도록 수정
	public String form(@ModelAttribute("member") Member member) {
		return "loginform";
	}

	@RequestMapping(value="message", method=RequestMethod.POST)
	public String form(@ModelAttribute("member") Member member, Member memberInfo, BindingResult result) {
		
		// 유효성 검사 수행
		new MemberValidator().validate(memberInfo, result);
		
		// 실패
		if (result.hasErrors()) {
			return "loginform";
		}
		else {
			return "created";
		}
		
	}

}
