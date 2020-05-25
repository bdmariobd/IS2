package tIntegracion.Alumno;

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
import negocio.Alumno.TAlumno;
import negocio.Alumno.TRelleno;
import tAux.DBUtil;

@TestMethodOrder(OrderAnnotation.class)
public
class tAlumnoIntTest {
	/*
	 * public int create(TAlumno a);

	 * 
	 * public TAlumno read(int id);
	 * 
	 * public int findByID(String id);
	 * 
	 * public List<TAlumno> readAll();
	 * 
	 * public int update(TAlumno a);
	 * 
	 * public int delete(int id);
	 * 
	 * public int isDeleted(int id);
	 * 
	 * public int findByDNI(String dni);
	 * 
	 * public boolean existeDNI(String DNI);
	 * 
	 * public int rellenar(TRelleno r); 
	 * 
	 * public int getID(); 
	 * 
	 * public List<TRelleno> readAllR(int id);
	 */
	
	boolean compareLists(List<TRelleno> l1,List<TRelleno> l2) {
		if(l1.size()!=l2.size())return false;
		Iterator<TRelleno> it1= l1.iterator(), it2=l2.iterator();
		while(it1.hasNext()&&it2.hasNext()) {
			TRelleno t1=it1.next(), t2= it2.next();
			if(t1.getIdAlumno()!=t2.getIdAlumno()||t1.getIdTest()!=t2.getIdTest()||t1.getNumFallos()!=t2.getNumFallos())
				return false;
		}
		return true;
	}
	
