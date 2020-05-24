package tNegocio.Vehiculo;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import negocio.FactoriaSA;
import negocio.Profesor.TProfesor;
import negocio.Vehiculo.SAVehiculo;
import negocio.Vehiculo.TVehProf;
import negocio.Vehiculo.TVehiculo;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import tAux.DBUtil;

@TestMethodOrder(OrderAnnotation.class)
public class tVehiculoNegTest {
/*
 * public int create(TVehiculo v);
	public TVehiculo read(int id);
	public List<TVehiculo> readAll();
	public int update(TVehiculo v);
	public int delete (String id);
	public int regDmg(String[] datos);
	public int asigVehProf(TVehProf datos);
 */
	//public TVehiculo(int id, int idSucursal, String tipo, String matricula, boolean activo, String daños)
	private SAVehiculo sa = FactoriaSA.getInstance().generateSAVehiculo();
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
			DBUtil.addSomething("INSERT INTO `Sucursal` (`id`, `ciudad`, `telefono`, `direccion`, `activo`) VALUES ('1', 'Lorca', '915411555', 'Av del Triangulo Longo',1);");
			DBUtil.addSomething("INSERT INTO `Sucursal` (`id`, `ciudad`, `telefono`, `direccion`, `activo`) VALUES ('2', 'Madrid', '654987321', 'Calle Alpargata',1);");			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	@Order(1)
	void testSACreate() {
		TVehiculo v1= new TVehiculo(0,1,"Toyota Auris","1111BCD",true,"Roce");
		assertEquals(1,sa.create(v1));
		TVehiculo v2= new TVehiculo(0,1,"Fiat 600","1111ABC",true,"");
		assertEquals(-3,sa.create(v2));
		TVehiculo v3= new TVehiculo(0,1,"Toyota Auris","1111BCD",true,"Roce");
		assertEquals(-2,sa.create(v3));
		TVehiculo v4= new TVehiculo(0,99,"Toyota Auris","4321DRT",true,"Mi sucursal no existe");
		assertEquals(-3,sa.create(v4));
		TVehiculo v5= new TVehiculo(0,2,"Seat Leon","6767GTY",true,"Intermitente derecho");
		assertEquals(2,sa.create(v5));
	}
	@Test
	@Order(2)
	void testSARead() {
		TVehiculo v1= new TVehiculo(1,1,"Toyota Auris","1111BCD",true,"Roce");
		TVehiculo v5= new TVehiculo(2,2,"Seat Leon","6767GTY",true,"Intermitente derecho");
		assertEquals(true,equals(v1,sa.read(1)));
		assertEquals(true,equals(v5,sa.read(2)));
		assertEquals(null,sa.read(3));
	}
	@Test
	@Order(3)
	void testSAUpdate() {
		TVehiculo v1= new TVehiculo(1,1,"Toyota Auris","8888888",true,"Roce");
		assertEquals(-3,sa.update(v1));
		v1= new TVehiculo(1,99,"Toyota Auris","1111BCD",true,"Roce");
		assertEquals(-3,sa.update(v1));
		TVehiculo v2= new TVehiculo(5,1,"Renault Megane","5555GFD",true,"");
		assertEquals(-1,sa.update(v2));
		v1= new TVehiculo(1,1,"Toyota Auris","6767GTY",true,"Roce");
		assertEquals(-2,sa.update(v1));
		TVehiculo v5= new TVehiculo(2,2,"Seat Ibiza","9999FFF",true,"Intermitente derecho");
		assertEquals(2,sa.update(v5)); assertEquals(true,equals(v5,sa.read(2)));
		v1= new TVehiculo(1,2,"Toyota Auris","1111BCD",true,"Roce");
		assertEquals(1,sa.update(v1)); assertEquals(true,equals(v1,sa.read(1)));
	}
	@Test
	@Order(4)
	void testSADelete() {
		assertEquals(-1,sa.delete("9"));
		assertEquals(1,sa.delete("1"));
		assertEquals(false,sa.read(1).isActivo());
		assertEquals(-6,sa.delete("1"));
		assertEquals(-3,sa.delete("dos"));
	}
	@Test
	@Order(5)
	void testReactiva() {
		TVehiculo v1= new TVehiculo(1,2,"Toyota Auris","1111BCD",true,"Roce");
		assertEquals(true,sa.read(sa.update(v1)).isActivo());
	}
	@Test
	@Order(6)
	void testRegDMG() {
		String[] datos1= {"4","No existo"};
		assertEquals(-1,sa.regDmg(datos1));
		String[] datos= {"1","No mete segunda"};
		assertEquals("Roce,No mete segunda",sa.read(sa.regDmg(datos)).getDaños());
	}
	@Test
	@Order(7)
	void testVehProf() {
		TVehProf u= new TVehProf(1,1); //profesor, vehiculo
		assertEquals(-2,sa.asigVehProf(u));
		//Añade un profesor sin pasar por otros DAO
		DBUtil.addSomething("INSERT into Profesor VALUES(1,1,'123456789P','Lucas','Vazquez','912345687','profLucas@apm.es',1340,1)");
		assertEquals(-2,sa.asigVehProf(new TVehProf(1,10)));
		assertEquals(1,sa.asigVehProf(u));
		assertEquals(-4,sa.asigVehProf(new TVehProf(1,2)));
	}
}
