package integracion.Test;

import java.util.List;
import negocio.Test.TTest;

public interface DAOTest {
    public int create(TTest tes);

    public TTest read(int id);

    public int findbyID(int i);

    public List<TTest> readAll();

    public int update(TTest tes);

    public int delete(int id);

    public int isDeleted(int id);
}