	@BeforeAll
	public static void initSetUp() {
		//Vaciamos la tabla correspondiente.
		try {
			DBUtil.deleteTable("Alumno");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("/Se ha vaciado toda la tabla correspondiente a Alumno, iniciando test");
	}

	
	@Test
	@Order(1)
	void testDAOCreate() { 
		// En este metodo probaremos a insertar una consulta en la base de datos con el DAO de Alumno.
		// El test comparara el retorno esperado con la tabla vacia, en este caso, 1, siendo 1 la ID
		// del nuevo alumno insertado. Mete otro para comprobar la auto-incrementalidad de las ids
		TAlumno a = new TAlumno(0,"50638375Z","Arturo","Reverte",915439233,"editorial@ucm.es",true,true);
		TAlumno b = new TAlumno(0,"50334444Z","Federico","Garcia",915499933,"editorial2@ucm.es",false,false);
		assertEquals(1,FactoriaDAO.getInstance().generateDAOAlumno().create(a));
		assertEquals(2,FactoriaDAO.getInstance().generateDAOAlumno().create(b));
	}

	@Test
	@Order(2)
	void testDAOgetID() {
		// Devuelve la maxima ID que exista en la base de datos y le suma uno, si no hay nada devuelve 0+1.
		//Como ya se han metido dos alumnos, comprobare que me devuelva un 3.
		assertEquals(3,FactoriaDAO.getInstance().generateDAOAlumno().getID());
	}
	@Test
	@Order(3)
	void testDAORead() {
		//Este metodo comprueba que el alumno buscado sea igual al que se espera. En este
		//caso comprobamos el ID0; // El funcionamiento implica que readAll funciona.
		TAlumno t = FactoriaDAO.getInstance().generateDAOAlumno().read(1);
		assertEquals(t.getId(),1);
		assertEquals(t.getDNI(),"50638375Z");
		assertEquals(t.getEmail(),"editorial@ucm.es");
		assertEquals(t.getActivo(),true);
		assertEquals(t.getApellidos(),"Reverte");
		assertEquals(t.getAmaxofobia(),true);
		assertEquals(t.getNombre(),"Arturo");
		assertEquals(t.getTelefono(),915439233);
	}
	@Test
	@Order(4)
	void testDAOReadNull() {
		//Este metodo comprueba da nulo si busca uno que no existe. En este
		//caso comprobamos el ID 999;
		TAlumno t = FactoriaDAO.getInstance().generateDAOAlumno().read(999);
		assertEquals(null,t);
	}
	@Test
	@Order(5)
	void testDaofindByID() {
		//Como el read pero en vez de leer alumnos lee ids. Comprobamos el caso de que este y no este. (1,999)
		assertEquals(1,FactoriaDAO.getInstance().generateDAOAlumno().findByID("1"));
		assertEquals(-1,FactoriaDAO.getInstance().generateDAOAlumno().findByID("999"));
	}
	@Test
	@Order(6)
	void testDAOUpdate() {
		// Se comprobara que al id 2 se le cambia el apellido a Lorca. 
		TAlumno temp = new TAlumno(2,"50334444Z","Federico","Lorca",915499933,"editorial2@ucm.es",false,false);
		assertEquals(2,FactoriaDAO.getInstance().generateDAOAlumno().update(temp)); // Comprobamos que el update ha sido correcto
		TAlumno updated = FactoriaDAO.getInstance().generateDAOAlumno().read(2); // 
		assertEquals(updated.getApellidos(),temp.getApellidos());
	}
	@Test
	@Order(7)
	void testDAODelete() { 
		//Delete tiene una funcion logica, asi que establece el campo activo a 0;
		//Establecere el estado activo del id 1 a 0.
		assertEquals(1,FactoriaDAO.getInstance().generateDAOAlumno().delete(1));
		TAlumno updated = FactoriaDAO.getInstance().generateDAOAlumno().read(1); // 
		assertEquals(false,updated.getActivo());
	}
	@Test
	@Order(8)
	void testDAOisDeleted() {
		//Como ahora en mi base de datos ninguno esta activo, me meto un activo.
		TAlumno c = new TAlumno(0,"50638375Z","Arturo","Reverte",915439233,"editorial@ucm.es",true,true);
		assertEquals(3,FactoriaDAO.getInstance().generateDAOAlumno().create(c));
		// Alumno id 3 tiene campo activo a true
		assertEquals(0,FactoriaDAO.getInstance().generateDAOAlumno().isDeleted(3)); // Si devuelve 0, es que el campo esta activo.
		assertEquals(-1,FactoriaDAO.getInstance().generateDAOAlumno().isDeleted(9999)); // Pruebo que pasa si no existe el id que se le pasa.		
	}
	@Test
	@Order(9)
	void testDAOisDeletedAlready() {
		//Como ahora en mi base de datos ninguno esta activo, me meto un activo.
		
		// Alumno id 2 tiene campo activo a false
		assertEquals(-6,FactoriaDAO.getInstance().generateDAOAlumno().isDeleted(2)); // Si devuelve -6, es que el campo no lo esta.
	}
	@Test
	@Order(10)
	void testDAOrellenar() {
		// Me devuelve cantidaad de filas insertadas (1).
		TRelleno r1 = new TRelleno(1, 1, 1);
		TRelleno r2 = new TRelleno(1, 2, 2);
		assertEquals(1,FactoriaDAO.getInstance().generateDAOAlumno().rellenar(r1));
		assertEquals(1,FactoriaDAO.getInstance().generateDAOAlumno().rellenar(r2));
	}
	@Test
	@Order(11)
	void testDaofindByDNI() {
		//Como el read pero en vez de leer alumnos lee ids. Comprobamos el caso de que este y no este. (1,999)
		assertEquals(1,FactoriaDAO.getInstance().generateDAOAlumno().findByDNI("50638375Z"));
		assertEquals(-1,FactoriaDAO.getInstance().generateDAOAlumno().findByDNI("88888888Z"));
	}

	@Test
	@Order(12)
	void testDAOReadAll() {
		TRelleno r1 = new TRelleno(1, 1, 1);
		TRelleno r2 = new TRelleno(1, 2, 2);
		List<TRelleno> lista1= new ArrayList<TRelleno>();
		lista1.add(r1);
		lista1.add(r2);
		//Ya tengo una lista preparada para comparar.
		assertEquals(true,compareLists(lista1, FactoriaDAO.getInstance().generateDAOAlumno().readAllR(1)));
		// Alumno id 1 se supone que tiene dos test. Comprobamos.
	}
	
	
}
