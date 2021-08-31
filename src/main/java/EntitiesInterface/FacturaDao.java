package EntitiesInterface;

import Entities.Factura;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public interface FacturaDao {

    void createTable() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    void insert (Factura factura ) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    Factura getById (int id) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
