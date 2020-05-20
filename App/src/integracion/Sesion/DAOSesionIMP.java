package integracion.Sesion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import integracion.DAOConnect;
import negocio.Profesor.TProfesor;
import negocio.Sesion.TSesion;
import resources.fechasConverter;

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
			String horaInicial= fechasConverter.horaToString(a.getHoraini());
			String horaFin=fechasConverter.horaToString(a.getHorafin());
			String fecha=fechasConverter.fechaToString(a.getFecha());
			String insertstm = "INSERT into Sesion VALUES ("+id+",'"+fecha+"','"+horaInicial+"','"+horaFin+"','"+a.getTipo()+"',"+a.isActivo()+", "+a.getIdAlumno()+", "+a.getIdProfesor()+");";
			
			int resultSet = statement.executeUpdate(insertstm);
			if(resultSet==0) return -5;
			return id;
		}
		catch (Exception e) {	
			return -4;
		}
		
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
	public boolean ocupadoProfesor(TSesion ses) { //true si ocupado,false si no
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT count(*) as noValidos "
					+ "FROM Sesion s "
					+ "Where s.id<>"+ses.getId()+" AND s.idProfesor="+ses.getIdProfesor()
					+ " AND s.fecha='"+fechasConverter.fechaToString(ses.getFecha())+"' AND "
					//lo que viene a continuacion comprueba la validez de las horas
					+"("
						+ "('"+fechasConverter.horaToString(ses.getHoraini())+"' BETWEEN s.horaini AND s.horafin OR "
						+"'"+fechasConverter.horaToString(ses.getHorafin())+"' BETWEEN s.horaini AND s.horafin)"
						
						+"OR"
						
						+"('"+fechasConverter.horaToString(ses.getHoraini())+"'<s.horaini AND "
						+"'"+fechasConverter.horaToString(ses.getHorafin())+"'>s.horafin)"
						
					+");";
			
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				int noValidos = resultSet.getInt("noValidos");
				return noValidos!=0?true:false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public boolean ocupadoAlumno(TSesion ses) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT count(*) as noValidos "
					+ "FROM Sesion s "
					+ "Where s.id<>"+ses.getId()+" AND s.idAlumno="+ses.getIdAlumno()
					+ " AND s.fecha='"+fechasConverter.fechaToString(ses.getFecha())+"' AND " 
					//lo que viene a continuacion comprueba la validez de las horas
					+"("
						+ "('"+fechasConverter.horaToString(ses.getHoraini())+"' BETWEEN s.horaini AND s.horafin OR "
						+"'"+fechasConverter.horaToString(ses.getHorafin())+"' BETWEEN s.horaini AND s.horafin)"
						
						+"OR"
						
						+"('"+fechasConverter.horaToString(ses.getHoraini())+"'<s.horaini AND "
						+"'"+fechasConverter.horaToString(ses.getHorafin())+"'>s.horafin)"
						
					+");";
			
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				int noValidos = resultSet.getInt("noValidos");
				return noValidos!=0?true:false;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	@Override
	public List<TSesion> readAllProfesor(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Sesion Where idProfesor="+id+";";
			List<TSesion> list = new ArrayList<TSesion>();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				list.add(new TSesion(resultSet.getInt("id"),resultSet.getDate("fecha"), 
						resultSet.getTime("horaini"), resultSet.getTime("horafin"),
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
			String query = "SELECT * FROM Sesion Where idAlumno="+id+";";
			List<TSesion> list = new ArrayList<TSesion>();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				list.add(new TSesion(resultSet.getInt("id"),resultSet.getDate("fecha"), 
						resultSet.getTime("horaini"), resultSet.getTime("horafin"),
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
			String query = "UPDATE Sesion SET fecha='"+fechasConverter.fechaToString(a.getFecha())+"',horaini='"
			+fechasConverter.horaToString(a.getHoraini())+"',horafin='"+fechasConverter.horaToString(a.getHorafin())+"',tipo='"+a.getTipo()
			+"',Activo="+a.isActivo()+",idAlumno="+a.getIdAlumno()
			+",idProfesor="+a.getIdProfesor()
			
			+" WHERE id="+a.getId()+";";
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
						resultSet.getTime("horaini"), resultSet.getTime("horafin"),
						resultSet.getString("tipo"), resultSet.getBoolean("activo"),resultSet.getInt("idAlumno"),resultSet.getInt("idProfesor"));
			} 
	catch (Exception e) {
			return null;
		}
		return null;
	}
}
