package LogicaNegocio;

import AccesoDatos.DA_Productos;
import Entidades.Producto;
import java.util.List;

public class BL_Producto {

    //Atributos
    private String _Mensaje;

    //Metodo get 
    public String getMensaje() {
        return _Mensaje;
    }

    //Metodo para Insertar los registros, se llama al metodo debido de la capa AccesoDatos
    public int Insertar(Producto Entidad) throws Exception {
        int Resultado = 0;

        try {

            DA_Productos DA = new DA_Productos();

            Resultado = DA.Insertar(Entidad);

        } catch (Exception ex) {
            Resultado = -1;
            throw ex;
        }

        return Resultado;
    }

    //Metodo para Modificar los registros, se llama al metodo debido de la capa AccesoDatos
    public int Modificar(Producto Entidad) throws Exception {
        int Resultado = 0;

        try {

            DA_Productos DA = new DA_Productos();

            Resultado = DA.Modificar(Entidad);

        } catch (Exception ex) {
            Resultado = -1;
            throw ex;
        }

        return Resultado;
    }

    //Metodo para Eliminar los registros, se llama al metodo debido de la capa AccesoDatos
    public int Eliminar(Producto Entidad) throws Exception {
        int Resultado = 0;

        try {

            DA_Productos DA = new DA_Productos();

            Resultado = DA.Eliminar(Entidad);
            _Mensaje = DA.getMensaje();

        } catch (Exception ex) {
            Resultado = -1;
            throw ex;
        }

        return Resultado;
    }

    //Metodo para listar los registros, se llama al metodo debido de la capa AccesoDatos
    public List<Producto> ListarRegistros(String condicion) throws Exception {
        List<Producto> Datos;

        try {

            DA_Productos DA = new DA_Productos();

            Datos = DA.ListarRegistros(condicion);

        } catch (Exception ex) {
            Datos = null;
            throw ex;
        }

        return Datos;
    }

    //Metodo para obtener un registro, se llama al metodo debido de la capa AccesoDatos
    public Producto ObtenerRegistro(String condicion) throws Exception {
        Producto Entidad = null;

        try {

            DA_Productos DA = new DA_Productos();

            Entidad = DA.ObtenerRegistro(condicion);

        } catch (Exception ex) {
            throw ex;
        }

        return Entidad;
    }
}
