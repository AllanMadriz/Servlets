package Entidades;

public class Proveedor {
    
    //Atributos
    private int idProveedor;
    private String nombreProveedor;
    private String descripcionProveedor;
    private boolean activo;

    //Constructores
    public Proveedor() {
    }

    public Proveedor(int idProveedor, String nombreProveedor, String descripcionProveedor, boolean activo) {
        this.idProveedor = idProveedor;
        this.nombreProveedor = nombreProveedor;
        this.descripcionProveedor = descripcionProveedor;
        this.activo = activo;
    }

    //Metodos get y set
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getDescripcionProveedor() {
        return descripcionProveedor;
    }

    public void setDescripcionProveedor(String descripcionProveedor) {
        this.descripcionProveedor = descripcionProveedor;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
