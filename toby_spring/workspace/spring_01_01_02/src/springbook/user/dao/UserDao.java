package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.domain.User;

// list1-2. jdbc를 이용한 등록과 조회 기능이 있는 UserDao클래스
// cf) DAO(Data Access Object)는 DB를 사용해 데이터를 조회하거나 조작하는 기능을 전담하도록 만든 오브젝트를 말한다. 
public class UserDao {
	
	//새로운 사용자를 생성한다.
	public void add(User user) throws ClassNotFoundException, SQLException{
		
		//jdbc드라이버 클래스 로딩
		Class.forName("com.mysql.jdbc.Driver"); 
		
		//DB연결을 위한 Connection을 가져온다.
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook","spring","book");
		
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
		//jdbc드라이버 클래스 로딩
		Class.forName("com.mysql.jdbc.Driver"); 
		
		//DB연결을 위한 Connection을 가져온다.
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook","spring","book");
		
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
}
