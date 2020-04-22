package integracion.Alumno;

import java.util.List;

import negocio.Alumno.TAlumno;;

public interface DAOAlumno {
	public int create(TAlumno a);

	public TAlumno read(int id);

	public int findByName(String nombre);

	public List<TAlumno> readAll();

	public int update(TAlumno a);

	public int delete(int id);
	
	public int isDeleted(int id);
}
