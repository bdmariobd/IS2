package tIntegracion.Vehiculo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import integracion.FactoriaDAO;
import integracion.Vehiculo.DAOVehiculo;
import negocio.Alumno.TAlumno;
import negocio.Alumno.TRelleno;
import negocio.Vehiculo.TVehProf;
import negocio.Vehiculo.TVehiculo;
import tAux.DBUtil;

@TestMethodOrder(OrderAnnotation.class)
class tVehiculoInt {
/*
 public int create(TVehiculo v);

	public TVehiculo read(int id);

	public List<TVehiculo> readAll();

	public int update(TVehiculo v);

	public int delete(int id);
	
	public int regDamage(String[] datos);
	
	public int isDeleted(int id);
	
	public boolean vehExist(int idv);
	
	public int asignarVehProf(TVehProf datos);
	
	public int findbyID(String id);

	int findByName(TVehiculo v);
 */
	
	
	//   public TVehiculo(int id, int idSucursal, String tipo, String matricula, boolean activo, String daños)
	
	public boolean equals(TVehiculo v1, TVehiculo v2) {
		if((v1==null)!=(v2==null))return false;
		return v1.getId()==v2.getId() && v1.getIdSucursal()==v2.getIdSucursal()&&v1.getTipo().equals(v2.getTipo())&&v1.getMatricula().equals(v2.getMatricula())
				&&v1.isActivo()==v2.isActivo()&&v1.getDaños().equals(v2.getDaños());
	}
	@BeforeAll
	public static void initSetUp() {
		//Vaciamos la tabla correspondiente.
		try {
			DBUtil.deleteTable("VehiculoProfesor");
			DBUtil.deleteTable("Sucursal");
			DBUtil.deleteTable("Profesor");
			DBUtil.deleteTable("Vehiculos");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	@Order(1)
	void testDAOCreate() {
		DBUtil.addSomething("INSERT INTO `Sucursal` (`id`, `ciudad`, `telefono`, `direccion`, `activo`) VALUES ('1', 'Lorca', '915411555', 'Av del Triangulo Longo',1);");
		TVehiculo v1= new TVehiculo(0,1,"Toyota Auris","7654DRT",true,"Roce");
		TVehiculo v2= new TVehiculo(0,1,"Fiat 600","7654DRS",true,"");
		assertEquals(1,FactoriaDAO.getInstance().generateDAOVehiculo().create(v1));
		assertEquals(2,FactoriaDAO.getInstance().generateDAOVehiculo().create(v2));
	}
	@Test
	@Order(2)
	void testDAORead() { //leo los vehiculos insertados antes
		TVehiculo v1= new TVehiculo(1,1,"Toyota Auris","7654DRT",true,"Roce");
		TVehiculo v2= new TVehiculo(2,1,"Fiat 600","7654DRS",true,"");
		TVehiculo vNoExiste= new TVehiculo(33,1,"Fragoneta","1234DRS",true,"No existo");
		DAOVehiculo dao= FactoriaDAO.getInstance().generateDAOVehiculo();
		assertEquals(true,equals(v1,dao.read(v1.getId())));
		assertEquals(true,equals(v2,dao.read(v2.getId())));
		assertEquals(false,equals(vNoExiste,dao.read(vNoExiste.getId())));
	}
	@Test
	@Order(3)
	void testDAOUpdate() { //comprueba si al primer vehiculo se le cambia el modelo 
		DAOVehiculo dao= FactoriaDAO.getInstance().generateDAOVehiculo();
		TVehiculo v1MOD= new TVehiculo(1,1,"Toyota Supra","7654DRT",true,"Roce");
		assertEquals(1,dao.update(v1MOD));
		assertEquals(true,equals(v1MOD,dao.read(v1MOD.getId()))); //leo la base de datos para ver si se ha guardado el cambio
	}
	@Test
	@Order(4)
	void testDelete() {//borro el vehiculo con id=1
		DAOVehiculo dao= FactoriaDAO.getInstance().generateDAOVehiculo();
		assertEquals(1,dao.delete(1));
		assertEquals(false, dao.read(1).isActivo());//leo para ver si se borro
	}
	@Test
	@Order(5)
	void testRegDMG() {//registro daños en id=1
		DAOVehiculo dao= FactoriaDAO.getInstance().generateDAOVehiculo();
		String[]datos= {"1","Roce"};
		assertEquals(1,dao.regDamage(datos));
	}
	@Test
	@Order(6)
	void testIsDeleted() { //el id=1 estaba borrado, voy a comprobarlo
		DAOVehiculo dao= FactoriaDAO.getInstance().generateDAOVehiculo();
		assertEquals(-6,dao.isDeleted(1));
	}
	@Test
	@Order(7)
	void testAsigProf() {//se le asigna a id=2 un profesor
		DBUtil.addSomething("INSERT INTO `Profesor` (`id`, `idSucursal`, `DNI`, `nombre`, `apellidos`, `telefono`, `email`, `sueldo`, `activo`) VALUES ('1', '2', '12345678E', 'Fran', 'Trader', '91543233', 'junits@gmail.com', '2500', true)");
		DAOVehiculo dao= FactoriaDAO.getInstance().generateDAOVehiculo();
		TVehProf vh= new TVehProf(1,2);
		assertEquals(2,dao.asignarVehProf(vh));
	}
	@Test
	@Order(8)
	void testVehEx() {
		DAOVehiculo dao= FactoriaDAO.getInstance().generateDAOVehiculo();
		assertEquals(true,dao.vehExist(2));
	}
	@Test
	@Order(9)
	void testFindById() {
		DAOVehiculo dao= FactoriaDAO.getInstance().generateDAOVehiculo();
		assertEquals(1,dao.findbyID("2"));
	}
	@Test
	@Order(10)
	void testFindByName() {
		TVehiculo v2= new TVehiculo(1,1,"Fiat 600","7654DRS",true,"");
		TVehiculo v3= new TVehiculo(6,1,"Fiat 600","1111BCD",true,"");

		DAOVehiculo dao= FactoriaDAO.getInstance().generateDAOVehiculo();
		assertEquals(-2,dao.findByName(v2));	
		assertEquals(0,dao.findByName(v3));

	}
}
