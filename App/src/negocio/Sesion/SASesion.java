package negocio.Sesion;

import java.util.List;

public interface SASesion {
    public int create(TSesion ses);
    public TSesion read(int id);
    public List<TSesion> readAll();
    public int update(TSesion ses);
    public int delete (String id);
    public int findByID(int id);
}