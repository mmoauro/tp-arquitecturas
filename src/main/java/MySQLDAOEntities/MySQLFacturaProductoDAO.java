package MySQLDAOEntities;
import DAOFactories.MySQLDAOFactory;
import Entities.FacturaProducto;
import EntitiesInterface.FacturaProductoDao;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class MySQLFacturaProductoDAO implements FacturaProductoDao {

    public void createTable() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.creatConnection();
        String query = "CREATE TABLE Factura_Producto (" +
                "idFactura int NOT NULL, " +
                "idProducto int NOT NULL," +
                "cantidad int NOT NULL," +
                "FOREIGN KEY (idFactura) REFERENCES Factura(idFactura)," +
                "FOREIGN KEY (idProducto) REFERENCES Producto(idProducto))";
        conn.prepareStatement(query).execute();
        conn.close();
    }

    public void insert (FacturaProducto facturaProducto) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.creatConnection();
        PreparedStatement query = conn.prepareStatement("INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?,?,?)");
        query.setInt(1, facturaProducto.getFactura().getIdFactura());
        query.setInt(2, facturaProducto.getProducto().getIdProdcuto());
        query.setInt(3, facturaProducto.getCantidad());
        query.execute();
        conn.commit();
        conn.close();
    }

    public FacturaProducto getById (int id) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.creatConnection();
        PreparedStatement query = conn.prepareStatement("SELECT * FROM Factura_Producto WHERE idFactura = ?");
        query.setInt(1, id);
        query.setMaxRows(1);
        ResultSet result = query.executeQuery();


        FacturaProducto f = null;
        while (result.next()) {
            //f = new FacturaProducto(result.getInt(1), result.getInt(2), result.getInt(3));
        }
        conn.close();
        return f;
    }
}
