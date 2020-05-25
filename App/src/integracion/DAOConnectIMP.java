package integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DAOConnectIMP extends DAOConnect {

	final static private String normalUsr = "RnYI9FA4l5", normalPass="BMHT16uWC5";
	//final static private String testUsr = "RGnXxNJvw7", testPass="9l8DJlBkCF";
	private String usr = normalUsr,pass=normalPass;
	private Connection con;
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		if(con!=null) return con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection ("jdbc:mysql://remotemysql.com:3306/"+usr+"?autoReconnect=true&useSSL=false",usr,pass);
		//System.out.println("Connection successful");
		return con;
	}

}
