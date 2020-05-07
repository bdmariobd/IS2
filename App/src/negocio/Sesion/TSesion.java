package negocio.Sesion;

import java.sql.Time;
import java.util.Date;

public class TSesion {
    private int id;
    private String tipo;
    private Date fecha;
    private Time horaini, horafin;
    private boolean activo;

    public TSesion(int id, Date fecha, Time horaini,Time horafin, String tipo ,boolean activo) {
        this.id = id;
        this.fecha = fecha;
        this.horaini = horaini;
        this.horafin = horafin;
        this.tipo=tipo;
        this.activo = activo;
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

}
