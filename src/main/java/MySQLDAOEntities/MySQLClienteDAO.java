package MySQLDAOEntities;

import DAOFactories.MySQLDAOFactory;
import Entities.Cliente;
import EntitiesInterface.ClienteDao;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;

public class MySQLClienteDAO implements ClienteDao {

    public void createTable() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String query = "CREATE TABLE Cliente (" +
                "idCliente int NOT NULL, " +
                "nombre VARCHAR(500)," +
                "email VARCHAR(150)," +
                "PRIMARY KEY(idCliente))";
        Connection conn = MySQLDAOFactory.creatConnection(); //Creo la conexion
        conn.prepareStatement(query).execute(); //Ejecuto Query
        conn.close(); //Cierro conexion
    }

    public void readAndInsertCustomers() throws SQLException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String path = Paths.get("").toAbsolutePath().toString() + "/csvs/clientes.csv";
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));;

        for (CSVRecord row : parser) {
            Entities.Cliente c = new Entities.Cliente(Integer.valueOf(row.get("idCliente")), row.get("nombre"), row.get("email"));
            this.insert(c);
        }
    }

    public void insert(Cliente cliente) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.creatConnection();
        PreparedStatement query = conn.prepareStatement("INSERT INTO Cliente (idCliente, nombre, email) VALUES (?,?,?)");
        query.setInt(1, cliente.getIdCliente());
        query.setString(2, cliente.getNombre());
        query.setString(3, cliente.getEmail());
        query.execute();
        conn.commit();
        conn.close();
    }

    public Cliente getById(int id) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.creatConnection();
        PreparedStatement query = conn.prepareStatement("SELECT * FROM Cliente WHERE idCliente = ?");
        query.setInt(1, id);
        query.setMaxRows(1);
        ResultSet result = query.executeQuery();


        Cliente c = null;
        while (result.next()) {
            c = new Cliente(result.getInt(1), result.getString(2), result.getString(3));
        }
        conn.close();
        return c;
    }

    public ArrayList<Cliente> getCustomersOrderByInvoiced() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.creatConnection();
        PreparedStatement query = conn.prepareStatement("SELECT C.*, SUM(P.valor * FP.cantidad) AS invoiced FROM Factura " +
                "JOIN Cliente C on Factura.idCliente = C.idCliente " +
                "JOIN Factura_Producto FP on Factura.idFactura = FP.idFactura " +
                "JOIN Producto P on P.idProducto = FP.idProducto " +
                "GROUP BY C.idCliente ORDER BY invoiced DESC;");
        ResultSet result = query.executeQuery();


        ArrayList<Cliente> customers = new ArrayList<>();
        while (result.next()) {
            customers.add(new Cliente(result.getInt(1), result.getString(2), result.getString(3)));
        }
        conn.close();
        return customers;
    }

}
