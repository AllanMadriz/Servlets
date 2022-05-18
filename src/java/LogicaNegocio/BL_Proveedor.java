package LogicaNegocio;

import AccesoDatos.DA_Proveedor;
import Entidades.Proveedor;
import java.util.ArrayList;
import java.util.List;

public class BL_Proveedor {
    // ATRIBUTOS __________________________

    private String _mensaje;

    // PROPIEDADES ________________________
    public String getMensaje() {
        return _mensaje;
    }

    // CONSTRUCTOR
    public BL_Proveedor() {
        _mensaje = "";
    }

    //Metodo para listar los registros, se llama al metodo debido de la capa AccesoDatos
    public List<Proveedor> ListarRegistros(String condicion) throws Exception {
        List<Proveedor> resultado = new ArrayList();
        DA_Proveedor accesoDatos = new DA_Proveedor();

        try {
            resultado = accesoDatos.ListarRegistros(condicion);
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Metodo para obtener un registro, se llama al metodo debido de la capa AccesoDatos
    public Proveedor ObtenerRegistro(String condicion) throws Exception {
        Proveedor Entidad = null;

        try {

            DA_Proveedor DA = new DA_Proveedor();

            Entidad = DA.ObtenerProveedor(condicion);

        } catch (Exception ex) {
            throw ex;
        }

        return Entidad;
    }
}
