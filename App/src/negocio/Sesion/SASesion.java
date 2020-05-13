package negocio.Sesion;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface SASesion {
    public int create(TSesion ses);
    public TSesion read(int id);
    public List<TSesion> readAll();
    public int update(TSesion ses);
    public int delete (String id);
    public int findByID(int id);
	public List<TSesion> readAllA(int id);
	public List<TSesion> readAllP(int id);
	public boolean disponibleProfesor(int id, Date fecha, Date horaIni, Date horaFin);
	public boolean disponibleAlumno(int id, Date fecha, Date horaIni, Date horaFin);
	public boolean existeIDProfesor(int s);
	public boolean existeIDAlumno(int s);
}