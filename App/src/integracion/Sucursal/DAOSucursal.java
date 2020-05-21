package integracion.Sucursal;

    import java.util.List;
    import negocio.Sucursal.TSucursal;

    public interface DAOSucursal {
        public int create(TSucursal suc);

        public TSucursal read(int id);

        public int findbyID(int i);

        public List<TSucursal> readAll();

        public int update(TSucursal suc);

        public int delete(int id);

        public int isDeleted(int id);
        
        public int linked(int s);//devuelve el num de profes y vehiculos asignados a la sucursal
    }

