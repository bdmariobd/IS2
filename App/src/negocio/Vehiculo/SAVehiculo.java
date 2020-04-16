package negocio.Vehiculo;

import java.util.List;

public interface SAVehiculo {
	public int create(TVehiculo v);
	public TVehiculo read(int id);
	public List<TVehiculo> readAll();
	public int update(TVehiculo v);
	public int delete (int id);
	public int regDmg(String[] datos);
}