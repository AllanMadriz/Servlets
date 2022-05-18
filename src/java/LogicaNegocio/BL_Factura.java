package LogicaNegocio;

import AccesoDatos.DA_Facturas;
import Entidades.Factura;
import java.util.List;

public class BL_Factura {

    //Atributos
    private String _Mensaje;

    //Metodo get
    public String getMensaje() {
        return _Mensaje;
    }

    //Metodo para listar los registros, se llama al metodo debido de la capa AccesoDatos
    public List<Factura> ListarRegistros(String Condicion) throws Exception {
        List<Factura> Datos;

        try {
            DA_Facturas DA = new DA_Facturas();
            Datos = DA.ListarRegistros(Condicion);
        } catch (Exception e) {
            Datos = null;
            throw e;
        }
        return Datos;
    }

    //Metodo para obtener un registro, se llama al metodo debido de la capa AccesoDatos
    public Factura ObtenerRegistro(String condicion) throws Exception {
        Factura Entidad = null;

        try {
            DA_Facturas DA = new DA_Facturas();
            Entidad = DA.ObtenerRegistro(condicion);
        } catch (Exception ex) {
            throw ex;
        }

        return Entidad;
    }

    //Metodo para Insertar los registros, se llama al metodo debido de la capa AccesoDatos
    public int Insertar(Factura Entidad) throws Exception {
        int Resultado = 0;

        try {
            DA_Facturas DA = new DA_Facturas();
            Resultado = DA.Insertar(Entidad);
            _Mensaje = DA.getMensaje();
        } catch (Exception ex) {
            Resultado = -1;
            throw ex;
        }

        return Resultado;
    }

    //Metodo para modificar el precio de los registros, se llama al metodo debido de la capa AccesoDatos
    public int ModificarFactura(Factura Entidad) throws Exception {
        int idfactura = 0;

        try {
            DA_Facturas DA = new DA_Facturas();

            idfactura = DA.Modificar(Entidad);
        } catch (Exception ex) {
            throw ex;
        }

        return idfactura;
    }

    //Metodo para Modificar los desc los registros, se llama al metodo debido de la capa AccesoDatos
    public int ModificarDesc(Factura Entidad) throws Exception {
        int idfactura = 0;

        try {
            DA_Facturas DA = new DA_Facturas();

            idfactura = DA.ModificarDesc(Entidad);
        } catch (Exception ex) {
            throw ex;
        }

        return idfactura;
    }

    //Metodo para modifica el total los registros, se llama al metodo debido de la capa AccesoDatos
    public int ModificarTotal(Factura Entidad) throws Exception {
        int idfactura = 0;

        try {
            DA_Facturas DA = new DA_Facturas();

            idfactura = DA.ModificarTotal(Entidad);
        } catch (Exception ex) {
            throw ex;
        }

        return idfactura;
    }

    //Metodo para Eliminar los registros, se llama al metodo debido de la capa AccesoDatos
    public int Eliminar(Factura factura) throws Exception {
        int resultado = -1;
        DA_Facturas adFactura = new DA_Facturas();

        try {
            resultado = adFactura.Eliminar(factura);
            _Mensaje = adFactura.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
}
