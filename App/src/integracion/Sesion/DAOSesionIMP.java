package integracion.Sesion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import integracion.DAOConnect;
import negocio.Profesor.TProfesor;
import negocio.Sesion.TSesion;

public class DAOSesionIMP implements DAOSesion{
	
	private int getID() {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String s= "SELECT IFNULL(MAX(id),0) as maximoo FROM Sesion;";
			ResultSet resultSet = statement.executeQuery(s);
			if(resultSet.next()) {
				int id = resultSet.getInt("maximoo");
				return id+1;
			}
		}
		catch (Exception e) {	
			return -4;
		}
		return -5;
	}

	@Override
	public int create(TSesion a) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			int id = getID();
			if(id<1) return id;
			
			String insertstm = "INSERT into Sesion VALUES ("+id+","+a.getFecha()+","+a.getHoraini().getTime()+","+a.getHorafin().getTime()+",'"+a.getTipo()+"',"+a.isActivo()+", "+a.getIdAlumno()+", "+a.getIdProfesor()+");";
			
			int resultSet = statement.executeUpdate(insertstm);
		}
		catch (Exception e) {	
			e.printStackTrace();
		}
		return 1;
	}

	
	@Override
	public int findByID(String id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Sesion WHERE id="+id+";";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) return 1;
			else return -1;
		}
		catch (Exception e) {
			return -4;
		}
	}
	@Override
	public List<TSesion> ocupadoProfesor(int id, Date fecha) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Sesion;";
			List<TSesion> list = new ArrayList<TSesion>();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				if(id==resultSet.getInt("idProfesor")&& fecha==resultSet.getDate("fecha"))
				list.add(new TSesion(resultSet.getInt("id"),resultSet.getDate("fecha"), 
						resultSet.getDate("horaini"), resultSet.getDate("horafin"),
						resultSet.getString("tipo"), resultSet.getBoolean("activo"),resultSet.getInt("idAlumno"),resultSet.getInt("idProfesor")));}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<TSesion> ocupadoAlumno(int id, Date fecha) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Sesion;";
			List<TSesion> list = new ArrayList<TSesion>();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				if(id==resultSet.getInt("idAlumno")&& fecha==resultSet.getDate("fecha"))
				list.add(new TSesion(resultSet.getInt("id"),resultSet.getDate("fecha"), 
						resultSet.getDate("horaini"), resultSet.getDate("horafin"),
						resultSet.getString("tipo"), resultSet.getBoolean("activo"),resultSet.getInt("idAlumno"),resultSet.getInt("idProfesor")));}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<TSesion> readAllProfesor(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Sesion;";
			List<TSesion> list = new ArrayList<TSesion>();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				if(id==resultSet.getInt("idProfesor"))
				list.add(new TSesion(resultSet.getInt("id"),resultSet.getDate("fecha"), 
						resultSet.getDate("horaini"), resultSet.getDate("horafin"),
						resultSet.getString("tipo"), resultSet.getBoolean("activo"),resultSet.getInt("idAlumno"),resultSet.getInt("idProfesor")));}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public List<TSesion> readAllAlumno(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Sesion;";
			List<TSesion> list = new ArrayList<TSesion>();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				if(id==resultSet.getInt("idProfesor"))
				list.add(new TSesion(resultSet.getInt("id"),resultSet.getDate("fecha"), 
						resultSet.getDate("horaini"), resultSet.getDate("horafin"),
						resultSet.getString("tipo"), resultSet.getBoolean("activo"),resultSet.getInt("idAlumno"),resultSet.getInt("idProfesor")));}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(TSesion a) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "UPDATE Sesion SET fecha='"+a.getFecha()+"',horaini='"+a.getHoraini()+"',horafin='"+a.getHorafin()+"',tipo='"+a.getTipo()+",Activo="+a.isActivo()+"',idAlumno='"+a.getIdAlumno()+"',idProfesor='"+a.getIdProfesor()+" WHERE id="+a.getId()+";";
			int resultSet = statement.executeUpdate(query);
			if(resultSet==0) return -1;
			return a.getId();
		}
		catch (Exception e) {
			e.printStackTrace();
			return -4;
		}
			
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String deletestm = "UPDATE Sesion SET activo="+0+" WHERE id="+id+";";
			int resultSet = statement.executeUpdate(deletestm);
			if(resultSet==0) return -1;
			return id;
		}
		catch (Exception e) {	
			return -4;
		}
	}

	@Override
	public int isDeleted(int id) {
		// TODO Auto-generated method stub
	
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Sesion WHERE id="+id+";";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				return resultSet.getBoolean("activo")? 0:-6;
			}
			return -1;
		}
		catch (Exception e) {
			return -4;
		}
	}

	@Override
	public TSesion read(int id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Sesion WHERE id=" + id + ";";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next())
				return new TSesion(resultSet.getInt("id"),resultSet.getDate("fecha"), 
						resultSet.getDate("horaini"), resultSet.getDate("horafin"),
						resultSet.getString("tipo"), resultSet.getBoolean("activo"),resultSet.getInt("idAlumno"),resultSet.getInt("idProfesor"));
			} 
	catch (Exception e) {
			return null;
		}
		return null;
	}
}
