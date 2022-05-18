package Entidades;

//No se comentaran todas la entidades ya que funcionan iguales y no es nada complejo de implementar

public class CarritoCompra {
    
    //Creamos los atributos de la clase
    private int idProducto;
    private int idCliente;
    private int cantidad;
    private String nombreProducto;

    //Creamos constructores, uno vacio y otro lleno
    public CarritoCompra() {
    }

    public CarritoCompra(int idProducto, int idCliente, int cantidad, String nombreProducto) {
        this.idProducto = idProducto;
        this.idCliente = idCliente;
        this.cantidad = cantidad;
        this.nombreProducto = nombreProducto;
    }

    //Generamos los metodos get y set
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
}
