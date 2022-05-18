package LogicaNegocio;

import AccesoDatos.DA_Detalle;
import Entidades.DetalleFactura;
import java.util.List;

public class BL_Detalle {

    //Atributos
    private String _Mensaje;

    //Metodo get
    public String getMensaje() {
        return _Mensaje;
    }

    //Metodo para listar los registros, se llama al metodo debido de la capa AccesoDatos
    public List<DetalleFactura> ListarRegistros(String Condicion) throws Exception {
        List<DetalleFactura> Datos;
        DA_Detalle AccesoDatos;
        try {
            AccesoDatos = new DA_Detalle();
            Datos = AccesoDatos.ListarRegistros(Condicion);
        } catch (Exception ex) {
            Datos = null;
            throw ex;
        }

        return Datos;
    }

    //Metodo para Insertar los registros, se llama al metodo debido de la capa AccesoDatos
    public int Insertar(DetalleFactura detalleFactura) throws Exception {
        int resultado = 0;
        DA_Detalle accesoDatos = new DA_Detalle();

        try {
            resultado = accesoDatos.Insertar(detalleFactura);
            _Mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }

    //Metodo para Eliminar los registros, se llama al metodo debido de la capa AccesoDatos
    public int Eliminar(DetalleFactura Entidad) throws Exception {
        int Resultado = 0;

        try {
            DA_Detalle DA = new DA_Detalle();
            Resultado = DA.Eliminar(Entidad);
        } catch (Exception ex) {
            Resultado = -1;
            throw ex;
        }

        return Resultado;
    }
}
