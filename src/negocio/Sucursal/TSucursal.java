package negocio.Sucursal;


public class TSucursal {
    private int id, telefono;
    private String ciudad, direccion;
    private boolean activo;

    public TSucursal(int id, String ciudad, int telefono,String direccion, boolean activo) {
        this.id = id;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.direccion = direccion;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }
    public String getCiudad() {
        return ciudad;
    }
    public int getTelefono() {
        return telefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public boolean isActivo() {
        return activo;
    }

}
