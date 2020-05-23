package tNegocio.Sesion;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestMethodOrder;
import negocio.FactoriaSA;
import negocio.Profesor.TProfesor;
import negocio.Sesion.SASesion;
import negocio.Sesion.TSesion;
import negocio.Vehiculo.SAVehiculo;
import negocio.Vehiculo.TVehProf;
import negocio.Vehiculo.TVehiculo;
import resources.fechasConverter;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import tAux.DBUtil;

@TestMethodOrder(OrderAnnotation.class)
public class tSesionNeg {
/*
 * public int create(TSesion ses);
    public TSesion read(int id);
    public List<TSesion> readAll();
    public int update(TSesion ses);
    public int delete (String id);
    public int findByID(int id);
	public List<TSesion> readAllA(int id);
	public List<TSesion> readAllP(int id);
	*ERRORES: 
	 * -1: no existe 
	 * -2: elemento repetido
	 * -3: datos no validos (numeros negativos, matricula que no cumple el formato...)
	 * -4: error de la base de datos
	 * -5: otros errores desconocidos
	 * -6: la sesion ya estaba borrada
	 * * -7: no existe profesor
	 * * -8: no existe alumno
	 *  -9: profesor en otra sesion a esa hora y fecha
	 *  -10:alumno en otra sesion a esa hora y fecha
	 *  -11:horas con masl formato
	 *  -12:fecha con mal formato
	 * Si se devuelve un transfer con que se devuelva null ya vale
 */
	//public TSesion(int id, Date fecha, Date horaini,Date horafin, String tipo ,boolean activo, int idAlumno, int idProfesor) 
	private SASesion sa = FactoriaSA.getInstance().generateSASesion();
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
	void testSACreate() {
		assertEquals(-7,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,1,4)));
		
		assertEquals(-8,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,4,1)));

		assertEquals(-11,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11-45"),"Practica",true,1,1)));

		assertEquals(-12,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("15 de marzo de 2020"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,1,1)));

		assertEquals(-3,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:45"),
				fechasConverter.StringHoraToDate("11:00"),"Practica",true,1,1)));
		
		assertEquals(1,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,1,1)));

		assertEquals(2,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:46"),
				fechasConverter.StringHoraToDate("12:30"),"Practica",true,2,1)));

		assertEquals(3,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("2020-05-16"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,1,1)));

		assertEquals(4,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("2020-05-17"),fechasConverter.StringHoraToDate("09:00"),
				fechasConverter.StringHoraToDate("13:00"),"Teorica intensivo",true,3,2)));
		
		//profesor ocupado por la clase id=2
		assertEquals(-9,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("12:30"),
				fechasConverter.StringHoraToDate("13:15"),"Practica",true,3,1)));
		
		 //alumno ocupado por la clase id=3
		assertEquals(-10,sa.create(new TSesion(0,fechasConverter.StringFechaToDate("2020-05-16"),fechasConverter.StringHoraToDate("10:30"),
				fechasConverter.StringHoraToDate("11:15"),"Practica",true,1,2)));
	}
	
	@Test
	@Order(2)
	void testSARead() {
		assertEquals(true, equals(new TSesion(1,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,1,1), sa.read(1)));
		assertEquals(true, equals(new TSesion(3,fechasConverter.StringFechaToDate("2020-05-16"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,1,1), sa.read(3)));
		assertEquals(true, equals(new TSesion(4,fechasConverter.StringFechaToDate("2020-05-17"),fechasConverter.StringHoraToDate("09:00"),
				fechasConverter.StringHoraToDate("13:00"),"Teorica intensivo",true,3,2), sa.read(4)));
		assertEquals(null, sa.read(5));
	}
	@Test
	@Order(3)
	void testSAUpdate() {
		assertEquals(-7,sa.update(new TSesion(1,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,1,4)));
		
		assertEquals(-8,sa.update(new TSesion(1,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,4,1)));

		assertEquals(-11,sa.update(new TSesion(1,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11-45"),"Practica",true,1,1)));

		assertEquals(-12,sa.update(new TSesion(1,fechasConverter.StringFechaToDate("15 de marzo de 2020"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,1,1)));

		assertEquals(-3,sa.update(new TSesion(1,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:45"),
				fechasConverter.StringHoraToDate("11:00"),"Practica",true,1,1)));
		
		//2 updates validos
		assertEquals(4,sa.update(new TSesion(4,fechasConverter.StringFechaToDate("2020-05-17"),fechasConverter.StringHoraToDate("09:00"),
				fechasConverter.StringHoraToDate("13:00"),"Intensivo A2",true,3,2)));
		assertEquals(true,sa.read(4).getTipo().equals("Intensivo A2"));
		
		TSesion s = new TSesion(2,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("11:50"),
				fechasConverter.StringHoraToDate("12:30"),"Practica",true,3,1);
		assertEquals(2,sa.update(s));
		assertEquals(true, equals(s,sa.read(2)));
		
		//profesor ocupado por la clase id=2
		assertEquals(-9,sa.create(new TSesion(1,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("12:00"),
				fechasConverter.StringHoraToDate("12:45"),"Practica",true,1,1)));
		
		 //alumno ocupado por la clase id=3
		assertEquals(-10,sa.create(new TSesion(4,fechasConverter.StringFechaToDate("2020-05-15"),fechasConverter.StringHoraToDate("09:00"),
				fechasConverter.StringHoraToDate("13:00"),"Intensivo A2",true,3,2)));
	}
	//public TSesion(int id, Date fecha, Date horaini,Date horafin, String tipo ,boolean activo, int idAlumno, int idProfesor)
	@Test
	@Order(4)
	void testSADelete() {
		assertEquals(-1,sa.delete("7"));
		assertEquals(3,sa.delete("3"));
		assertEquals(false,sa.read(3).isActivo());
		assertEquals(-6,sa.delete("3"));		
	}
	@Test
	@Order(5)
	void testReactiva() {
		TSesion s3 = new TSesion(3,fechasConverter.StringFechaToDate("2020-05-16"),fechasConverter.StringHoraToDate("11:00"),
				fechasConverter.StringHoraToDate("11:45"),"Practica",true,1,1);
		assertEquals(true,sa.read(sa.update(s3)).isActivo());
	}
}
