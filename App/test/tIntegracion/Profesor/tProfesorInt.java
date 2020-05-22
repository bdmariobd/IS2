package tIntegracion.Profesor;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import integracion.FactoriaDAO;
import negocio.Profesor.TProfesor;
import tAux.DBUtil;
@TestMethodOrder(OrderAnnotation.class)
class tProfesorInt {
	@BeforeAll
	public static void initSetUp() {
		//Vaciamos la tabla correspondiente.
		try {
			DBUtil.deleteTable("Sucursal");
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
	void testDAOCreate() { 
		DBUtil.addSomething("INSERT INTO `Sucursal` (`id`, `ciudad`, `telefono`, `direccion`, `activo`) VALUES ('1', 'Lorca', '915411555', 'Av del Triangulo Longo',1);");
		TProfesor a = new TProfesor(0,1,"90638375Z","Fray","Luis",915439233,"editorial@ucm.es",2500,true);
		TProfesor b = new TProfesor(0,1,"80334444Z","De","Leon",915499933,"editorial2@ucm.es",3500,false);
		assertEquals(1,FactoriaDAO.getInstance().generateDAOProfesor().create(a));
		assertEquals(2,FactoriaDAO.getInstance().generateDAOProfesor().create(b));
	}

	
	@Test
	@Order(2)
	void testDAORead() {
		TProfesor t = FactoriaDAO.getInstance().generateDAOProfesor().read(1);
		assertEquals(t.getId(),1);
		assertEquals(t.getDNI(),"90638375Z");
		assertEquals(t.getIdSucursal(),1);
		assertEquals(t.getEmail(),"editorial@ucm.es");
		assertEquals(t.getActivo(),true);
		assertEquals(t.getApellidos(),"Luis");
		assertEquals(t.getSueldo(),2500);
		assertEquals(t.getNombre(),"Fray");
		assertEquals(t.getTelefono(),915439233);
	}
	@Test
	@Order(3)
	void testDAOReadNull() {
		TProfesor t = FactoriaDAO.getInstance().generateDAOProfesor().read(999);
		assertEquals(null,t);
	}
	@Test
	@Order(4)
	void testDaofindByID() {
		//Como el read pero en vez de leer alumnos lee ids. Comprobamos el caso de que este y no este. (1,999)
		assertEquals(1,FactoriaDAO.getInstance().generateDAOProfesor().findByID("1"));
		assertEquals(-1,FactoriaDAO.getInstance().generateDAOProfesor().findByID("999"));
	}
	@Test
	@Order(5)
	void testDAOUpdate() {
		// Se comprobara que al id 2 se le cambia el apellido a Tigre
		TProfesor temp = new TProfesor(2,1,"80334444Z","De","Tigre",333399933,"editorial2@ucm.es",3500,false);
		assertEquals(2,FactoriaDAO.getInstance().generateDAOProfesor().update(temp)); // Comprobamos que el update ha sido correcto
		TProfesor updated = FactoriaDAO.getInstance().generateDAOProfesor().read(2); // 
		assertEquals(updated.getApellidos(),temp.getApellidos());
	}
	@Test
	@Order(7)
	void testDAODelete() { 
		//Delete tiene una funcion logica, asi que establece el campo activo a 0;
		//Establecere el estado activo del id 1 a 0.
		assertEquals(1,FactoriaDAO.getInstance().generateDAOProfesor().delete(1));
		TProfesor updated = FactoriaDAO.getInstance().generateDAOProfesor().read(1); // 
		assertEquals(false,updated.getActivo());
	}
	@Test
	@Order(8)
	void testDAOisDeleted() {
		
		DBUtil.addSomething("INSERT INTO `Profesor` (`id`, `idSucursal`, `DNI`, `nombre`, `apellidos`, `telefono`, `email`, `sueldo`, `activo`) VALUES ('3', '2', '4', 'Fran', 'Trader', '91543233', 'junits@gmail.com', '2500', true)");
		// prof id 3 tiene campo activo a true
		assertEquals(0,FactoriaDAO.getInstance().generateDAOProfesor().isDeleted(3)); // Si devuelve 0, es que el campo esta activo.
		assertEquals(-1,FactoriaDAO.getInstance().generateDAOProfesor().isDeleted(9999)); // Pruebo que pasa si no existe el id que se le pasa.		
	}
	@Test
	@Order(9)
	void testDAOisDeletedAlready() {
		// Alumno id 2 tiene campo activo a false
		assertEquals(-6,FactoriaDAO.getInstance().generateDAOProfesor().isDeleted(2)); // Si devuelve -6, es que el campo no lo esta.
	}
	
	@Test
	@Order(10)
	void testDaofindByDNI() {
		assertEquals(true,FactoriaDAO.getInstance().generateDAOProfesor().existeID("1"));
		assertEquals(false,FactoriaDAO.getInstance().generateDAOProfesor().existeID("999"));
	}
	@Test
	@Order(11)
	void testDAOExisteIDSucursal() {
		assertEquals(true,FactoriaDAO.getInstance().generateDAOProfesor().existeIdSucursal(1));
	}
	@Test
	@Order(12)
	void testDAOexisteDNI() {
		TProfesor temp = new TProfesor(40,1,"80334444Z","De","Tigre",333399933,"editorial2@ucm.es",3500,false);
		TProfesor noestaAdded = new TProfesor(50,1,"AAAAAAAZ","De","Tigre",333399933,"editorial2@ucm.es",3500,false);
		assertEquals(true,FactoriaDAO.getInstance().generateDAOProfesor().existeDNI(temp));
		assertEquals(false,FactoriaDAO.getInstance().generateDAOProfesor().existeDNI(noestaAdded));
	}
	
	

}
