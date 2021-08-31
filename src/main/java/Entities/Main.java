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
            /*daoFactory.getClienteDAO().createTable();
            daoFactory.getFacturaDAO().createTable();
            daoFactory.getProductoDAO().createTable();
            readAndInsertCustomers();
            readAndInsertInvoices();
            daoFactory.getProductoDAO().readAndInsertProducts();*/
            daoFactory.getFacturaProductoDAO().createTable();
            readAndInsertProductInvoices();
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

    private static void updatePath (String path) throws IOException {
        parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
    }

    /*public static void readAndInsertProducts () throws SQLException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ProductoDao productoDao = daoFactory.getProductoDAO();
        String productPath = path + "productos.csv";
        updatePath(productPath);
        for (CSVRecord row : parser) {
            Entities.Producto p = new Entities.Producto(Integer.valueOf(row.get("idProducto")), row.get("nombre"), Float.valueOf(row.get("valor")));
            productoDao.insert(p);
        }
    }*/

    public static void readAndInsertCustomers() throws SQLException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        ClienteDao clienteDao = daoFactory.getClienteDAO();
        String customerPath = path + "clientes.csv";
        updatePath(customerPath);
        for (CSVRecord row : parser) {
            Entities.Cliente c = new Entities.Cliente(Integer.valueOf(row.get("idCliente")), row.get("nombre"), row.get("email"));
            clienteDao.insert(c);
        }
    }

    public static void readAndInsertInvoices() throws SQLException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FacturaDao facturaDao = daoFactory.getFacturaDAO();
        ClienteDao clienteDao = daoFactory.getClienteDAO();
        String facturaPath = path + "facturas.csv";
        updatePath(facturaPath);
        for (CSVRecord row : parser) {
            Entities.Cliente c = clienteDao.getById(Integer.valueOf(row.get("idCliente")));

            Entities.Factura f = new Entities.Factura(Integer.valueOf(row.get("idFactura")), c);
            facturaDao.insert(f);
        }
    }

    public static void readAndInsertProductInvoices() throws SQLException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        FacturaDao facturaDao = daoFactory.getFacturaDAO();
        ProductoDao productoDao = daoFactory.getProductoDAO();
        FacturaProductoDao facturaProductoDao = daoFactory.getFacturaProductoDAO();

        String facturaPath = path + "facturas-productos.csv";
        updatePath(facturaPath);
        for (CSVRecord row : parser) {

            Entities.Factura f = facturaDao.getById(Integer.valueOf(row.get("idFactura")));
            Entities.Producto p = productoDao.getById(Integer.valueOf(row.get("idProducto")));
            Entities.FacturaProducto fp = new Entities.FacturaProducto(f, p, Integer.valueOf(row.get("cantidad")));
            facturaProductoDao.insert(fp);
        }
    }




}
