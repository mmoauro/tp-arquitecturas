package Entities;

import EntitiesInterface.ProductoDao;

import java.sql.SQLException;

public class Producto {
    private ProductoDao dao;
    private int idProdcuto;
    private String name;
    private float valor;

    /*public Entities.Producto () throws SQLException {
        this.dao = new MySQLDAOEntities.MySQLProductoDAO();
    }*/

    public Producto (int idProdcuto, String name, float valor) throws SQLException {
        //this();
        this.idProdcuto = idProdcuto;
        this.name = name;
        this.valor = valor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getIdProdcuto() {
        return this.idProdcuto;
    }

    public String toString() {
        return this.idProdcuto + " - " + this.name;
    }

}
