package negocio.Alumno;

import java.util.List;

import integracion.FactoriaDAO;
import integracion.Alumno.DAOAlumno;
import integracion.Test.DAOTest;
import negocio.Test.TTest;

public class SAalumnoImp implements SAalumno {

	@Override
	public int create(TAlumno a) {
		int id;
		DAOAlumno dao = FactoriaDAO.getInstance().generateDAOAlumno();
		if (datosIncorrectos(a))
			return -3;
		else if (dao.existeDNI(a.getDNI()))
			return -2;
		else
			id = dao.create(a);
		return id;
	}

	private boolean DNICorrecto(String DNI) {
		boolean dniCorrecto = false;
		if (DNI.length() == 9) {
			String numerosDNI = DNI.substring(0, DNI.length() - 1);
			try {
				Integer.parseInt(numerosDNI);
				try {
					Integer.parseInt(DNI);
				} catch (Exception e) {
					dniCorrecto = true;
				}
			} catch (Exception e) {
				dniCorrecto = false;
			}
		}
		return dniCorrecto;
	}

	private boolean telefonoCorrecto(int m) {
		String x = Integer.toString(m);
		return (x.length() == 9);
	}

	private boolean datosIncorrectos(TAlumno a) {
		return (!DNICorrecto(a.getDNI()) || a.getNombre().length() > 20 || a.getApellidos().length() > 20
				|| !telefonoCorrecto(a.getTelefono()) || a.getEmail().length() > 100);
	}

	@Override
	public TAlumno read(int id) {
		return FactoriaDAO.getInstance().generateDAOAlumno().read(id);
	}

	@Override
	public List<TAlumno> readAll() {
		return FactoriaDAO.getInstance().generateDAOAlumno().readAll();
	}

	@Override
	public int update(TAlumno a) {
		int id;
		DAOAlumno dao = FactoriaDAO.getInstance().generateDAOAlumno();

		if (datosIncorrectos(a))
			id = -3;
		else
			id = dao.update(a);
		return id;
	}

	@Override
	public int delete(String id) {
		if (isNumeric(id)) {
			DAOAlumno dao = FactoriaDAO.getInstance().generateDAOAlumno();
			int deleted = dao.isDeleted(Integer.parseInt(id));
			if (deleted != 0)
				return deleted;
			int result = dao.delete(Integer.parseInt(id));
			return result;
		} else
			return -3;
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
	public int findByName(String nombre) {
		return 0;
	}
	public int rellenar(TRelleno r) {
		if(!existeIDAlumno(r.getIdAlumno())) return -1;
		else if(!existeIDTest(r.getIdTest())) return -6;
		else if(!numF(r.getNumFallos(),r.getIdTest())) return -7;
		DAOAlumno dao = FactoriaDAO.getInstance().generateDAOAlumno();
		int id=  dao.rellenar(r);
		return id;
	}
	public boolean existeIDAlumno(int s) {
		DAOAlumno dao = FactoriaDAO.getInstance().generateDAOAlumno();
		TAlumno a = dao.read(s);
		int id=  dao.findByID(Integer.toString(s));
		if(id==1&& a.getActivo())return true;
		else return false;
	}
	public boolean existeIDTest(int s) {//
		DAOTest dao = FactoriaDAO.getInstance().generateDAOTest();
		TTest t= dao.read(s);
		int id=  dao.findbyID(s);
		if(id==1&& t.isActivo())return true;
		else return false;
	}
	public boolean numF(int fallos, int id) {
		DAOTest dao = FactoriaDAO.getInstance().generateDAOTest();
		int preguntas = dao.preguntas(id);
		if(fallos>preguntas)  return false;
		else return true;
	}
	@Override
	
	public List<TRelleno> readAllR(int id) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOAlumno().readAllR(id);
	}

}
