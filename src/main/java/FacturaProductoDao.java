import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class FacturaProductoDao {
    private Connection connection;

    public FacturaProductoDao() throws SQLException {
        new Driver();
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp1", "root", "password"); // TODO: Change db name
    }

    public void createTable() throws SQLException {
        String query = "CREATE TABLE Factura_Producto (" +
                "idFactura int NOT NULL, " +
                "idProducto int NOT NULL," +
                "cantidad int NOT NULL," +
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura)," +
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
        this.connection.prepareStatement(query).execute();
    }

    public void insert (FacturaProducto facturaProducto) throws SQLException {
        PreparedStatement query = this.connection.prepareStatement("INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?,?,?)");
        query.setInt(1, facturaProducto.getFactura().getIdFactura());
        query.setInt(2, facturaProducto.getProducto().getIdProdcuto());
        query.setInt(3, facturaProducto.getCantidad());
        query.execute();
    }

    public FacturaProducto getById (int id) throws SQLException {
        PreparedStatement query = this.connection.prepareStatement("SELECT * FROM Factura_Producto WHERE idFactura = ?");
        query.setInt(1, id);
        query.setMaxRows(1);
        ResultSet result = query.executeQuery();


        FacturaProducto f = null;
        while (result.next()) {
            //f = new FacturaProducto(result.getInt(1), result.getInt(2), result.getInt(3));
        }
        return f;
    }
}
