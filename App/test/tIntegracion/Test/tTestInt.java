package tIntegracion.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import integracion.FactoriaDAO;
import negocio.Test.TTest;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import tAux.DBUtil;

@TestMethodOrder(OrderAnnotation.class)
public class tTestInt {

	@BeforeAll
	public static void initSetUp() {
		//Vaciamos la tabla correspondiente.
		try {
			DBUtil.deleteTable("Alumno");
			DBUtil.deleteTable("Test");
			DBUtil.deleteTable("TestRealizados");
					} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	@Order(1)
	void testDAOCreate() {
		TTest a = new TTest (0,"Permiso B",30,true); // test activo
		TTest b = new TTest (0,"Permiso A2",30,false); // test noactivo
		TTest c = new TTest (0,"Permiso B1",30,true); // test activo
		assertEquals(1,FactoriaDAO.getInstance().generateDAOTest().create(a));
		assertEquals(2,FactoriaDAO.getInstance().generateDAOTest().create(b));
		assertEquals(3,FactoriaDAO.getInstance().generateDAOTest().create(c));
		
	}
	@Test
	@Order(2)
	void testDAORead() {
		TTest leido = FactoriaDAO.getInstance().generateDAOTest().read(1);
		assertEquals(1,leido.getId());
		assertEquals("Permiso B",leido.getTipo());
		assertEquals(30,leido.getNumpreguntas());
		assertEquals(true,leido.isActivo());
		//leido nullo
		assertEquals(null,FactoriaDAO.getInstance().generateDAOTest().read(99));
	}
	@Test
	@Order(3)
	void testDAOUpdate() { // Compruebo que al test 1 el tipo de permiso cambia a A
		TTest cambio = new TTest(1,"Permiso A",30,true);
		assertEquals(1,FactoriaDAO.getInstance().generateDAOTest().update(cambio));
		TTest leido = FactoriaDAO.getInstance().generateDAOTest().read(1);
		assertEquals(cambio.getTipo(),leido.getTipo());
		
	}
	@Test
	@Order(4)
	void testDAODelete() {
		assertEquals(1,FactoriaDAO.getInstance().generateDAOTest().delete(1));
		assertEquals(-1,FactoriaDAO.getInstance().generateDAOTest().delete(9));
		TTest leido = FactoriaDAO.getInstance().generateDAOTest().read(1);
		assertEquals(false,leido.isActivo());
	}
	@Test
	@Order(5)
	void testDAOisDelete() {
		assertEquals(0,FactoriaDAO.getInstance().generateDAOTest().isDeleted(3));
		assertEquals(-6,FactoriaDAO.getInstance().generateDAOTest().isDeleted(1));
	}
}
