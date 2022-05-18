
package Entidades;

public class Categoria {
    //Atributos de la clase
    private int idProductoCategoria;
    private String categoria;
    private String descripcionCategoria;

    //Constructor vacio
    public Categoria() {
    }

    //Constructor con parametros
    public Categoria(int idProductoCategoria, String categoria, String descripcionCategoria) {
        this.idProductoCategoria = idProductoCategoria;
        this.categoria = categoria;
        this.descripcionCategoria = descripcionCategoria;
    }

    //Metodos get y set
    public int getIdProductoCategoria() {
        return idProductoCategoria;
    }

    public void setIdProductoCategoria(int idProductoCategoria) {
        this.idProductoCategoria = idProductoCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }
    
    
}
