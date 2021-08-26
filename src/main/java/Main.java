import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.sql.SQLException;

public class Main {
    private static String path = Paths.get("").toAbsolutePath().toString() + "/tp1/csvs/";
    private static CSVParser parser;

    public static void main(String[] args) throws SQLException, IOException {
        ProductoDao productoDao = new ProductoDao();
        ClienteDao clienteDao = new ClienteDao();
        FacturaDao facturaDao = new FacturaDao();
        FacturaProductoDao facturaProductoDao = new FacturaProductoDao();
        productoDao.createTable();
        clienteDao.createTable();
        facturaDao.createTable();
        facturaProductoDao.createTable();
        readAndInsertProducts();
        readAndInsertCustomers();
        readAndInsertInvoices();
        readAndInsertProductInvoices();
    }

    private static void updatePath (String path) throws IOException {
        parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));
    }

    public static void readAndInsertProducts () throws SQLException, IOException {
        ProductoDao productoDao = new ProductoDao();
        String productPath = path + "productos.csv";
        updatePath(productPath);
        for (CSVRecord row : parser) {
            Producto p = new Producto(Integer.valueOf(row.get("idProducto")), row.get("nombre"), Float.valueOf(row.get("valor")));
            productoDao.insert(p);
        }
    }

    public static void readAndInsertCustomers() throws SQLException, IOException {
        ClienteDao clienteDao = new ClienteDao();
        String customerPath = path + "clientes.csv";
        updatePath(customerPath);
        for (CSVRecord row : parser) {
            Cliente c = new Cliente(Integer.valueOf(row.get("idCliente")), row.get("nombre"), row.get("email"));
            clienteDao.insert(c);
        }
    }

    public static void readAndInsertInvoices() throws SQLException, IOException {
        FacturaDao facturaDao = new FacturaDao();
        ClienteDao clienteDao = new ClienteDao();
        String facturaPath = path + "facturas.csv";
        updatePath(facturaPath);
        for (CSVRecord row : parser) {
            Cliente c = clienteDao.getById(Integer.valueOf(row.get("idCliente")));

            Factura f = new Factura(Integer.valueOf(row.get("idFactura")), c);
            facturaDao.insert(f);
        }
    }

    public static void readAndInsertProductInvoices() throws SQLException, IOException {
        FacturaDao facturaDao = new FacturaDao();
        ProductoDao productoDao = new ProductoDao();
        FacturaProductoDao facturaProductoDao = new FacturaProductoDao();

        String facturaPath = path + "facturas-productos.csv";
        updatePath(facturaPath);
        for (CSVRecord row : parser) {

            Factura f = facturaDao.getById(Integer.valueOf(row.get("idFactura")));
            Producto p = productoDao.getById(Integer.valueOf(row.get("idProducto")));
            FacturaProducto fp = new FacturaProducto(f, p, Integer.valueOf(row.get("cantidad")));
            facturaProductoDao.insert(fp);
        }
    }


}
