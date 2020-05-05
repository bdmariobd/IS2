package negocio.Sucursal;

import java.util.List;

import integracion.FactoriaDAO;
import integracion.Sucursal.DAOSucursal;

public class SASucursalIMP implements SASucursal {
	/*ERRORES: 
	 * -1: no existe
	 * -2: elemento repetido
	 * -3: datos no validos (numeros negativos, matricula que no cumple el formato...)
	 * -4: error de la base de datos
	 * -5: otros errores desconocidos
	 * -6: el vehiculo ya estaba borrado
	 * Si se devuelve un transfer con que se devuelva null ya vale
	 */
		private boolean telefonoCorrecto(int m) {
			String x = Integer.toString(m);
			return (x.length()==9);
		}
	@Override
	public int create(TSucursal s) {
		// TODO comprobar que no hay matriculas repetidas y que la sucursal existe
		DAOSucursal dao = FactoriaDAO.getInstance().generateDAOSucursal();
		if(!telefonoCorrecto(s.getTelefono()) || s.getCiudad().length()>100 
				|| s.getDireccion().length()>150) return -3;
		int id=  dao.create(s);
		return id;
	}

	@Override
	public TSucursal read(int id) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOSucursal().read(id);
	}

	@Override
	public List<TSucursal> readAll() {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOSucursal().readAll();
	}

	@Override
	public int update(TSucursal s) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOSucursal().update(s);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		if(isNumeric(id)) {
			DAOSucursal dao = FactoriaDAO.getInstance().generateDAOSucursal();
			int deleted= dao.isDeleted(Integer.parseInt(id));
			if(deleted!=0) return deleted;
			int result = dao.delete(Integer.parseInt(id));
			return result;
		}
		else return -3;
		
	}

	private boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
}