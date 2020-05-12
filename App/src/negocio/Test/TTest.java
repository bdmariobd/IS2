package negocio.Test;

public class TTest {

    private int id, numpreguntas;
    private String tipo;
    private boolean activo;

    public TTest(int id, String tipo, int numpreguntas, boolean activo) {
        this.id = id;
        this.tipo = tipo;
        this.numpreguntas = numpreguntas;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }
    public String getTipo() {
        return tipo;
    }
    public int getNumpreguntas() {
        return numpreguntas;
    }
    public boolean isActivo() {
        return activo;
    }
}
