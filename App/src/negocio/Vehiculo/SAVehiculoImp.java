package negocio.Vehiculo;

import java.util.List;

import integracion.FactoriaDAO;
import integracion.Vehiculo.DAOVehiculo;

public class SAVehiculoImp implements SAVehiculo {

	@Override
	public int create(TVehiculo v) {
		// TODO Auto-generated method stub
		int id=-1;
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		id=  dao.create(v);
		return id;
	}

	@Override
	public TVehiculo read(int id) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOVehiculo().read(id);
	}

	@Override
	public List<TVehiculo> readAll() {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOVehiculo().readAll();
	}

	@Override
	public int update(TVehiculo v) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOVehiculo().update(v);
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		int result = dao.delete(id);
		return result;
	}

	@Override
	public int regDmg(String[] datos) {
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		int result = dao.regDamage(datos);
		return result;
	}

}
