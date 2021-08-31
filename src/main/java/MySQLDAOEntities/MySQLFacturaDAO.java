package MySQLDAOEntities;
import DAOFactories.MySQLDAOFactory;
import Entities.*;
import EntitiesInterface.*;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.sql.*;

public class MySQLFacturaDAO implements FacturaDao {

    public void createTable() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.createConnection();
        String query = "CREATE TABLE Factura (" +
                "idFactura int NOT NULL, " +
                "idCliente int NOT NULL," +
                "PRIMARY KEY(idFactura)," +
                "FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente))";
        conn.prepareStatement(query).execute();
        conn.close();
    }

    public void readAndInsertInvoices() throws SQLException, IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String path = Paths.get("").toAbsolutePath().toString() + "/csvs/facturas.csv";
        CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));;

        for (CSVRecord row : parser) {
            ClienteDao clienteDao = new MySQLClienteDAO();
            Entities.Cliente c =  clienteDao.getById(Integer.valueOf(row.get("idCliente")));
            Entities.Factura f = new Entities.Factura(Integer.valueOf(row.get("idFactura")), c);
            this.insert(f);
        }
    }

    public void insert (Factura factura ) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.createConnection();
        PreparedStatement query = conn.prepareStatement("INSERT INTO Factura (idFactura, idCliente) VALUES (?,?)");
        query.setInt(1, factura.getIdFactura());
        query.setInt(2, factura.getCustomer().getIdCliente());
        query.execute();
        conn.commit();
        conn.close();
    }

    public Factura getById (int id) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Connection conn = MySQLDAOFactory.createConnection();
        ClienteDao clienteDao = new MySQLClienteDAO();
        PreparedStatement query = conn.prepareStatement("SELECT * FROM Factura WHERE idFactura = ?");
        query.setInt(1, id);
        query.setMaxRows(1);
        ResultSet result = query.executeQuery();


        Factura f = null;
        while (result.next()) {
            Cliente c = clienteDao.getById(result.getInt(2));
            f = new Factura(result.getInt(1), c);
        }
        conn.close();
        return f;
    }

}
