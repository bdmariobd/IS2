package negocio.Sesion;

import java.util.List;

import integracion.FactoriaDAO;
import integracion.Sesion.DAOSesion;

public class SASesionIMP implements SASesion {
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
	public int create(TSesion s) {
		// TODO comprobar que no hay matriculas repetidas y que la sucursal existe
		DAOSesion dao = FactoriaDAO.getInstance().generateDAOSesion();
		if(!telefonoCorrecto(s.getTelefono()) || s.getCiudad().length()>100 
				|| s.getDireccion().length()>150) return -3;
		int id=  dao.create(s);
		return id;
	}

	@Override
	public TSesion read(int id) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOSesion().read(id);
	}

	@Override
	public List<TSesion> readAll() {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOSesion().readAll();
	}

	@Override
	public int update(TSesion s) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOSesion().update(s);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		if(isNumeric(id)) {
			DAOSesion dao = FactoriaDAO.getInstance().generateDAOSesion();
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
	 public int findByID(int id) {
		 return FactoriaDAO.getInstance().generateDAOSesion().findbyID(id);
	 }
}