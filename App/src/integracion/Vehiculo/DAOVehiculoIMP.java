package integracion.Vehiculo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.DAOConnect;
import integracion.DAOConnect;
import negocio.Vehiculo.TVehiculo;

public class DAOVehiculoIMP implements DAOVehiculo {

	private int getID() {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String s= "SELECT IFNULL(MAX(id),0) as maximoo FROM Vehiculos;";
			ResultSet resultSet = statement.executeQuery(s);
			if(resultSet.next()) {
				int id = resultSet.getInt("maximoo");
				System.out.println(id);
				return id+1;
			}
		}
		catch (Exception e) {	
			e.printStackTrace();
		}
		return -1;
	}
	@Override
	public int create(TVehiculo v) {
		// TODO Auto-generated method stub
		int id = 0;
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			id = getID();
			String insertstm = "INSERT into Vehiculos VALUES ("+id+","+v.getIdSucursal()+",'"+v.getTipo()+"','"+
			v.getDaños()+"',"+v.isActivo()+",'"+v.getMatricula()+"');";
			
			//ResultSet resultSet = statement.executeQuery(query);
			int resultSet = statement.executeUpdate(insertstm);
			/*if(resultSet.next()) {
				System.out.print(resultSet.getString("uno"));
			}*/
		}
		catch (Exception e) {	
			e.printStackTrace();
		}
		return id;
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
	public TVehiculo findByName(String nombre) {
		// TODO Auto-generated method stub
		return null;
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
			e.printStackTrace();
		}
		return null;
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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public int delete(int id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String deletestm = "UPDATE Vehiculos SET activo="+0+" WHERE id="+id+";";
			
			
			int resultSet = statement.executeUpdate(deletestm);
			
		}
		catch (Exception e) {	
			e.printStackTrace();
		}
		return 1;
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
			
		}
		catch (Exception e) {	
			e.printStackTrace();
		}
		return 0;
	}

}
