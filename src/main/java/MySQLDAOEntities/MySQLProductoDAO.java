package MySQLDAOEntities;

import DAOFactories.MySQLDAOFactory;
import Entities.Producto;
import EntitiesInterface.ProductoDao;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.sql.*;

public class MySQLProductoDAO implements ProductoDao {

    public void createTable() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.creatConnection();
        String query = "CREATE TABLE Producto (" +
                "idProducto int NOT NULL, " +
                "nombre VARCHAR(55) NOT NULL," +
                "valor float NOT NULL," +
                "PRIMARY KEY (idProducto))";
        conn.prepareStatement(query).execute();
        conn.close();
    }

    public void readAndInsertProducts () throws SQLException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String path = Paths.get("").toAbsolutePath().toString() + "/csvs/productos.csv";
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));;
        //ProductoDao productoDao = MySQLDAOFactory.getProductoDAO();
        for (CSVRecord row : parser) {
            Entities.Producto p = new Entities.Producto(Integer.valueOf(row.get("idProducto")), row.get("nombre"), Float.valueOf(row.get("valor")));
            this.insert(p);
        }
    }

    public void insert(Producto producto) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.creatConnection();
        PreparedStatement query = conn.prepareStatement("INSERT INTO Producto (idProducto, nombre, valor) VALUES (?,?,?)");
        query.setInt(1, producto.getIdProdcuto());
        query.setString(2, producto.getName());
        query.setFloat(3, producto.getValor());
        query.execute();
        conn.commit();
        conn.close();
    }

    public Producto getById (int id) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.creatConnection();
        PreparedStatement query = conn.prepareStatement("SELECT * FROM Producto WHERE idProducto = ?");
        query.setInt(1, id);
        query.setMaxRows(1);
        ResultSet result = query.executeQuery();

        Producto p = null;
        while (result.next()) {
            p = new Producto(result.getInt(1), result.getString(2), result.getFloat(3));
        }
        conn.close();
        return p;
    }

    public Producto getMoreCollected() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.creatConnection();
        PreparedStatement query = conn.prepareStatement("SELECT P.* FROM Factura_Producto " +
                "JOIN Producto P on P.idProducto = Factura_Producto.idProducto " +
                "ORDER BY (cantidad * P.valor) desc LIMIT 1");
        ResultSet result = query.executeQuery();

        Producto p = null;
        while (result.next()) {
            p = new Producto(result.getInt(1), result.getString(2), result.getFloat(3));
        }
        conn.close();
        return p;
    }

}
