package tNegocio.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import integracion.FactoriaDAO;
import negocio.FactoriaSA;
import negocio.Test.SATest;
import negocio.Test.TTest;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import tAux.DBUtil;

@TestMethodOrder(OrderAnnotation.class)
public class tTestNegTest {

	private SATest sa = FactoriaSA.getInstance().generateSATest();
	private TTest a = new TTest (1,"Permiso B",30,true); // test activo
	private TTest b = new TTest (2,"Permiso A2",30,false); // test noactivo
	private TTest c = new TTest (3,"Permiso B1",30,true); // test activo
	private TTest muchaspreguntas = new TTest(4,"Permiso B",80,true);
	private TTest muypocas = new TTest (4,"Permiso A",0,true);
	private TTest tipoIncorrecto = new TTest(4,"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",30,true);
	
	@BeforeAll
	public static void initSetUp() {
		//Vaciamos la tabla correspondiente.
		try {
			DBUtil.deleteTable("Test");
					} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	@Order(1)
	void testDAOCreate() {
		assertEquals(1,sa.create(a));
		assertEquals(2,sa.create(b));
		assertEquals(3,sa.create(c));
		assertEquals(-3,sa.create(muchaspreguntas));
		assertEquals(-3,sa.create(muypocas));
		assertEquals(-3,sa.create(tipoIncorrecto));
		
		
	}
	@Test
	@Order(2)
	void testSARead() {
		TTest leido = sa.read(2);
		assertEquals(2,leido.getId());
		assertEquals("Permiso A2",leido.getTipo());
		assertEquals(30,leido.getNumpreguntas());
		assertEquals(false,leido.isActivo());
		//leido nullo
		assertEquals(null,sa.read(99));
	}
	@Test
	@Order(3)
	void testSAUpdate() { // Compruebo que al test 1 el tipo de permiso cambia a A
		TTest cambio = new TTest(1,"Permiso A",30,true);
		assertEquals(1,sa.update(cambio));
		TTest leido = sa.read(1);
		assertEquals(cambio.getTipo(),leido.getTipo());
		TTest cambioIncorrecto = new TTest(1,"Permiso A",90,true);
		assertEquals(-3,sa.update(cambioIncorrecto));
	
	}
	@Test
	@Order(4)
	void testDAODelete() {
		assertEquals(1,sa.delete("1"));
		assertEquals(-6,sa.delete("2"));
		assertEquals(-1,sa.delete("999"));
		assertEquals(-3,sa.delete("jeje"));
		TTest leido = sa.read(1);
		assertEquals(false,leido.isActivo());
	}
	
}