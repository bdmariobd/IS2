package tIntegracion.Sesion;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import integracion.FactoriaDAO;
import integracion.Sesion.DAOSesion;
import negocio.Sesion.TSesion;
import resources.fechasConverter;
import tAux.DBUtil;

@TestMethodOrder(OrderAnnotation.class)
public
class tSesionIntTest {
/*
public int create(TSesion ses);
    public TSesion read(int id);
    public List<TSesion> readAll();
    public int update(TSesion ses);
    public int delete (String id);
    public int findByID(int id);
	public List<TSesion> readAllA(int id);
	public List<TSesion> readAllP(int id);
 */

	//public TSesion(int id, Date fecha, Date horaini,Date horafin, String tipo ,boolean activo, int idAlumno, int idProfesor) {

	private DAOSesion dao= FactoriaDAO.getInstance().generateDAOSesion();
	public boolean equals(TSesion s1, TSesion s2) {
		if((s1==null)!=(s2==null))return false;
		return s1.getId()==s2.getId() && s1.getFecha().equals(s2.getFecha()) && s1.getHoraini().equals(s2.getHoraini()) &&
				s1.getHorafin().equals(s2.getHorafin()) && s1.getTipo().equals(s2.getTipo()) && s1.isActivo()==s2.isActivo()&&
				s1.getIdAlumno()==s2.getIdAlumno()&&s1.getIdProfesor()==s2.getIdProfesor();
	}
	@BeforeAll
	public static void initSetUp() {
		//Vaciamos la tabla correspondiente.
		try {
			DBUtil.deleteTable("Sesion");
			DBUtil.deleteTable("Profesor");
			DBUtil.deleteTable("Alumno");
			//Se insertan unos cuantos alumnos y profesores para probar
			DBUtil.addSomething("INSERT into Alumno VALUES (1,'12345678Q','Eufalio','Macetero',912465867,'xxGamer@gmail.com',0,1)");
			DBUtil.addSomething("INSERT into Alumno VALUES (2,'12345678W','Jacobo','Booleano',912345456,'sanJacobo@ucm.com',1,1)");
			DBUtil.addSomething("INSERT into Alumno VALUES (3,'12345678R','Clara','Pecas',913947586,'clariPKS@gmail.com',0,1)");
			//No necesito añadir sucursal para profesor ya que no afecta para sesion. Dejo en id=0
			DBUtil.addSomething("INSERT into Profesor VALUES(1,0,'46573898D','Maria Teresa','Moreno','684938284','mtm@apm.es',1400,1)");
			DBUtil.addSomething("INSERT into Profesor VALUES(2,0,'85463906G','Susana','Grisso','912345649','susg@apm.es',1400,1)");
			DBUtil.addSomething("INSERT into Profesor VALUES(3,0,'45645654N','Paul','Giroud','678987789','paulg@apm.es',1400,1)");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	@Order(1)
	void testDAOCreate() {
		TSesion s1 = new TSesion(0,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("12:00"),
				fechasConverter.StringHoraToDate("13:30"),"Permiso B Practica",true,1,1);
		TSesion s2 = new TSesion(0,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("13:30"),
				fechasConverter.StringHoraToDate("15:00"),"Permiso B Teoria",true,1,2);
		TSesion s3 = new TSesion(0,fechasConverter.StringFechaToDate("2020-05-16"),fechasConverter.StringHoraToDate("12:00"),
				fechasConverter.StringHoraToDate("13:30"),"Permiso B Practica",true,2,1);
		assertEquals(1,dao.create(s1));
		assertEquals(2,dao.create(s2));
		assertEquals(3,dao.create(s3));
	}
	@Test
	@Order(2)
	void testDAORead() { //leo las sesiones insertadas antes
		TSesion s1 = new TSesion(1,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("12:00"),
				fechasConverter.StringHoraToDate("13:30"),"Permiso B Practica",true,1,1);
		TSesion s2 = new TSesion(2,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("13:30"),
				fechasConverter.StringHoraToDate("15:00"),"Permiso B Teoria",true,1,2);
		TSesion s3 = new TSesion(3,fechasConverter.StringFechaToDate("2020-05-16"),fechasConverter.StringHoraToDate("12:00"),
				fechasConverter.StringHoraToDate("13:30"),"Permiso B Practica",true,2,1);
		assertEquals(true,equals(s1,dao.read(1)));
		assertEquals(true,equals(s2,dao.read(2)));
		assertEquals(true,equals(s3,dao.read(3)));
		assertEquals(null,dao.read(4));//leo uno que no esta
	}
	@Test
	@Order(3)
	void testDAOFindById() {
		assertEquals(1,dao.findByID("1"));
		assertEquals(-1,dao.findByID("5"));
	}
	@Test
	@Order(4)
	void testDAOUpdate() {
		TSesion s1 = new TSesion(1,fechasConverter.StringFechaToDate("2019-05-15"),fechasConverter.StringHoraToDate("12:00"),
				fechasConverter.StringHoraToDate("13:30"),"Permiso B Teoria",true,1,1);
		assertEquals(1,dao.update(s1));
		assertEquals(true,equals(s1,dao.read(1)));//leo en la BDD para ver si se ha guardado el cambio de año y el tipo de la clase
	}
	@Test
	@Order(5)
	void testDAODelete() { //se borra la sesion 1
		assertEquals(1,dao.delete(1));
		assertEquals(false,dao.read(1).isActivo()); //se comprueba si se ha guardado el cambio
	}
	@Test
	@Order(6)
	void testDAOisDeleted(){
		assertEquals(-6,dao.isDeleted(1)); //se vuelve a comprobar si esta borrao
	}
	@Test
	@Order(7)
	void testDAOOcupados() {  
		//esta no se podria usar, ya que estan ocupados
		TSesion s2 = new TSesion(0,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("14:00"),
				fechasConverter.StringHoraToDate("15:30"),"Permiso B Teoria",true,1,2); 
		assertEquals(true,dao.ocupadoAlumno(s2) && dao.ocupadoProfesor(s2));
		//esta si se podria usar, porque tienen una a la misma hora pero el dia 16 en vez del 17
		TSesion s3 = new TSesion(0,fechasConverter.StringFechaToDate("2020-05-17"),fechasConverter.StringHoraToDate("12:00"),
				fechasConverter.StringHoraToDate("13:30"),"Permiso B Practica",true,2,1);
		assertEquals(false,dao.ocupadoAlumno(s3) && dao.ocupadoProfesor(s3));

	}
}
