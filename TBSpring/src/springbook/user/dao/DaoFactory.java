package springbook.user.dao;

/**
 *	팩토리의 메소드는 UserDao 타입의 오브젝트를 어떻게 만들고,
 *	어떻게 준비시킬지 결정한다.
 */


public class DaoFactory {

	public UserDao userDao(){
		return new UserDao(this.connectionMaker());
	}
	
	/**
	 *	기존에 new DConnectionMaker() 부분이 중복된 부분을 공통의 메소드로 추출하여 중복을 최소화 하였고,
	 *	이제는 메소드 수의 상관없이 유연해졌다.
	 */
	public AccountDao accountDao(){
		return new AccountDao(this.connectionMaker());
	}
	
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
