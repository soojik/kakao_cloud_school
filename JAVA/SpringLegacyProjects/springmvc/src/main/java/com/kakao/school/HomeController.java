package com.kakao.school;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kakao.school.domain.Command;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	// 로그를 기록하기 위한 객체
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// 요청이 GET 방식으로 왔을 때 처리하는 메서드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		// View에게 데이터 전달
		// serverTime이라는 이름으로 foramttedDate라는 데이터를 전달
		model.addAttribute("serverTime", formattedDate );

		// 리턴하는 문자열이 View 이름
		// servlet-context.xml 파일에서 View Resolver를 추가해서 실제 출력할 View가 결졍된다.
		return "home";
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(Model model) {

		model.addAttribute("message", "Hello!");

		return "hello";
	}
	
	@RequestMapping(value = "/message/detail/{num}", method = RequestMethod.GET)
	public String detail(Model model, @PathVariable("num") int num) {
		
		model.addAttribute("num", num);
		
		return "detail";
	}
	
	@RequestMapping(value = "param1", method=RequestMethod.GET)
	public String param1(Model model, HttpServletRequest req) {
		// 전통적인 자바 웹 프로그래밍에서의 파라미터 읽기
		String name = req.getParameter("name");
		String passwd = req.getParameter("passwd");
		
		logger.info("name : " + name);
		logger.info("passwd : " + passwd);
		
		return "";
	}
	
	@RequestMapping(value="param2", method=RequestMethod.POST)
	public String param2(Model model, @RequestParam("name") String name, @RequestParam("passwd") String passwd) {
		
		logger.info("name : " + name);
		logger.info("passwd : " + passwd);
		
		return "";	
	}
	
	@RequestMapping(value = "param3", method = RequestMethod.POST)
	public String param3(Model model, Command command) {
		logger.info(command.toString());
		return "";
	}
	
	@RequestMapping(value = "redirect", method = RequestMethod.GET)
	public String redirect(Model model, RedirectAttributes rattr) {
		
		// 데이터 생성 - request.setAttribute("msg", "메세지") 와 동일
		// redirect로 이동하게 되면 request의 데이터는 전달되지 않는다.
		// request가 새로 만들어지기 때문이다.
		// 이런 경우에는 session이나 RedirectAttributes를 이용해야 한다.
		model.addAttribute("msg", "hihi~");
		
		// 브라우저를 종료하거나 세션에서 제거하지 않는 이상 계속 유지
		// session.setAttribute("msg", "세션 이용한 데이터 전달");
		
		rattr.addFlashAttribute("msg", "일회용(flash) redirect attribute 전달");
		
		return "redirect:disp";
	}

	@RequestMapping(value = "disp", method = RequestMethod.GET)
	// (1) @RequestParam("msg") String msg 추가
	public String disp(Model model) {
		
		// redirect 보내는 쪽에서 따로 전달하지 않으면 model.attribute로 넘어온 데이터 그대로 받아서 사용
		// (2) model.addAttribute("msg", msg);
		
		return "redirect";
	}
}
