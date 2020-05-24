package tNegocio.Sucursal;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import negocio.FactoriaSA;
import negocio.Sucursal.TSucursal;

import org.junit.jupiter.api.Order;

import tAux.DBUtil;
@TestMethodOrder(OrderAnnotation.class)
public
class tSucursalNegTest {

	private TSucursal a = new TSucursal(1,"Murcia",919199191,"Calle del Limon de Don Salva Espin",true);
	private TSucursal b = new TSucursal(2,"Madrid",9,"Nuñez de Balboa, N9 ",false);
	private TSucursal c = new TSucursal(3,"Madrid",988888888,"Santa Engracia, N10 ",true);
	
	  @BeforeAll
		public static void initSetUp() {
			//Vaciamos la tabla correspondiente.
			try {
				DBUtil.deleteTable("Sucursal");
				DBUtil.deleteTable("Vehiculos");
				DBUtil.deleteTable("Sesion");
				DBUtil.deleteTable("Profesor");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	@Test
	@Order(1)
	void test() {
		assertEquals(1,FactoriaSA.getInstance().generateSASucursal().create(a));
		assertEquals(2,FactoriaSA.getInstance().generateSASucursal().create(c));
	}
	@Test
	@Order(2)
	void test2() {
		assertEquals(-3,FactoriaSA.getInstance().generateSASucursal().create(b));
	}
	@Test
	@Order(3)
	void test3() {
		TSucursal temp = FactoriaSA.getInstance().generateSASucursal().read(1);
		assertEquals(a.getId(),temp.getId());
		assertEquals(a.getCiudad(),temp.getCiudad());
		assertEquals(a.getTelefono(),temp.getTelefono());
		assertEquals(a.getDireccion(),temp.getDireccion());
		assertEquals(a.isActivo(),temp.isActivo());
	}
	@Test
	@Order(4)
	void test4() {
		DBUtil.addSomething("INSERT INTO `Profesor` (`id`, `idSucursal`, `DNI`, `nombre`, `apellidos`, `telefono`, `email`, `sueldo`, `activo`) VALUES ('1', '1', '1', 'Mario', 'Sempai', '915466783', 'junits@gmail.com', '2500', '1')");
		DBUtil.addSomething("Insert INTO Vehiculos Values(1,1,'Coche BMW M3',' ',1,'4543XML');");
		assertEquals(-7, FactoriaSA.getInstance().generateSASucursal().delete("1"));
	}
	@Test
	@Order(5)
	void test5() {
		DBUtil.addSomething("INSERT INTO `Profesor` (`id`, `idSucursal`, `DNI`, `nombre`, `apellidos`, `telefono`, `email`, `sueldo`, `activo`) VALUES ('2', '1', '4', 'Fran', 'Trader', '91543233', 'junits@gmail.com', '2500', '0')");
		DBUtil.addSomething("Insert INTO Vehiculos Values(2,1,'Coche BMW M3',' ',0,'4444KLK');");
		assertEquals(-7, FactoriaSA.getInstance().generateSASucursal().delete("1"));
		assertEquals(-7, FactoriaSA.getInstance().generateSASucursal().delete("1"));
	}
	@Test
	@Order(6)
	void test6() {
		DBUtil.addSomething("INSERT INTO `Profesor` (`id`, `idSucursal`, `DNI`, `nombre`, `apellidos`, `telefono`, `email`, `sueldo`, `activo`) VALUES ('3', '2', '4', 'Fran', 'Trader', '91543233', 'junits@gmail.com', '2500', '0')");
		DBUtil.addSomething("Insert INTO Vehiculos Values(3,2,'Coche BMW M4',' ',0,'5555KLK');");
		assertEquals(2, FactoriaSA.getInstance().generateSASucursal().delete("2"));
	}
	@Test
	@Order(7)
	void test7() {
		assertEquals(-6, FactoriaSA.getInstance().generateSASucursal().delete("2"));
	}
	@Test
	@Order(8)
	void test8() {
		TSucursal temp = new TSucursal(1,"Murcia",9,"Calle del Limon de Don Salva Espin",true);
		assertEquals(-3, FactoriaSA.getInstance().generateSASucursal().update(temp));
	}
	@Test
	@Order(9)
	void test9() {
		TSucursal temp = new TSucursal(1,"Murcia",922224444,"Calle del Limon de Don Salva Espin",true);
		assertEquals(1, FactoriaSA.getInstance().generateSASucursal().update(temp));
	}
	@Test
	@Order(10)
	void test10() {
		assertEquals(-1, FactoriaSA.getInstance().generateSASucursal().findByID(999));
	}
	@Test
	@Order(11)
	void test11() {
		assertEquals(1, FactoriaSA.getInstance().generateSASucursal().findByID(1));
	}
}
