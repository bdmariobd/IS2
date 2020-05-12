package negocio.Alumno;

import java.util.List;

public interface SAalumno {
	public int create(TAlumno a);
	
	public TAlumno read(int id);
	
	public List<TAlumno> readAll();
	
	public int update(TAlumno a);
	
	public int delete (String id);
	
	public int findByName(String nombre);
	
	public int rellenar(TRelleno r);
	public boolean existeIDAlumno(int s);
	public boolean existeIDTest(int s);
	public boolean numF(int fallos, int preguntas);
	public List<TRelleno> readAllR(int id);
	
}
