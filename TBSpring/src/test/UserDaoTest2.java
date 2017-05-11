package test;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import springbook.user.dao.UserDao;

/**
 * 
 * @author ddamik
 *	@DirtiesContext는 스프링의 테스트 컨텍스트 프레임워크에게 해당 클래스의 테스트에서 애플리케이션 컨텍스트의 상태변경을 알려줌.
 *	테스트 컨텍스트는 DirtiesContext 애노테이션이 붙은 클래스는 애플리케이션 컨텍스트 공유를 허용하지 않는다.
 *	테스트 중에 변경한 컨텍스트가 뒤의 테스트에 영향을 주지 않게 하기 위해서다.
 */
//@DirtiesContext				
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/test-applicationContext.xml")
public class UserDaoTest2 {

		@Autowired
		UserDao dao;
		
		@Before
		public void setUp(){
			
			//	테스트에서 UserDao가 사용할 DataSource 오브젝트를 직접 생성한다.
			DataSource dataSource = new SingleConnectionDataSource(
					"jdbc:mysql://localhost/testdb", "root", "1", true);
			
			dao.setDataSource(dataSource);	//	코드에 의한 수동 DI
		}
}
