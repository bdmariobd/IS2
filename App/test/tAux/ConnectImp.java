package tAux;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectImp extends Connect {

	private Connection con;
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if(con!=null) return con;
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection ("jdbc:mysql://remotemysql.com:3306/RnYI9FA4l5?autoReconnect=true&useSSL=false","RnYI9FA4l5","BMHT16uWC5");
		return con;
	}

}
