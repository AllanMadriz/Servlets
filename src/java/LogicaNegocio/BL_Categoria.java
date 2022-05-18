package LogicaNegocio;

import AccesoDatos.DA_Categoria;
import Entidades.Categoria;
import java.util.ArrayList;
import java.util.List;

public class BL_Categoria {

    // ATRIBUTOS __________________________
    private String _mensaje;

    // PROPIEDADES ________________________
    public String getMensaje() {
        return _mensaje;
    }

    // CONSTRUCTOR
    public BL_Categoria() {
        _mensaje = "";
    }

    //Metodo para listar, llamamos a la clase del Acceso a Datos y al metodo requerido
    public List<Categoria> ListarRegistros(String condicion) throws Exception {
        List<Categoria> resultado = new ArrayList();
        DA_Categoria accesoDatos = new DA_Categoria();

        try {
            resultado = accesoDatos.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    //Metodo para obtener un registro, llamamos a la clase del Acceso a Datos y al metodo requerido
    public Categoria ObtenerRegistro(String condicion) throws Exception {
        Categoria Entidad = null;

        try {

            DA_Categoria DA = new DA_Categoria();

            Entidad = DA.ObtenerRegistro(condicion);

        } catch (Exception ex) {
            throw ex;
        }

        return Entidad;
    }
}
