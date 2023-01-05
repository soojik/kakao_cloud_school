package di.service;

import di.entity.MemberEntity;

import org.springframework.stereotype.Service;

import di.dto.MemberDTO;
import di.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

//@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	// service는 Repository를 주입받아서 사용
	// @Autowired
	private final MemberRepository memberRepository;

	@Override
	public MemberDTO findById(String id) {
		// Repository에 필요한 매개변수 생성
		// Repository 메서드 호출
		MemberEntity memberEntity = memberRepository.findById(id);
		
		// Controller에게 전달할 데이터 생성
		MemberDTO memberDTO = MemberDTO.builder()
				.id(memberEntity.getId())
				.password(memberEntity.getPassword())
				.nickname(memberEntity.getNickname())
				.build();
		
		return memberDTO;
	}

}
