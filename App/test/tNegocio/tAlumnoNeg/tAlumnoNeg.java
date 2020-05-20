package tNegocio.tAlumnoNeg;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import integracion.FactoriaDAO;
import negocio.FactoriaSA;
import negocio.Alumno.TAlumno;
import negocio.Alumno.TRelleno;
import tAux.DBUtil;

class tAlumnoNeg {

	public int getRandomDNI()	{
		int x = (int) Math.floor(10000000 + Math.random() * 90000000);

		System.out.println(x);
		
    return x;
	}
    public int getRandomTelf()	{
		int x = (int) Math.floor(100000000 + Math.random() * 900000000);
		System.out.println(x);
		
    return x;
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
		System.out.println("Se ha vaciado toda la tabla correspondiente a Alumno, iniciando test");
	}
    
	@Test // Con la tabla vacia, meto un Alumno nuevo.
	@Order(1)
	public void testSACreate() {
		TAlumno transfer = new TAlumno(0,Integer.toString(getRandomDNI())+"Z", "Paco", "Marhuenda" , 915439233, "junits@gmail.com", false, true);
		assertEquals(FactoriaSA.getInstance().generateSAalumno().create(transfer),0);
	}
	@Test
	@Order(2)
	public void test1() { // Prueba a insertar con un dni invalido
		TAlumno transfer = new TAlumno(0,"1X9//Z", "Paco", "Marhuenda" , 915439233, "junits@gmail.com", false, false);
		assertEquals(FactoriaSA.getInstance().generateSAalumno().create(transfer),-3);
		
	}
	public void test2() { // Prueba a insertar con un dni ya existente, implicado que el primero tiene ese DNI
		TAlumno transfer = new TAlumno(0,"50638375Z", "Paco", "Marhuenda" , 915439233, "junits@gmail.com", false, false);
		assertEquals(FactoriaSA.getInstance().generateSAalumno().create(transfer),-2);
		
	}
	@Test //Comprobar que ID 6 y el dado son iguales.
	public void test3() {
		TAlumno t = FactoriaSA.getInstance().generateSAalumno().read(6);
		TAlumno a = new TAlumno(6,"71299544Z", "Paco", "Marhuenda" , 915439233, "junits@gmail.com", false, false);
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
	// Se prueba con el ID 3, implicando que existe desactivado.
	public void test4() { 
		// meter uno y borrar otro.
		assertEquals(-6,FactoriaSA.getInstance().generateSAalumno().delete("3"));
	}
	@Test // Intenta desactivar un objeto que no existe, se prueba con id 99 implicando que no existe.
	public void test5() { 
		assertEquals(-1,FactoriaSA.getInstance().generateSAalumno().delete("99"));
	}
	@Test  // Prueba al borrar un objeto que existe en la base de datos y está activo, debe volver la ID del objeto
	public void test6() { 
		assertEquals(6, FactoriaSA.getInstance().generateSAalumno().delete("6"));
		// cada vez que se usa esta funcion el siguiente test dará fallo
	}
	@Test // Comprueba las funcion update con datos incorrectos.
	public void test7() {
		TAlumno a = new TAlumno(0,"XX//232232323Z", "PacoOOOOOOOOOOOOOOOOO", "Marhuenda" , 943, "junits@gmail.com", true, true);
		assertEquals(-3,FactoriaSA.getInstance().generateSAalumno().update(a));
	}
	@Test // Comprueba las funcion update cambiando el telf del ID 1 por un numero aleatorio de 9 cifras.
	public void test8() {
		TAlumno a = new TAlumno(1,"50638375Z", "Paco", "Marhuenda" , getRandomTelf(), "junits@gmail.com", true, true);
		assertEquals(1,FactoriaSA.getInstance().generateSAalumno().update(a));
	}
	@Test // Comprobar funcion rellenar con un transfer relleno, prueba con alumno inexistente.
	// -1 si no existe alumno. Implica el uso de un test existente.
	public void test9() {
		TRelleno r = new TRelleno(999,1,5);
		assertEquals(-1,FactoriaSA.getInstance().generateSAalumno().rellenar(r));
	}
	@Test // Comprobar funcion rellenar con un transfer relleno, prueba con test inexistente.
	// -6 si no existe test .
	public void test10() {
		TRelleno r = new TRelleno(1,999,5); 
		assertEquals(-6,FactoriaSA.getInstance().generateSAalumno().rellenar(r));
	}
	@Test // Comprobar funcion rellenar con un transfer relleno, prueba muchos fallos y ids validas.
	// -7 si hay mas fallos que preguntas.
	public void test11() {
		TRelleno r = new TRelleno(1,8,999);
		assertEquals(-7,FactoriaSA.getInstance().generateSAalumno().rellenar(r));
	}
	@Test // Comprobar funcion rellenar con un transfer relleno, prueba con datos correctos
	public void test12() {
		TRelleno r = new TRelleno(1,8,5);
		assertEquals(1,FactoriaSA.getInstance().generateSAalumno().rellenar(r));
	}
	

}
