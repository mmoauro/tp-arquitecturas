public class Factura {
    private Cliente customer;
    private int idFactura;

    public Factura(int idFactura, Cliente customer) {
        this.customer = customer;
        this.idFactura = idFactura;
    }

    public Cliente getCustomer() {
        return customer;
    }

    public void setCustomer(Cliente customer) {
        this.customer = customer;
    }

    public int getIdFactura() {
        return idFactura;
    }

}
