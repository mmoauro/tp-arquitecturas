package EntitiesInterface;

import Entities.Cliente;

import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.ArrayList;

public interface ClienteDao {

    void createTable() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    void insert(Cliente cliente) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    Cliente getById(int id) throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    ArrayList<Cliente> getCustomersOrderByInvoiced() throws SQLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
