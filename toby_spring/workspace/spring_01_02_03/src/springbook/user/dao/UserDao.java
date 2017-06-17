package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.domain.User;

// list1-2. jdbc를 이용한 등록과 조회 기능이 있는 UserDao클래스
// cf) DAO(Data Access Object)는 DB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 오브젝트를 말한다. 
public abstract class UserDao {
	                                                        
	//추상메소드로 변경. 메소드의 구현은 서브클래스가 담당.
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
	
	
	//새로운 사용자를 생성한다.
	public void add(User user) throws ClassNotFoundException, SQLException{
		
		Connection c = getConnection();
		
		//SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
		PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) "
				+ "values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		//만들어진 Statement를 실행한다.
		ps.executeUpdate();
		
		//사용한 자원을 반환.
		ps.close();
		c.close();
		
	}
	
	//id를 가지고 사용자 정보를 읽어온다.
	public User get(String id) throws ClassNotFoundException, SQLException{
		
		Connection c = getConnection();
		
		//SQL을 담은 Statement(또는 PreparedStatement)를 만든다.
		PreparedStatement ps = c.prepareStatement(
				"select * from users where id=?");
		ps.setString(1, id);
		
		//SQL쿼리의 실행 결과를 ResultSet으로 받아서 정보를 저장할 오브젝트(User)에 옮겨준다. 
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		//사용한 자원을 반환.
		rs.close();
		ps.close();
		c.close();
		
		return user;
		
	}
	
	// 테스트용 main() 메소드
	/*public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao();
		
		User user = new User();
		user.setId("whiteship");
		user.setName("백기선");
		user.setPassword("married");
		
		dao.add(user);
		
		System.out.println(user.getId() + "등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId() + "조회 성공");
	}*/
 
}
