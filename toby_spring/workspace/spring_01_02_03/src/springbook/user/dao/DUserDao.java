package springbook.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DUserDao extends UserDao{

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		// D�� DB connection �����ڵ�
		return null;
	}

}
