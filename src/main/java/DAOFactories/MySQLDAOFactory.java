package DAOFactories;

import EntitiesInterface.*;
import MySQLDAOEntities.MySQLClienteDAO;
import MySQLDAOEntities.MySQLFacturaDAO;
import MySQLDAOEntities.MySQLFacturaProductoDAO;
import MySQLDAOEntities.MySQLProductoDAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDAOFactory extends DAOFactory {
    public static final String driver = "com.mysql.cj.jdbc.Driver";
    public static final String uri = "jdbc:mysql://localhost:3306/tp1";
    public static final String user = "root", password = "password";



    public static Connection creatConnection() throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, SQLException {
        Class.forName(driver).getDeclaredConstructor().newInstance();
        Connection conn = DriverManager.getConnection(uri, user, password);
        conn.setAutoCommit(false);
        return conn;
    }

    @Override
    public ClienteDao getClienteDAO() throws SQLException {
        return new MySQLClienteDAO();
    }

    @Override
    public FacturaDao getFacturaDAO() throws SQLException {
        return new MySQLFacturaDAO();
    }

    @Override
    public ProductoDao getProductoDAO() {
        return new MySQLProductoDAO();
    }

    @Override
    public FacturaProductoDao getFacturaProductoDAO() {
        return new MySQLFacturaProductoDAO();
    }
}
