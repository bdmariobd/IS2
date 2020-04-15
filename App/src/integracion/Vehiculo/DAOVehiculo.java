package integracion.Vehiculo;

import java.util.List;

import negocio.Vehiculo.TVehiculo;

public interface DAOVehiculo {
	public int create(TVehiculo v);

	public TVehiculo read(int id);

	public TVehiculo findByName(String nombre);

	public List<TVehiculo> readAll();

	public int update(TVehiculo v);

	public int delete(int id);
	
	public int regDamage(String[] datos);
}
