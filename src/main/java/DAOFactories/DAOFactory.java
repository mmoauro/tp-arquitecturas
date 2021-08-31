package DAOFactories;

import EntitiesInterface.*;

import java.sql.SQLException;

public abstract class DAOFactory {

    public static final int MySQL = 1;


    public static DAOFactory getDAOFactory(int factoryId) {
        switch (factoryId) {
            case 1:
                return new MySQLDAOFactory();
            default:
                return null;
        }
    };


    public abstract ClienteDao getClienteDAO() throws SQLException;

    public abstract FacturaDao getFacturaDAO() throws SQLException;

    public abstract ProductoDao getProductoDAO() throws SQLException;

    public abstract FacturaProductoDao getFacturaProductoDAO() throws SQLException;
}
