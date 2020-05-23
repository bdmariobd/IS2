package negocio.Vehiculo;

import java.util.List;

import integracion.FactoriaDAO;
import integracion.Profesor.DAOProfesor;
import integracion.Vehiculo.DAOVehiculo;
import negocio.FactoriaSA;

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
	private boolean valid(char c) {
		return Character.isLetter(c) && c!='A' && c!='E' &&c!='I' &&c!='O' && c!='U';
	}
	private boolean matriculaCorrecta(String m) {
        if(m.length()!=7) return false;
        if(!m.substring(0, 4).chars().allMatch(Character::isDigit)) return false;
        String letters = m.substring(4, 7);
        for(int i=0;i<letters.length();++i) {
        	if(!valid(letters.charAt(i)))return false;
        }
        return true;
    }
	@Override
	public int create(TVehiculo v) {
		// TODO comprobar que no hay matriculas repetidas y que la sucursal existe
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		if(!matriculaCorrecta(v.getMatricula()) || v.getDaños().length()>300 || v.getIdSucursal()>1000000000 
			|| v.getTipo().length()>20 || FactoriaSA.getInstance().generateSASucursal().findByID(v.getIdSucursal()) !=1 ) return -3;
		int aux= dao.findByName(v);
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
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		if(!matriculaCorrecta(v.getMatricula()) || v.getDaños().length()>300 || v.getIdSucursal()>1000000000 
				|| v.getTipo().length()>20 || FactoriaSA.getInstance().generateSASucursal().findByID(v.getIdSucursal()) !=1 ) return -3;
		int aux= dao.findByName(v);
		if (aux!=0) return aux;
		return dao.update(v);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		if(isNumeric(id)) {
			DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
			int deleted= dao.isDeleted(Integer.parseInt(id));
			if(deleted!=0) return deleted;
			int result = dao.delete(Integer.parseInt(id));
			return result;
		}
		else return -3;
		
	}
	
	@Override
	public int regDmg(String[] datos) {
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		int result = dao.regDamage(datos);
		return result;
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
	private boolean vehAvailable(int idV) { // comprobar que el vehiculo NO tiene
		// un profesor es comprobar que el vehiculo NO está en la tabla.
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		if(dao.vehExist(idV)) return false;
		return true;
		
		
	}
	private boolean vehActivo(int id) {
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		if(dao.isDeleted(id)==0) return true;
		return false;
	}
	private boolean profActivo(int id) {
		DAOProfesor dao = FactoriaDAO.getInstance().generateDAOProfesor();
		if(dao.isDeleted(id)==0) return true;
		return false;
	}
	
	
	private boolean vehExist(int idV) {
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		if(dao.findbyID(String.valueOf(idV)) == 1) return true;
		return false;
	}
	private boolean profExist(int idP) {
		
		DAOProfesor dao = FactoriaDAO.getInstance().generateDAOProfesor();
		if(dao.existeID(String.valueOf(idP))) return true;
		return false;
	}
	@Override
	public int asigVehProf(TVehProf datos) {
		int result=-2; // Por defecto supongo.
		DAOVehiculo dao = FactoriaDAO.getInstance().generateDAOVehiculo();
		if(vehExist(datos.getIDV()) && profExist(datos.getIDP()) && vehActivo(datos.getIDV()) 
				&& profActivo(datos.getIDP())) {
			if(vehAvailable(datos.getIDV())) {
				result = dao.asignarVehProf(datos);
			}
		}
		return result;
		
	}
	
}
