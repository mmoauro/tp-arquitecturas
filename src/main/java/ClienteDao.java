import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;

public class ClienteDao {
    private Connection connection;

    public ClienteDao() throws SQLException {
        new Driver();
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp1", "root", "password"); // TODO: Change db name
    }

    public void createTable() throws SQLException {
        String query = "CREATE TABLE Cliente (" +
                "idCliente int NOT NULL, " +
                "nombre VARCHAR(500)," +
                "email VARCHAR(150)," +
                "PRIMARY KEY(idCliente))";
        this.connection.prepareStatement(query).execute();
    }

    public void insert(Cliente cliente) throws SQLException {
        PreparedStatement query = this.connection.prepareStatement("INSERT INTO Cliente (idCliente, nombre, email) VALUES (?,?,?)");
        query.setInt(1, cliente.getIdCliente());
        query.setString(2, cliente.getNombre());
        query.setString(3, cliente.getEmail());
        query.execute();
    }

    public Cliente getById(int id) throws SQLException {
        PreparedStatement query = this.connection.prepareStatement("SELECT * FROM Cliente WHERE idCliente = ?");
        query.setInt(1, id);
        query.setMaxRows(1);
        ResultSet result = query.executeQuery();


        Cliente c = null;
        while (result.next()) {
            c = new Cliente(result.getInt(1), result.getString(2), result.getString(3));
        }
        return c;
    }

    public ArrayList<Cliente> getCustomersOrderByInvoiced() throws SQLException {
        PreparedStatement query = this.connection.prepareStatement("SELECT C.*, SUM(P.valor * FP.cantidad) AS invoiced FROM Factura " +
                "JOIN Cliente C on Factura.idCliente = C.idCliente " +
                "JOIN Factura_Producto FP on Factura.idFactura = FP.idFactura " +
                "JOIN Producto P on P.idProducto = FP.idProducto " +
                "GROUP BY C.idCliente ORDER BY invoiced DESC;");
        ResultSet result = query.executeQuery();


        ArrayList<Cliente> customers = new ArrayList<>();
        while (result.next()) {
            customers.add(new Cliente(result.getInt(1), result.getString(2), result.getString(3)));
        }
        return customers;
    }
}
