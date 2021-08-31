package Entities;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import MySQLDAOEntities.*;
import DAOFactories.*;
import EntitiesInterface.*;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Main {
    private static String path = Paths.get("").toAbsolutePath().toString() + "/csvs/";
    private static CSVParser parser;
    private static DAOFactory daoFactory = new MySQLDAOFactory();

    public static void main(String[] args) {
        try {
            daoFactory.getClienteDAO().createTable();
            daoFactory.getFacturaDAO().createTable();
            daoFactory.getProductoDAO().createTable();
            daoFactory.getClienteDAO().readAndInsertCustomers();
            daoFactory.getProductoDAO().readAndInsertProducts();
            daoFactory.getFacturaDAO().readAndInsertInvoices();
            daoFactory.getFacturaProductoDAO().createTable();
            daoFactory.getFacturaProductoDAO().readAndInsertProductInvoices();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
