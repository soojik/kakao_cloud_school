import java.sql.Connection;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DBTest {
	
	@Autowired
	private DataSource dataSource;
	
	// @Test 어노테이션이 붙은 메서드가 테스트 메서드
	@Test
	public void 연결테스트() {
		try {
			System.out.println(dataSource);
			// 연결
			Connection con = dataSource.getConnection();
			System.out.println(con);
			con.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}

}
