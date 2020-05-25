package tIntegracion.Sucursal;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import integracion.FactoriaDAO;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import negocio.Sucursal.TSucursal;
import tAux.DBUtil;

@TestMethodOrder(OrderAnnotation.class)
public

class tSucursalIntTest {
		
		boolean compareLists(List<TSucursal> l1,List<TSucursal> l2) {
			if(l1.size()!=l2.size())return false;
			Iterator<TSucursal> it1= l1.iterator(), it2=l2.iterator();
			while(it1.hasNext()&&it2.hasNext()) {
				TSucursal t1=it1.next(), t2= it2.next();
				if(t1.getId()!=t2.getId()||t1.getCiudad()!=t2.getCiudad()||t1.getTelefono()!=t2.getTelefono()||t1.getDireccion()!=t2.getDireccion()||t1.isActivo()!=t2.isActivo()) return false;
			}
			return true;
		}
		
		@BeforeAll
		public static void initSetUp() {
			System.out.println("Iniciando preparaciones previas...");
			try {
				DBUtil.deleteTable("Sucursal");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			System.out.println("Se ha vaciado toda la tabla correspondiente a Sucursal, iniciando test");
		}
		@Test
		@Order(1)
		void testDAOCreate() { 
			TSucursal a = new TSucursal(1,"Murcia",919199191,"Calle del Limon de Don Salva Espin",true);
			TSucursal b = new TSucursal(2,"Madrid",999999999,"Nuñez de Balboa, N9 ",false);
			assertEquals(1,FactoriaDAO.getInstance().generateDAOSucursal().create(a));
			assertEquals(2,FactoriaDAO.getInstance().generateDAOSucursal().create(b));
		}
		@Test
		@Order(2)
		void testDAORead() {
			//Este metodo comprueba que el alumno buscado sea igual al que se espera. En este
			//caso comprobamos el ID0; // El funcionamiento implica que readAll funciona.
			TSucursal a = new TSucursal(1,"Murcia",919199191,"Calle del Limon de Don Salva Espin",true);
			TSucursal t = FactoriaDAO.getInstance().generateDAOSucursal().read(1);
			assertEquals(true ,(a.getId()==t.getId()||a.getCiudad()==t.getCiudad()||a.getTelefono()==t.getTelefono()||a.getDireccion()==t.getDireccion()||a.isActivo()==t.isActivo()));
		}
		@Test
		@Order(3)
		void testDAOReadNull() {
			TSucursal t = FactoriaDAO.getInstance().generateDAOSucursal().read(999);
			assertEquals(null,t);
		}
		@Test
		@Order(4)
		void testDaofindByID() {
			assertEquals(1,FactoriaDAO.getInstance().generateDAOSucursal().findbyID(1));
			assertEquals(-1,FactoriaDAO.getInstance().generateDAOSucursal().findbyID(999));
		}
		@Test
		@Order(5)
		void testDAOUpdate() {
			TSucursal temp = new TSucursal(2,"Madrid",999999999,"Isabelita Usera, N9 ",false);
			assertEquals(2,FactoriaDAO.getInstance().generateDAOSucursal().update(temp)); // Comprobamos que el update ha sido correcto
			TSucursal updated = FactoriaDAO.getInstance().generateDAOSucursal().read(2); // 
			assertEquals(updated.getDireccion(),temp.getDireccion());
		}
		@Test
		@Order(6)
		void testDAODelete() { 
			
		}
		@Test
		@Order(7)
		void testDAOisDeleted() {
				
		}
		
				
}


