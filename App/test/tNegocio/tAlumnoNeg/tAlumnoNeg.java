package tNegocio.tAlumnoNeg;

import static org.junit.jupiter.api.Assertions.*;


import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;


import negocio.FactoriaSA;
import negocio.Alumno.TAlumno;
import negocio.Alumno.TRelleno;

import tAux.DBUtil;
@TestMethodOrder(OrderAnnotation.class)
class tAlumnoNeg {

	private TAlumno a =  new TAlumno(1,"30775986Z", "Paco", "Marhuenda" , 915439233, "junits@gmail.com", true, true); // ID 1
	private TAlumno b = new TAlumno(2,Integer.toString(getRandomDNI())+"Z", "M.", "Rajoy" , 913333333, "junits@gmail.com", false, false); // 2
	private TAlumno c =  new TAlumno(3,Integer.toString(getRandomDNI())+"Z", "Ortega", "Cano" , 94444233, "junits@gmail.com", true, true); // ID 3
	private TAlumno invaliddni = new TAlumno(0,"1X9//Z", "Paco", "Marhuenda" , 915439233, "junits@gmail.com", false, false); // dni invalido
	
	
	
	public int getRandomDNI()	{
		int x = (int) Math.floor(10000000 + Math.random() * 90000000);

		//System.out.println(x);
		
    return x;
	}
    public int getRandomTelf()	{
		int x = (int) Math.floor(100000000 + Math.random() * 900000000);
		//System.out.println(x);
		
    return x;
	}	
    @BeforeAll
	public static void initSetUp() {
		//Vaciamos la tabla correspondiente.
		try {
			DBUtil.deleteTable("Alumno");
			DBUtil.deleteTable("Test");
			DBUtil.deleteTable("Profesor");
			DBUtil.deleteTable("Sucursal");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
    
	@Test // Con la tabla vacia, meto un Alumno nuevo.
	@Order(1)
	public void testSACreate() {
		
		assertEquals(FactoriaSA.getInstance().generateSAalumno().create(a),1);
	}
	@Test
	@Order(2)
	public void test1() { // Prueba a insertar con un dni invalido
		assertEquals(FactoriaSA.getInstance().generateSAalumno().create(invaliddni),-3);
	}
	public void test2() { // Prueba a insertar con un dni ya existente, implicado que el primero tiene ese DNI
		assertEquals(FactoriaSA.getInstance().generateSAalumno().create(a),-2);
	}
	@Test //Read del alumno con ID =1. Comprobar que ID 1 y el dado son iguales.
	@Order(3)
	public void test3() {
		TAlumno t = FactoriaSA.getInstance().generateSAalumno().read(1);
		assertEquals(t.getId(),a.getId());
		assertEquals(t.getDNI(),a.getDNI());
		assertEquals(t.getEmail(),a.getEmail());
		assertEquals(t.getActivo(),a.getActivo());
		assertEquals(t.getApellidos(),a.getApellidos());
		assertEquals(t.getAmaxofobia(),a.getAmaxofobia());
		assertEquals(t.getNombre(),a.getNombre());
		assertEquals(t.getTelefono(),a.getTelefono());
	}
	@Test // Prueba al borrar un objeto ya desactivado, si la función devuelve -6, es que el delete con ese ID ya está desactivado.
	// Se prueba con el ID 2, implicando que existe desactivado.
	@Order(4)
	public void test4() { 
		// meter uno y borrar otro.
		FactoriaSA.getInstance().generateSAalumno().create(b); //alumno que se inserta desactivado
		assertEquals(-6,FactoriaSA.getInstance().generateSAalumno().delete("2"));
	}
	@Test
	@Order(5)
	public void test4_1(){ // Comprobar que no puedo borrar un alumno con sesiones pendientes.
		DBUtil.addSomething("INSERT INTO `Sucursal` (`id`, `ciudad`, `telefono`, `direccion`, `activo`) VALUES ('1', 'Murcia', '918877331', 'Calle del Limon de Don Salva Espin', '1')");
		DBUtil.addSomething("INSERT INTO `Profesor` (`id`, `idSucursal`, `DNI`, `nombre`, `apellidos`, `telefono`, `email`, `sueldo`, `activo`) VALUES ('1', '1', '1', 'Mario', 'Sempai', '915466783', 'junits@gmail.com', '2500', '1')");
		DBUtil.addSomething("INSERT INTO `Sesion` (`id`, `fecha`, `horaini`, `horafin`, `tipo`, `activo`, `idAlumno`, `idProfesor`) VALUES ('1', '2020-06-22', '9:00', '10:30', 'Permiso B', '1', '1', '1');"); // Se le ha metido una sesion activa al Alumno 1.
		// Si no aseguro que los datos esten bien, error de claves externas.
		assertEquals(-7,FactoriaSA.getInstance().generateSAalumno().delete("1"));
		}
	@Test // Intenta desactivar un objeto que no existe, se prueba con id 99 implicando que no existe.
	@Order(6)
	public void test5() { 
		
		assertEquals(-1,FactoriaSA.getInstance().generateSAalumno().delete("99"));
	}
	@Test 
	@Order(7)// Prueba al borrar un objeto que existe en la base de datos y está activo y no tiene sesiones activas, debe volver la ID del objeto
	public void test6() { 
		DBUtil.addSomething("Insert INTO Alumno  VALUES ('3','33334333Z','Agente','Pdro',555555555,'junits@gmail.com','1','1');"); //ID3
		assertEquals(3, FactoriaSA.getInstance().generateSAalumno().delete("3"));
		
	}
	@Test // Comprueba las funcion update con datos incorrectos.
	@Order(8)
	public void test7() {
		TAlumno a = new TAlumno(0,"XX//232232323Z", "PacoOOOOOOOOOOOOOOOOO", "Marhuenda" , 943, "junits@gmail.com", true, true);
		assertEquals(-3,FactoriaSA.getInstance().generateSAalumno().update(a));
	}
	@Test // Comprueba las funcion update cambiando el telf del ID 1 por un numero aleatorio de 9 cifras.
	@Order(9)
	public void test8() {
		TAlumno a = new TAlumno(1,"50638375Z", "Paco", "Marhuenda" , getRandomTelf(), "junits@gmail.com", true, true);
		assertEquals(1,FactoriaSA.getInstance().generateSAalumno().update(a));
	}
	@Test // Comprobar funcion rellenar con un transfer relleno, prueba con alumno inexistente.
	// -1 si no existe alumno. Implica el uso de un test existente.
	@Order(10)
	public void test9() {
		TRelleno r = new TRelleno(999,1,5);
		assertEquals(-1,FactoriaSA.getInstance().generateSAalumno().rellenar(r));
	}
	@Test // Comprobar funcion rellenar con un transfer relleno, prueba con test inexistente.
	// -6 si no existe test .
	@Order(11)
	public void test10() {
		TRelleno r = new TRelleno(1,999,5); 
		assertEquals(-6,FactoriaSA.getInstance().generateSAalumno().rellenar(r));
	}
	@Test // Comprobar funcion rellenar con un transfer relleno, prueba muchos fallos y ids validas.
	// -7 si hay mas fallos que preguntas.
	@Order(12)
	public void test11() {
		DBUtil.addSomething("Insert INTO Test VALUES (1,'Permiso B',30,1);");
		TRelleno r = new TRelleno(1,1,999);
		assertEquals(-7,FactoriaSA.getInstance().generateSAalumno().rellenar(r));
	}
	@Test // Comprobar funcion rellenar con un transfer relleno, prueba con datos correctos
	@Order(13)
	public void test12() {
		DBUtil.addSomething("Insert INTO Test VALUES (2,'Permiso B',70,1);");
		TRelleno r = new TRelleno(1,2,5);
		assertEquals(1,FactoriaSA.getInstance().generateSAalumno().rellenar(r));
	}
	

}
