package integracion.Sucursal;

	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import integracion.DAOConnect;
    import integracion.DAOConnect;
	import negocio.Sucursal.TSucursal;

	public class DAOSucursalIMP implements DAOSucursal {

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
				String s= "SELECT IFNULL(MAX(id),0) as maximoo FROM Sucursales;";
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
		public int create(TSucursal suc) {
			// TODO Auto-generated method stub
			try {
				Connection connection = DAOConnect.getInstance().getConnection();
				Statement statement = connection.createStatement();
				int id = getID();
				if(id<1) return id;
				
				String insertstm = "INSERT into Vehiculos VALUES ("+id+","+suc.getCiudad()+",'"+suc.getTelefono()+"','"+
						suc.getDireccion()+"',"+suc.isActivo()+"');";
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
		public TSucursal read(int id) {
			try {
				Connection connection = DAOConnect.getInstance().getConnection();
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM Sucursales WHERE id="+id+";";
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()) return new TSucursal(resultSet.getInt("id"),resultSet.getString("ciudad"), 
						resultSet.getInt("telefono"), resultSet.getString("direccion"),
						resultSet.getBoolean("activo"));
			}
			catch (Exception e) {
				return null;
			}
			return null;
		}
		
		@Override
		public int findbyID(String id) { //busqueda por id
			// TODO Auto-generated method stub
			try {
				Connection connection = DAOConnect.getInstance().getConnection();
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM Sucursales WHERE id="+id+";";
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()) return 1;
				else return -1;
			}
			catch (Exception e) {
				return -4;
			}
		}


		@Override
		public List<TSucursal> readAll() {
			// TODO Auto-generated method stub
			try {
				Connection connection = DAOConnect.getInstance().getConnection();
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM Sucursales;";
				
				List<TSucursal> list = new ArrayList<TSucursal>();
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next()) {
					list.add(new TSucursal(resultSet.getInt("id"),resultSet.getString("ciudad"), 
							resultSet.getInt("telefono"), resultSet.getString("direccion"),
							resultSet.getBoolean("activo")));
				}
				return list;
			}
			catch (Exception e) {
				return null;
			}
		}

		@Override
		public int update(TSucursal suc) {
			// TODO Auto-generated method stub
			try {
				Connection connection = DAOConnect.getInstance().getConnection();
				Statement statement = connection.createStatement();
				String query = "UPDATE Sucursales SET ciudad='"+suc.getCiudad()+"',telefono='"+suc.getTelefono()+
						",direccion='"+suc.getDireccion()+"',activo="+suc.isActivo()+"' WHERE id="+suc.getId()+";";
				int resultSet = statement.executeUpdate(query);
				if(resultSet==0) return -1;
				return suc.getId();
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
				String deletestm = "UPDATE Sucursales SET activo="+0+" WHERE id="+id+";";
				int resultSet = statement.executeUpdate(deletestm);
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
				String query = "SELECT * FROM Sucursales WHERE id="+id+";";
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

	}
