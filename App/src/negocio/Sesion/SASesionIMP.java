package negocio.Sesion;

import java.sql.Time;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import integracion.FactoriaDAO;
import integracion.Alumno.DAOAlumno;
import integracion.Profesor.DAOProfesor;
import integracion.Sesion.DAOSesion;
import negocio.Alumno.TAlumno;
import negocio.Profesor.TProfesor;

public class SASesionIMP implements SASesion {
	/*ERRORES: 
	 * -1: no existe 
	 * -2: elemento repetido
	 * -3: datos no validos (numeros negativos, matricula que no cumple el formato...)
	 * -4: error de la base de datos
	 * -5: otros errores desconocidos
	 * -6: la sesion ya estaba borrada
	 * * -7: no existe profesor
	 * * -8: no existe alumno
	 *  -9: profesor en otra sesion a esa hora y fecha
	 *  -10:alumno en otra sesion a esa hora y fecha
	 *  -11:horas con masl formato
	 *  -12:fecha con mal formato
	 * Si se devuelve un transfer con que se devuelva null ya vale
	 */
		
	@Override
	public int create(TSesion s) {
		DAOSesion dao = FactoriaDAO.getInstance().generateDAOSesion();
		if(!existeIDProfesor(s.getIdProfesor())) return -7;
		if(!existeIDAlumno(s.getIdAlumno())) return -8;
		if(s.getHoraini()==null || s.getHorafin()==null) return -11;
		if(s.getFecha()==null) return -12;
		if(s.getHorafin().getTime()-s.getHoraini().getTime()<0 ||s.getTipo().length()>150) return -3;
		if(dao.ocupadoProfesor(s)) return -9;
		if(dao.ocupadoAlumno(s)) return -10;
		int id=  dao.create(s);
		return id;
	}

	public boolean existeIDProfesor(int s) {
		DAOProfesor dao = FactoriaDAO.getInstance().generateDAOProfesor();
		TProfesor a = dao.read(s);
		int id=  dao.findByID(Integer.toString(s));
		if(id==1&& a.getActivo())return true;
		else return false;
	}

	public boolean existeIDAlumno(int s) {
		DAOAlumno dao = FactoriaDAO.getInstance().generateDAOAlumno();
		TAlumno a = dao.read(s);
		int id=  dao.findByID(Integer.toString(s));
		if(id==1&& a.getActivo())return true;
		else return false;
	}


	@Override
	public TSesion read(int id) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOSesion().read(id);
	}

	@Override
	public List<TSesion> readAllP(int id) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOSesion().readAllProfesor(id);
	}
	@Override
	public List<TSesion> readAllA(int id) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOSesion().readAllAlumno(id);
	}
	@Override
	public int update(TSesion s) {
		DAOSesion dao = FactoriaDAO.getInstance().generateDAOSesion();
		long duracionSesion = (s.getHorafin().getTime()-s.getHoraini().getTime());
		if(!existeIDProfesor(s.getIdProfesor())) return -7;
		if(!existeIDAlumno(s.getIdAlumno())) return -8;
		if(s.getHoraini()==null || s.getHorafin()==null) return -11;
		if(s.getFecha()==null) return -12;
		if(dao.ocupadoProfesor(s)) return -9;
		if(dao.ocupadoAlumno(s)) return -10;
		if(duracionSesion>0 ||s.getTipo().length()>150) return -3;
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
			Integer.parseInt(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	@Override
	public List<TSesion> readAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int findByID(int id) {
		// TODO Auto-generated method stub
		return 0;
	}
}