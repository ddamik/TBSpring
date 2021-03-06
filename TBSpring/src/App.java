import java.sql.SQLException;

import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.support.GenericXmlApplicationContext;

import springbook.user.dao.CountingConnectionMaker;
import springbook.user.dao.CountingDaoFactory;
import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class App {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// UserDaoTest();
		// UserDaoConnectionCountingTest();
		// UserDaoXmlApplicationContext();

		// 2장.
		// UpdateUserDaoTest();

		/**
		 * JUnit Test
		 */
		JUnitCore.main("test.UserDaoTest");
	}

	public static void UserDaoTest() throws ClassNotFoundException, SQLException {
		/*
		 * // NConnectionMaker를 만들었다면, 아래와 같이 만들면 된다. // ConnectionMaker
		 * connectionMaker = new NConnectionMaker(); ConnectionMaker
		 * connectionMaker = new DConnectionMaker(); // UserDao 가 사용할
		 * ConnectionMaker 구현 클래스를 결정하고 // 오브젝트를 만든다. UserDao dao = new
		 * UserDao(connectionMaker); // 1. UserDao 생성 // 2. 사용할 ConnectionMaker
		 * 타입의 오브젝트 제공. // 결국 두 오브젝트 사이의 의존관계 설정 효
		 */

		/**
		 * DaoFactory처럼 @Configuration이 붙은 자바 코드를 설정정보로 사용하려면
		 * AnnotationConfigApplicationContext를 사용한다. ApplicationContext의
		 * getBean()이라는 메소드를 이용해 UserDao의 오브젝트를 가져올 수 있다.
		 *
		 * getBean()의 파라미터인 "userDao"는 ApplicationContext에 등록된 빈의 이름이다.
		 * DaoFactory에서 @Bean이라는 애노테이션 userDao라는 메소드에 붙였기때문에 메소드가 바로 빈의 이름이 된다.
		 * 즉, DaoFactory의 userDao() 메소드를 호출해서 그 결과를 가져온다고 생각하면 된다.
		 */

		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("whiteship");
		user.setName("ddamik");
		user.setPassword("password");

		dao.add(user);
		System.out.println(user.getId() + " 등록 완료!");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
	}

	public static void UserDaoConnectionCountingTest() throws ClassNotFoundException, SQLException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);

		CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
		System.out.println("Connection counter : " + ccm.getCounter());
	}

	public static void UserDaoXmlApplicationContext() throws ClassNotFoundException, SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("whiteship");
		user.setName("ddamik");
		user.setPassword("password");

		dao.add(user);
		System.out.println(user.getId() + " 등록 완료!");

		User user2 = dao.get(user.getId());
		System.out.println(user2.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
	}

	public static void UpdateUserDaoTest() throws ClassNotFoundException, SQLException {
		ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user = new User();
		user.setId("whiteship");
		user.setName("ddamik");
		user.setPassword("password");

		dao.add(user);
		User user2 = dao.get(user.getId());

		if (!user.getName().equals(user2.getName()))
			System.out.println("테스트 실패 (name)");
		else if (!user.getPassword().equals(user2.getPassword()))
			System.out.println("테스트 실패 (password)");
		else
			System.out.println("조회 테스트 성공.");
	}
}
