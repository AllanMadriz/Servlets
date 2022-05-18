package Entidades;

public class DetalleFactura {
    //Atributos
    private int idFactura;
    private int idProducto;
    private int cantidadFacturada;
    private int precioUnidad;
    private int total;

    //Constructores
    public DetalleFactura() {

    }

    public DetalleFactura(int idFactura, int idProducto, int cantidadFacturada, int precioUnidad, int total) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidadFacturada = cantidadFacturada;
        this.precioUnidad = precioUnidad;
        this.total = total;
    }

    //Metodos get y set
    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidadFacturada() {
        return cantidadFacturada;
    }

    public void setCantidadFacturada(int cantidadFacturada) {
        this.cantidadFacturada = cantidadFacturada;
    }

    public int getPrecioUnidad() {
        return precioUnidad;
    }

    public void setPrecioUnidad(int precioUnidad) {
        this.precioUnidad = precioUnidad;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
