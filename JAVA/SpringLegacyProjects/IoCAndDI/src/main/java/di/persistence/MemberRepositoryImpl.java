package di.persistence;

import org.springframework.stereotype.Repository;

import di.entity.MemberEntity;

@Repository
public class MemberRepositoryImpl implements MemberRepository {
	
	/* 원래는 생성자 사용
	 * new Member("김지수", "1234", "soo")
	 * 매개변수의 이름을 알 수 없어서 가독성이 떨어진다.
	 */

	@Override
	public MemberEntity findById(String id) {

		MemberEntity memberEntity = MemberEntity.builder()
				.id("김지수")
				.password("1234")
				.nickname("soo")
				.build();
		
		return memberEntity;
	}

}
