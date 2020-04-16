package negocio.Vehiculo;

import java.util.List;

import integracion.FactoriaDAO;
import integracion.Vehiculo.DAOVehiculo;

public class SAVehiculoImp implements SAVehiculo {
	/*ERRORES: 
	 * -1: no existe
	 * -2: elemento repetido
	 * -3: datos no validos (numeros negativos, matricula que no cumple el formato...)
	 * -4: error de la base de datos
	 * -5: otros errores desconocidos
	 * -6: el vehiculo ya estaba borrado
	 * Si se devuelve un transfer con que se devuelva null ya vale
	 */
	@Override
	public int create(TVehiculo v) {
		// TODO comprobar que no hay matriculas repetidas y que la sucursal existe
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		if(v.getMatricula().length()>10 || v.getDaños().length()>300 || v.getIdSucursal()>1000000000 
				|| v.getTipo().length()>20) return -3;
		int aux= dao.findByName(v.getMatricula());
		if (aux!=0) return aux;
		int id=  dao.create(v);
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
		int deleted= dao.isDeleted(id);
		if(deleted!=0) return deleted;
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
