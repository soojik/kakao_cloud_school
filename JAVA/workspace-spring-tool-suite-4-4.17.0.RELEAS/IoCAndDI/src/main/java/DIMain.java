import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import di.controller.MemberController;
import di.persistence.GoodMapper;
import di.persistence.GoodRepository;
import di.persistence.TransactionRepository;
import domain.Good;

public class DIMain {

	public static void main(String[] args) {

		try(GenericXmlApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml")) {
			/* controller 가져오기
			MemberController controller = context.getBean("memberController", MemberController.class);
			controller.findById("1");
			 */
			/* sql 연결 확인
			SqlSessionFactory sqlFactory = context.getBean("sqlSessionFactory", SqlSessionFactory.class);
			System.out.println(sqlFactory);
			SqlSession session = sqlFactory.openSession();
			System.out.println(session);
			*/
			
			/* good repository
			GoodRepository repository = context.getBean(GoodRepository.class);
			*/

			/*
			Good good = Good.builder()
					.code(1)
					.name("사과")
					.manufacture("안동")
					.price(1500)
					.build();
			
			repository.insertGood(good);
			*/
			
			/*
			GoodMapper repository = context.getBean(GoodMapper.class);
			
			List<Good> list = repository.allGood();
			for (Good good : list) {
				System.out.println(good);
			}
			*/

			TransactionRepository repository = context.getBean(TransactionRepository.class);
			repository.insert();

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

	}

}
