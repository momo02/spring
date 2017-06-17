package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import springbook.user.domain.User;

// list1-2. jdbc�� �̿��� ��ϰ� ��ȸ ����� �ִ� UserDaoŬ����
// cf) DAO(Data Access Object)�� DB�� ����� �����͸� ��ȸ�ϰų� �����ϴ� ����� �����ϵ��� ���� ������Ʈ�� ���Ѵ�. 
public class UserDao {
	
	//���ο� ����ڸ� �����Ѵ�.
	public void add(User user) throws ClassNotFoundException, SQLException{
		
		//jdbc����̹� Ŭ���� �ε�
		Class.forName("com.mysql.jdbc.Driver"); 
		
		//DB������ ���� Connection�� �����´�.
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook","spring","book");
		
		//SQL�� ���� Statement(�Ǵ� PreparedStatement)�� �����.
		PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) "
				+ "values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		//������� Statement�� �����Ѵ�.
		ps.executeUpdate();
		
		//����� �ڿ��� ��ȯ.
		ps.close();
		c.close();
		
	}
	
	//id�� ������ ����� ������ �о�´�.
	public User get(String id) throws ClassNotFoundException, SQLException{
		//jdbc����̹� Ŭ���� �ε�
		Class.forName("com.mysql.jdbc.Driver"); 
		
		//DB������ ���� Connection�� �����´�.
		Connection c = DriverManager.getConnection(
				"jdbc:mysql://localhost/springbook","spring","book");
		
		//SQL�� ���� Statement(�Ǵ� PreparedStatement)�� �����.
		PreparedStatement ps = c.prepareStatement(
				"select * from users where id=?");
		ps.setString(1, id);
		
		//SQL������ ���� ����� ResultSet���� �޾Ƽ� ������ ������ ������Ʈ(User)�� �Ű��ش�. 
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		//����� �ڿ��� ��ȯ.
		rs.close();
		ps.close();
		c.close();
		
		return user;
		
	}
}
