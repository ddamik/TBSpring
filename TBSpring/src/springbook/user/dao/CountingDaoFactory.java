package springbook.user.dao;

import java.sql.Connection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 
 * @author ddamik
 *	UserDaoTest에선 UserDao를 호출.
 *	UserDao가 원하는 것은 connectionMaker를 원한다.
 *	connectionMaker를 호출할때 counting메소드를 중간에 넣어줘야 하며, counting메소드에 DConnection을 DI를 해야한다. 
 */
@Configuration
public class CountingDaoFactory {

	@Bean
	public UserDao userDao(){
//		return new UserDao(connectionMaker());
		UserDao userDao = new UserDao();
		userDao.setConnectionMaker(connectionMaker());
		return userDao;
	}
	
	
	/**
	 * @return
	 * 	UserDao에서는 connectionMaker를 사용한다.
	 */
	@Bean
	public ConnectionMaker connectionMaker(){
		return new CountingConnectionMaker(realConnectionMaker());
	}
	
	
	/**
	 * @return
	 *	실질적인 DB커넥션인 DConnectionMaker를 생성하여	
	 *	CountingConnectionMaker로 넘겨준다.
	 *	그렇다면 UserDao에서는 기존과 동일하게 connectionMaker를 호출하지만, 실질적으로는 CountingConnectionMaker로 부터 DConnectionMaker를 받는다.
	 */
	@Bean
	public ConnectionMaker realConnectionMaker(){
		return new DConnectionMaker();
	}
}
