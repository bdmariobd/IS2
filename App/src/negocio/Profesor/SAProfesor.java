package negocio.Profesor;

import java.util.List;

public interface SAProfesor {
	public int create(TProfesor a);
	
	public TProfesor read(int id);
	
	public List<TProfesor> readAll();
	
	public int update(TProfesor a);
	
	public int delete (String id);
	
	public int findByName(String nombre);
	
}
