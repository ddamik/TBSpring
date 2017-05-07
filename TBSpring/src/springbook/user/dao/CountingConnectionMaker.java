package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * @author ddamik
 *
 *	CountingConnectionMaker 클래스는 ConnectionMaker 인터페이스를 구현했지만, 직접 DB 커넥션을 만들지 않는다.
 *	대신 DAO가 DB 커넥션을 가져올 때마다 호출하는 makeConnection()에서 DB 연결횟수 카운터를 증가시킨다.
 *	
 *	DB 연결횟수 카운팅 작업을 마치면 실제 DB 커넥션을 만들어주는 realConnectionMaker에 저장된 ConnectionMaker 타입 오브젝트의 makeConnection()을	
 *	호출해서 그 결과를 DAO에게 돌려준다.
 *
 *	UserDao는 ConnectionMaker 인터페이스에만 의존하고 있기 때문에, ConnectionMaker 인터페이스를 구현하고 있다면 어떤 것이든 DI가 가능하다.
 *	UserDao 오브젝트가 DI 받는 대상의 설정을 조정해서 DConnection 오브젝트 대신 CountingConnectionMaker 오브젝트로 바꿔치기하는 것이다.
 *	이렇게하면 UserDao가 DB 커넥션을 가져오라고 할때마다 CountingConnectionMaker의 makeConnection() 메소드가 실행되고 카운터는 하나씩 증가한다.
 *
 *	CountingConnectionMaker가 다시 실제 사용할 DB커넥션을 제공해주는 DConnectionMaker를 호출하도록 만들어야 한다.

 */
public class CountingConnectionMaker implements ConnectionMaker{

	int counter = 0;
	private ConnectionMaker realConnectionMaker;
	
	public CountingConnectionMaker(ConnectionMaker realConnectionMaker){
		this.realConnectionMaker = realConnectionMaker;
	}
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		this.counter++;
		return realConnectionMaker.makeConnection();
	}
	
	public int getCounter(){
		return this.counter;
	}
}
