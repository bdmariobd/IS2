package integracion.Profesor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import integracion.DAOConnect;
import negocio.Profesor.TProfesor;

public class DAOProfesorIMP implements DAOProfesor {

	private int getID() {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String s = "SELECT IFNULL(MAX(id),0) as maximoo FROM Profesor;";
			ResultSet resultSet = statement.executeQuery(s);
			if (resultSet.next()) {
				int id = resultSet.getInt("maximoo");
				return id + 1;
			}
		} catch (Exception e) {
			return -4;
		}
		return -5;
	}

	@Override
	public int create(TProfesor a) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			int id = getID();
			if (id < 1)
				return id;
			String insertstm = "INSERT into Profesor VALUES (" + id + ", " + a.getIdSucursal() + ",'" + a.getDNI()
					+ "','" + a.getNombre() + "','" + a.getApellidos() + "'," + a.getTelefono() + ",'" + a.getEmail()
					+ "'," + a.getSueldo() + "," + a.getActivo() + ");";

			statement.executeUpdate(insertstm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public boolean existeDNI(String DNI) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String consulta = "SELECT COUNT(*) FROM Profesor WHERE DNI = '" + DNI + "';";
			ResultSet resultSet = statement.executeQuery(consulta);
			if (resultSet.next())
				return (resultSet.getInt(1) > 0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
	
	public boolean existeIdSucursal(int idSucursal) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String consulta = "SELECT COUNT(*) FROM Sucursal WHERE id = " + idSucursal + ";";
			ResultSet resultSet = statement.executeQuery(consulta);
			if (resultSet.next())
				return (resultSet.getInt(1) > 0);					;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public TProfesor read(int id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Profesor WHERE id=" + id + ";";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next())
				return new TProfesor(resultSet.getInt("id"), resultSet.getInt("idSucursal"), resultSet.getString("DNI"),
						resultSet.getString("nombre"), resultSet.getString("apellidos"), resultSet.getInt("telefono"),
						resultSet.getString("email"), resultSet.getInt("Sueldo"), resultSet.getBoolean("activo"));
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	@Override
	public int findByID(String id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Profesor WHERE id=" + id + ";";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next())
				return 1;
			else
				return -1;
		} catch (Exception e) {
			return -4;
		}
	}

	@Override
	public List<TProfesor> readAll() {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Profesor;";
			List<TProfesor> list = new ArrayList<TProfesor>();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				list.add(new TProfesor(resultSet.getInt("id"), resultSet.getInt("idSucursal"),
						resultSet.getString("DNI"), resultSet.getString("nombre"), resultSet.getString("apellidos"),
						resultSet.getInt("telefono"), resultSet.getString("email"), resultSet.getInt("Sueldo"),
						resultSet.getBoolean("activo")));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(TProfesor a) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "UPDATE Profesor SET idSucursal = " + a.getIdSucursal() + ", DNI='" + a.getDNI()
					+ "',nombre='" + a.getNombre() + "',apellidos='" + a.getApellidos() + "',telefono="
					+ a.getTelefono() + ",email='" + a.getEmail() + "',sueldo = " + a.getSueldo() + ",Activo="
					+ a.getActivo() + " WHERE id=" + a.getId() + ";";
			int resultSet = statement.executeUpdate(query);
			if (resultSet == 0)
				return -1;
			return a.getId();
		} catch (Exception e) {
			e.printStackTrace();
			return -4;
		}

	}

	@Override
	public int delete(int id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String deletestm = "UPDATE Profesor SET activo=" + 0 + " WHERE id=" + id + ";";
			int resultSet = statement.executeUpdate(deletestm);
			if (resultSet == 0)
				return -1;
			return id;
		} catch (Exception e) {
			return -4;
		}
	}

	@Override
	public int isDeleted(int id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Profesor WHERE id=" + id + ";";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				return resultSet.getBoolean("activo") ? 0 : -6;
			}
			return -1;
		} catch (Exception e) {
			return -4;
		}
	}

	@Override
	public boolean existeID(String id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String consulta = "SELECT COUNT(*) FROM Profesor WHERE id = '" + id+ "';";
			ResultSet resultSet = statement.executeQuery(consulta);
			if (resultSet.next())
				return (resultSet.getInt(1) > 0);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
