package integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DAOConnectIMP extends DAOConnect {

	
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		 
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection ("jdbc:mysql://remotemysql.com:3306/RnYI9FA4l5","RnYI9FA4l5","oWz7GlPKh5");
		System.out.println("Connection successful");
		return conn;
		       
	
	}
	

}
