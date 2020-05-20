package tAux;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Connect {
	private static Connect instancia;
	
	public static Connect getInstance() {
		if(instancia==null) instancia = new ConnectImp();
		return instancia;
	}
	
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
