package MySQLDAOEntities;
import DAOFactories.MySQLDAOFactory;
import Entities.FacturaProducto;
import Entities.Producto;
import EntitiesInterface.ClienteDao;
import EntitiesInterface.FacturaDao;
import EntitiesInterface.FacturaProductoDao;
import EntitiesInterface.ProductoDao;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
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

    public void readAndInsertProductInvoices() throws SQLException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String path = Paths.get("").toAbsolutePath().toString() + "/csvs/facturas-productos.csv";
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

        for (CSVRecord row : parser) {
            FacturaDao fDao = new MySQLFacturaDAO();
            ProductoDao pDao = new MySQLProductoDAO();

            Entities.Factura f = fDao.getById(Integer.valueOf(row.get("idFactura")));
            Entities.Producto p = pDao.getById(Integer.valueOf(row.get("idProducto")));
            Entities.FacturaProducto fp = new Entities.FacturaProducto(f, p, Integer.valueOf(row.get("cantidad")));
            this.insert(fp);
        }
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
