package negocio.Profesor;

import java.util.List;

import integracion.FactoriaDAO;
import integracion.Profesor.DAOProfesor;

public class SAProfesorImp implements SAProfesor {

	@Override
	public int create(TProfesor a) {
		int id;
		DAOProfesor dao = FactoriaDAO.getInstance().generateDAOProfesor();

		if (datosIncorrectos(a))
			id = -3;
		else if (!dao.existeIdSucursal(a.getIdSucursal()))
			id = -6;
		else
			id = dao.create(a);

		return id;
	}

	@Override
	public TProfesor read(int id) {
		return FactoriaDAO.getInstance().generateDAOProfesor().read(id);
	}

	@Override
	public List<TProfesor> readAll() {
		return FactoriaDAO.getInstance().generateDAOProfesor().readAll();
	}

	@Override
	public int update(TProfesor a) {
		int id;
		DAOProfesor dao = FactoriaDAO.getInstance().generateDAOProfesor();

		if (datosIncorrectos(a))
			id = -3;
		else if (!dao.existeIdSucursal(a.getIdSucursal()))
			id = -6;
		else
			id = dao.update(a);
		return id;
	}

	private boolean telefonoCorrecto(int m) {
		String x = Integer.toString(m);
		return (x.length() == 9);
	}
	private boolean datosIncorrectos(TProfesor a) {
		return (a.getDNI().length() != 9 || a.getNombre().length() > 20 || a.getApellidos().length() > 20
				|| !telefonoCorrecto(a.getTelefono()) || a.getEmail().length() > 100);
	}

	@Override
	public int delete(String id) {
		if (isNumeric(id)) {
			DAOProfesor dao = FactoriaDAO.getInstance().generateDAOProfesor();
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
		// TODO Auto-generated method stub
		return 0;
	}

}
