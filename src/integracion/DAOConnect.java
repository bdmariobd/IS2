package integracion;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DAOConnect {
	private static DAOConnect instance;

	public static DAOConnect getInstance() {
		if(instance==null) {
			instance = new DAOConnectIMP();
		}
		
		return instance;
	}

	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
