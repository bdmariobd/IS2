package integracion.Alumno;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import integracion.DAOConnect;
import negocio.Alumno.TAlumno;
import negocio.Alumno.TRelleno;

public class DAOAlumnoIMP implements DAOAlumno {
	
	public int rellenar(TRelleno r) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			int id = getID();
			if(id<1) return id;
			
			String insertstm = "INSERT into TestRealizados VALUES ("+r.getIdAlumno()+","+r.getIdTest()+","+r.getNumFallos()+");";
			
			int resultSet = statement.executeUpdate(insertstm);
		}
		catch (Exception e) {	
			e.printStackTrace();
		}
		return r.getIdAlumno();
	}
	
	public List<TRelleno> readAllR(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM TestRealizados;";
			List<TRelleno> list = new ArrayList<TRelleno>();
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				if(resultSet.getInt("idAlumno")==id) {
				list.add(new TRelleno(resultSet.getInt("idAlumno"),resultSet.getInt("idTest"),
						resultSet.getInt("numFallos")));
				}
			}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private int getID() {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String s = "SELECT IFNULL(MAX(id),0) as maximoo FROM Alumno;";
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
	public int create(TAlumno a) {
		// TODO Auto-generated method stub
		int id = -1;
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			id = getID();
			if (id < 1)
				return id;

			String insertstm = "INSERT into Alumno VALUES (" + id + ",'" + a.getDNI() + "','" + a.getNombre() + "','"
					+ a.getApellidos() + "'," + a.getTelefono() + ",'" + a.getEmail() + "'," + a.getAmaxofobia() + ","
					+ a.getActivo() + ");";

			int resultSet = statement.executeUpdate(insertstm);
			if (resultSet == 0)
				return -5;
			return id;
		} catch (Exception e) {

			return -4;
		}

	}

	@Override
	public TAlumno read(int id) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Alumno WHERE id=" + id + ";";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next())
				return new TAlumno(resultSet.getInt("id"), resultSet.getString("DNI"), resultSet.getString("nombre"),
						resultSet.getString("apellidos"), resultSet.getInt("telefono"), resultSet.getString("email"),
						resultSet.getBoolean("amaxofobia"), resultSet.getBoolean("activo"));
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	public boolean existeDNI(String DNI) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String consulta = "SELECT COUNT(*) FROM Alumno WHERE DNI = '" + DNI + "';";
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
	

	@Override
	public int findByDNI(String dni) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Alumno WHERE dni='" + dni + "';";
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
	public int findByID(String id) {
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Alumno WHERE id=" + id + ";";
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
	public List<TAlumno> readAll() {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Alumno;";
			List<TAlumno> list = new ArrayList<TAlumno>();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				list.add(new TAlumno(resultSet.getInt("id"), resultSet.getString("DNI"), resultSet.getString("nombre"),
						resultSet.getString("apellidos"), resultSet.getInt("telefono"), resultSet.getString("email"),
						resultSet.getBoolean("amaxofobia"), resultSet.getBoolean("activo")));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int update(TAlumno a) {
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "UPDATE Alumno SET DNI='" + a.getDNI() + "',nombre='" + a.getNombre() + "',apellidos='"
					+ a.getApellidos() + "',telefono=" + a.getTelefono() + ",email='" + a.getEmail() + "',amaxofobia="
					+ a.getAmaxofobia() + ",Activo=" + a.getActivo() + " WHERE id=" + a.getId() + ";";
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
		// TODO Auto-generated method stub
		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String deletestm = "UPDATE Alumno SET activo=" + 0 + " WHERE id=" + id + ";";
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
		// TODO Auto-generated method stub

		try {
			Connection connection = DAOConnect.getInstance().getConnection();
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM Alumno WHERE id=" + id + ";";
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				return resultSet.getBoolean("activo") ? 0 : -6;
			}
			return -1;
		} catch (Exception e) {
			return -4;
		}
	}

}
