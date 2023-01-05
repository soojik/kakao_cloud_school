package persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import persistence.ItemRepository;;

// 인스턴스 생성해주는 Factory 클래스
// Factory 클래스라고 명시해주는 어노테이션
@Configuration
public class RepositoryFactory {
	
	// create 대신 newInstance 사용해도 같은 의미
	// 매번 인스턴스를 생성해서 제공
	// 인스턴스를 만들어주는 메서드
	@Bean
	public static ItemRepository create() {
		return new ItemRepository();
	}

}
