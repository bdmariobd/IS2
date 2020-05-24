package negocio.Test;


import java.util.List;

import integracion.FactoriaDAO;
import integracion.Test.DAOTest;

public class SATestIMP implements SATest{
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
	public int create(TTest t) {
		DAOTest dao = FactoriaDAO.getInstance().generateDAOTest();
		if(t.getNumpreguntas()>50||t.getNumpreguntas()<1|| // preguntar cuantas preguntas son
				 t.getTipo().length()>20) return -3;
		int id=  dao.create(t);
		return id;
	}

	@Override
	public TTest read(int id) {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOTest().read(id);
	}

	@Override
	public List<TTest> readAll() {
		// TODO Auto-generated method stub
		return FactoriaDAO.getInstance().generateDAOTest().readAll();
	}

	@Override
	public int update(TTest t) {
		// TODO Auto-generated method stub
		if(t.getNumpreguntas()>50||t.getNumpreguntas()<1|| // preguntar cuantas preguntas son
				 t.getTipo().length()>20) return -3;
		return FactoriaDAO.getInstance().generateDAOTest().update(t);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		if(isNumeric(id)) {
			DAOTest dao = FactoriaDAO.getInstance().generateDAOTest();
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
	
}
