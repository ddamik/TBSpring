package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *	팩토리의 메소드는 UserDao 타입의 오브젝트를 어떻게 만들고,
 *	어떻게 준비시킬지 결정한다.
 *	
 *	@Configuration: 스프링이 빈 팩토리를 위한 오브젝트 설정을 담당하는 클래스라고 인식할 수 있다.
 *	@Bean: 오브젝트를 만들어주는 메소드에 붙인다.
 */

@Configuration
public class DaoFactory {

	@Bean
	public UserDao userDao(){
//		return new UserDao(this.connectionMaker());
		UserDao userDao = new UserDao();
		userDao.setConnectionMaker(connectionMaker());
		return userDao;
	}
	
	/**
	 *	기존에 new DConnectionMaker() 부분이 중복된 부분을 공통의 메소드로 추출하여 중복을 최소화 하였고,
	 *	이제는 메소드 수의 상관없이 유연해졌다.
	 */
	@Bean
	public AccountDao accountDao(){
		return new AccountDao(this.connectionMaker());
	}
	
	@Bean
	public MessageDao messageDao(){
		return new MessageDao(this.connectionMaker());
	}

	/**
	 *	ConnecionMaker의 구현 클래스를 결정하고 오브젝트를 만드는 코드를 별도의 메소드로 뽑아내야 한다.
	 */
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}
}
