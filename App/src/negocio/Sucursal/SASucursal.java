package negocio.Sucursal;

import java.util.List;

public interface SASucursal {
    public int create(TSucursal suc);
    public TSucursal read(int id);
    public List<TSucursal> readAll();
    public int update(TSucursal suc);
    public int delete (String id);

}