package com.kakao.school.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.kakao.school.dto.Member;

public class MemberValidator implements Validator {

	// 유효성 검사 수행할 DTO 설정
	@Override
	public boolean supports(Class<?> clazz) {

		return Member.class.isAssignableFrom(clazz);
	}

	// 유효성 검사 수행할 메서드
	@Override
	public void validate(Object target, Errors errors) {
		Member member = (Member) target;

		if (member.getEmail() == null || member.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		}

		if (member.getPasswd() == null || member.getPasswd().trim().isEmpty()) {
			errors.rejectValue("passwd", "required");
		}

	}

}
