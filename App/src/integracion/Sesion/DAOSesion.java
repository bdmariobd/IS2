package integracion.Sesion;

import java.util.Date;
import java.util.List;

import negocio.Sesion.TSesion;;

public interface DAOSesion {
	public int create(TSesion a);

	public TSesion read(int id);

	public int findByID(String id);

	public int update(TSesion a);

	public int delete(int id);
	
	public int isDeleted(int id);

	public List<TSesion> readAllProfesor(int id);
	
	public List<TSesion> readAllAlumno(int id);

	public boolean ocupadoProfesor(TSesion ses);

	public boolean ocupadoAlumno(TSesion ses);
}
