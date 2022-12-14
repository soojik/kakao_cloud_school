package di.controller;

import org.springframework.stereotype.Controller;

import di.service.MemberService;
import lombok.RequiredArgsConstructor;

// Servlet 클래스의 인스턴스로 생성
//@Controller
@RequiredArgsConstructor
// Controller는 다른 클래스에 주입되지 않기 때문에 템플릿 메서드 패턴(인터페이스 생성 후, 구현하는 클래스 생성)을 사용하지 않는다.
public class MemberController {

	// controller는 service를 주입받는다.
	private final MemberService memberService;

	// 원래 view 이름을 반환한다.
	public void findById(String id) {
		System.out.println(memberService.findById(id));
	}

}
