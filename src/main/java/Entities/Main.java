package Entities;

import DAOFactories.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Main {
    private static DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MySQL);

    public static void main(String[] args) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        daoFactory.getClienteDAO().createTable();
        daoFactory.getFacturaDAO().createTable();
        daoFactory.getProductoDAO().createTable();
        daoFactory.getFacturaProductoDAO().createTable();
        daoFactory.getClienteDAO().readAndInsertCustomers();
        daoFactory.getProductoDAO().readAndInsertProducts();
        daoFactory.getFacturaDAO().readAndInsertInvoices();
        daoFactory.getFacturaProductoDAO().readAndInsertProductInvoices();
    }
}
