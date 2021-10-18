package Entities;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String email;

    public Cliente (int idCliente, String name, String email) {
        this.idCliente = idCliente;
        this.nombre = name;
        this.email = email;
    }

    public Cliente () {}

    public int getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString () {
        return this.idCliente + " - " + this.nombre;
    }
}
