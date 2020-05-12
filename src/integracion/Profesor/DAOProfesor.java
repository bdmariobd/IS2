package integracion.Profesor;

import java.util.List;

import negocio.Profesor.TProfesor;;

public interface DAOProfesor {
	public int create(TProfesor a);

	public TProfesor read(int id);

	public int findByID(String id);

	public List<TProfesor> readAll();

	public int update(TProfesor a);

	public int delete(int id);
	
	public int isDeleted(int id);

	public boolean existeIdSucursal(int idSucursal);

	public boolean existeDNI(String dni);
}
