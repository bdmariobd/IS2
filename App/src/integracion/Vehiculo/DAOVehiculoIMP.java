package integracion.Vehiculo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.DAOConnect;
import integracion.DAOConnect;
import negocio.Vehiculo.TVehProf;
import negocio.Vehiculo.TVehiculo;

public class DAOVehiculoIMP implements DAOVehiculo {

	/*ERRORES: 
	 * -1: no existe
	 * -2: ya existe
	 * -3: datos no validos (numeros negativos, matricula que no cumple el formato...)
	 * -4: error de la base de datos
	 * -5: otros errores desconocidos
	 * Si se devuelve un transfer con que se devuelva null ya vale
	 */
	
	//metodo que genera las id
	private int getID() {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String s= "SELECT IFNULL(MAX(id),0) as maximoo FROM Vehiculos;";
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
	public int create(TVehiculo v) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			int id = getID();
			if(id<1) return id;
			
			String insertstm = "INSERT into Vehiculos VALUES ("+id+","+v.getIdSucursal()+",'"+v.getTipo()+"','"+
			v.getDaños()+"',"+v.isActivo()+",'"+v.getMatricula()+"');";
			//ResultSet resultSet = statement.executeQuery(query);
			int resultSet = statement.executeUpdate(insertstm);
			if(resultSet==0) return -5;
			return id;
		}
		catch (Exception e) {	
			return -4;
		}
	}

	@Override
	public TVehiculo read(int id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Vehiculos WHERE id="+id+";";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) return new TVehiculo(resultSet.getInt("id"),resultSet.getInt("idSucursal"), 
					resultSet.getString("tipo"), resultSet.getString("matricula"),
					resultSet.getBoolean("activo"),resultSet.getString("danos"));
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}
	@Override
	public int findByName(String nombre) { //busqueda de matriculas
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Vehiculos WHERE matricula='"+nombre+"';";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) return -2;
			return 0;
		}
		catch (Exception e) {
			return -4;
		}
	}
	public int findbyID(String id) { 
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Vehiculos WHERE id="+id+";";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) return 1;
			else return -1;
		}
		catch (Exception e) {
			return -4;
		}
	}

	@Override
	public List<TVehiculo> readAll() {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Vehiculos;";
			
			List<TVehiculo> list = new ArrayList<TVehiculo>();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				list.add(new TVehiculo(resultSet.getInt("id"),resultSet.getInt("idSucursal"), 
						resultSet.getString("tipo"), resultSet.getString("matricula"),
						resultSet.getBoolean("activo"),resultSet.getString("danos")));
			}
			return list;
		}
		catch (Exception e) {
			return null;
		}
	}

	@Override
	public int update(TVehiculo v) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "UPDATE Vehiculos SET tipo='"+v.getTipo()+"',matricula='"+v.getMatricula()+
					"',activo="+v.isActivo()+",danos='"+v.getDaños()+"' WHERE id="+v.getId()+";";
			int resultSet = statement.executeUpdate(query);
			if(resultSet==0) return -1;
			return v.getId();
		}
		catch (Exception e) {
			return -4;
		}
	}
	
	@Override
	public int delete(int id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String deletestm = "UPDATE Vehiculos SET activo="+0+" WHERE id="+id+";";
			int resultSet = statement.executeUpdate(deletestm);
			if(resultSet==0) return -1;
			return id;
		}
		catch (Exception e) {	
			return -4;
		}
	}

	public int regDamage(String[] datos) {
		try {
			//datos 0 = iddelvehiculo;
			//datos 1 = danos;
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			int id=Integer.parseInt(datos[0]);
			String updatestm = "UPDATE Vehiculos SET danos=concat(danos,'"+", "+datos[1]+"') WHERE id="+id+";";
			//UPDATE `Vehiculos` SET `danos` = 'test' WHERE `Vehiculos`.`id` = 100
			int resultSet = statement.executeUpdate(updatestm);
			if(resultSet==0) return -1;
			return id;
		}
		catch (Exception e) {	
			return -4;
		}
	}
	public int isDeleted(int id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Vehiculos WHERE id="+id+";";
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
	public int asignarVehProf(TVehProf datos) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String insertstm = "INSERT into VehiculoProfesor VALUES ("+datos.getIDV()+","+datos.getIDP()+");";
			ResultSet resultSet = statement.executeQuery(insertstm);
			if(resultSet.next()) {
				return datos.getIDV();
			}
			return -1;
		}
		catch (Exception e) {
			return -4;
		}
	}
	
	

}
