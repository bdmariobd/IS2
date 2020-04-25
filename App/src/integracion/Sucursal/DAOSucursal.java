package integracion.Sucursal;

    import java.util.List;
    import negocio.Sucursal.TSucursal;

    public interface DAOSucursal {
        public int create(TSucursal suc);

        public TSucursal read(int id);

        public int findbyID(String id);

        public List<TSucursal> readAll();

        public int update(TSucursal suc);

        public int delete(int id);

        public int isDeleted(int id);
    }

}