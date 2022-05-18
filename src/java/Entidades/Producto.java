package Entidades;

public class Producto {

    //Atributos
    private int idProducto;
    private String nombreProducto;
    private int stock;
    private int precio;
    private String descripcion;
    private int idCategoriaProducto;
    private int idProveedor;
    private boolean enVenta;

    //Constructores
    public Producto() {
    }

    public Producto(int idProducto, String nombreProducto, int stock, int precio, String descripcion, int idCategoriaProducto, int idProveedor, boolean enVenta) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.stock = stock;
        this.precio = precio;
        this.descripcion = descripcion;
        this.idCategoriaProducto = idCategoriaProducto;
        this.idProveedor = idProveedor;
        this.enVenta = enVenta;
    }
    
    //Metodos get y set
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoriaProducto() {
        return idCategoriaProducto;
    }

    public void setIdCategoriaProducto(int idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public boolean isEnVenta() {
        return enVenta;
    }

    public void setEnVenta(boolean enVenta) {
        this.enVenta = enVenta;
    }

}
