package tAux;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static void deleteTable(String tablename) throws SQLException, ClassNotFoundException {
		try{
			Connection con = ConnectImp.getInstance().getConnection();
			Statement st = con.createStatement();
			String query = "Delete FROM "+ tablename+" ;";
			st.executeUpdate(query);
			System.out.println("-Tabla "+tablename+" borrada correctamente");
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error DB o tablename");
		}
	} // esto no va pq soy down y no tengo permisos en el servidor
	public static void exportTable(String tablename) {
	    Statement stmt;
	    String query;
	    String filename = tablename+"_file";
	    try {
	    	Connection con = ConnectImp.getInstance().getConnection();
	        stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
	                ResultSet.CONCUR_UPDATABLE);

	        //For comma separated file
	        query = "SELECT * into OUTFILE  '"+filename+
	                "' FIELDS TERMINATED BY ',' FROM " + tablename;
	        stmt.executeQuery(query);

	    } catch(Exception e) {
	        e.printStackTrace();
	        stmt = null;
	    }
	}
	public static void addSomething(String query) {
		try {
			Connection con=ConnectImp.getInstance().getConnection();
			Statement st=con.createStatement();
			//st.executeQuery("SET FOREIGN_KEY_CHECKS=0");
			st.executeUpdate(query);
			//st.executeQuery("SET FOREIGN_KEY_CHECKS=1");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error de conexion con la base de dato o de consulta");
			e.printStackTrace();
		}
		
	}
	
}
