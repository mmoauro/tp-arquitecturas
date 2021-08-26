import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class FacturaDao {
    private Connection connection;

    public FacturaDao() throws SQLException {
        new Driver();
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "password"); // TODO: Change db name
    }

    public void createTable() throws SQLException {
        String query = "CREATE TABLE Factura (" +
                "idFactura int NOT NULL, " +
                "idCliente int NOT NULL," +
                "PRIMARY KEY(idFactura)," +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))";
        this.connection.prepareStatement(query).execute();
    }

    public void insert (Factura factura ) throws SQLException {
        PreparedStatement query = this.connection.prepareStatement("INSERT INTO Factura (idFactura, idCliente) VALUES (?,?)");
        query.setInt(1, factura.getIdFactura());
        query.setInt(2, factura.getCustomer().getIdCliente());
        query.execute();
    }

    public Factura getById (int id) throws SQLException {
        ClienteDao clienteDao = new ClienteDao();
        PreparedStatement query = this.connection.prepareStatement("SELECT * FROM Factura WHERE idFactura = ?");
        query.setInt(1, id);
        query.setMaxRows(1);
        ResultSet result = query.executeQuery();


        Factura f = null;
        while (result.next()) {
            Cliente c = clienteDao.getById(result.getInt(2));
            f = new Factura(result.getInt(1), c);
        }
        return f;
    }
}
