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
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Error DB o tablename");
		}
	}
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
	public static int getRandomDNI()	{
		int x = (int) Math.floor(10000000 + Math.random() * 90000000);

		System.out.println(x);
		
    return x;
	}
    public static int getRandomTelf()	{
		int x = (int) Math.floor(100000000 + Math.random() * 900000000);
		System.out.println(x);
		
    return x;
	}	
	
}
