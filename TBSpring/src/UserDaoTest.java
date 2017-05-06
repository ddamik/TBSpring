import java.sql.SQLException;

import springbook.user.dao.DaoFactory;
import springbook.user.dao.UserDao;
import springbook.user.domain.User;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

		/*
		//	NConnectionMaker를 만들었다면, 아래와 같이 만들면 된다.
		//	ConnectionMaker connectionMaker = new NConnectionMaker();
		ConnectionMaker connectionMaker = new DConnectionMaker();	//	UserDao	가 사용할 ConnectionMaker 구현 클래스를 결정하고
																	//	오브젝트를 만든다.
		UserDao dao = new UserDao(connectionMaker);		//	1. UserDao	생성
														//	2. 사용할 ConnectionMaker 타입의 오브젝트 제공.
														//	결국 두 오브젝트 사이의 의존관계 설정 효
		 */
		
		UserDao dao = new DaoFactory().userDao();
		User user = new User();
		user.setId("whiteship");
		user.setName("ddmaik");
		user.setPassword("password");
		
		dao.add(user);
		System.out.println(user.getId() + " 등록 성공!");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
	}

}
