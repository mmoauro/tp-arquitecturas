package DAOFactories;

import EntitiesInterface.*;

import java.sql.SQLException;

public abstract class DAOFactory {

    public static final int MySQL = 1;


    public static DAOFactory getDAOFactory(int factoryId) {
        // Este metodo te devuelve un DAO dependiendo de la tecnologia que se esta usando.
        // Para cada tecnologia se tiene un DAO, que implementa los metodos abstractos de esta clase.
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
