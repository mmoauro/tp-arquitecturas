package EntitiesInterface;

import Entities.FacturaProducto;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public interface FacturaProductoDao {

    void createTable() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    void insert (FacturaProducto facturaProducto) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    FacturaProducto getById (int id) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
