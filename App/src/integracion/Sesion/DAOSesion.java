package integracion.Sesion;

import java.util.List;

import negocio.Sesion.TSesion;;

public interface DAOSesion {
	public int create(TSesion a);

	public TSesion read(int id);

	public int findByID(String id);

	public List<TSesion> readAll();

	public int update(TSesion a);

	public int delete(int id);
	
	public int isDeleted(int id);
}
