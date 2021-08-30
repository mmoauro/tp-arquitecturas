import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class ProductoDao {
    private Connection connection;

    public ProductoDao() throws SQLException {
        new Driver();
        this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tp1", "root", "password"); // TODO: Change db name
    }

    public void createTable() throws SQLException {
        String query = "CREATE TABLE Producto (" +
                "idProducto int NOT NULL, " +
                "nombre VARCHAR(55) NOT NULL," +
                "valor float NOT NULL," +
                "PRIMARY KEY (idProducto))";
        this.connection.prepareStatement(query).execute();
    }

    public void insert(Producto producto) throws SQLException {
        PreparedStatement query = this.connection.prepareStatement("INSERT INTO Producto (idProducto, nombre, valor) VALUES (?,?,?)");
        query.setInt(1, producto.getIdProdcuto());
        query.setString(2, producto.getName());
        query.setFloat(3, producto.getValor());
        query.execute();
    }

    public Producto getById (int id) throws SQLException {
        PreparedStatement query = this.connection.prepareStatement("SELECT * FROM Producto WHERE idProducto = ?");
        query.setInt(1, id);
        query.setMaxRows(1);
        ResultSet result = query.executeQuery();

        Producto p = null;
        while (result.next()) {
            p = new Producto(result.getInt(1), result.getString(2), result.getFloat(3));
        }
        return p;
    }

    public Producto getMoreCollected() throws SQLException {
        PreparedStatement query = this.connection.prepareStatement("SELECT P.* FROM Factura_Producto " +
                "JOIN Producto P on P.idProducto = Factura_Producto.idProducto " +
                "ORDER BY (cantidad * P.valor) desc LIMIT 1");
        ResultSet result = query.executeQuery();

        Producto p = null;
        while (result.next()) {
            p = new Producto(result.getInt(1), result.getString(2), result.getFloat(3));
        }
        return p;
    }
}
