
import org.springframework.context.support.GenericXmlApplicationContext;

import domain.Item;

public class Main {

	public static void main(String[] args) {
//		ItemRepository itemReposiroty = new ItemRepository();
		
		// 인스턴스 생성을 다른 팩토리 클래스를 이용해서 생성
		// 다른 클래스의 메서드를 이용해서 인스턴스를 생성하는 것을 Factory Method Pattern이라고 한다.
		
		/* 스프링이 인스턴스를 생성하도록
		ApplicationContext context = new AnnotationConfigApplicationContext(RepositoryFactory.class);
		*/
		
		try(GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml")) {
			/*
			ItemRepository itemRepository = context.getBean("itemRepository", ItemRepository.class);
			
			Item item = itemRepository.get();
			*/
			
			Item item = context.getBean("item", Item.class);
			System.out.println(item);
		} catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		// RepositoryFactory 클래스의 create 메서드 호출해서 ItemRepository 클래스의 인스턴스 생성
		
//		ItemRepository itemRepository = RepositoryFactory.create();
//		ItemRepository itemRepository1 = RepositoryFactory.create();
		
//		System.out.println(System.identityHashCode(itemRepository1) == System.identityHashCode(itemRepository));
		


	}

}
