package negocio.Sesion;

import java.sql.Time;
import java.util.Date;

public class TSesion {
    private int id, idAlumno,idProfesor;
    private String tipo;
    private Date fecha;
    private Time horaini, horafin;
    private boolean activo;

    public TSesion(int id, Date fecha, Time horaini,Time horafin, String tipo ,boolean activo, int idAlumno, int idProfesor) {
        this.id = id;
        this.fecha = fecha;
        this.horaini = horaini;
        this.horafin = horafin;
        this.tipo=tipo;
        this.activo = activo;
        this.idAlumno= idAlumno;
        this.idProfesor=idProfesor;
    }

    public int getId() {
        return id;
    }
    public Date getFecha() {
        return fecha;
    }
    public Time getHoraini() {
        return horaini;
    }
    public Time getHorafin() {
        return horafin;
    }
    public String getTipo() {
        return tipo;
    }
    public boolean isActivo() {
        return activo;
    }
    public int getIdAlumno() {
    	return idAlumno;
    }
    public int getIdProfesor() {
    	return idProfesor;
    }

}
