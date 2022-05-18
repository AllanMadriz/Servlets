package LogicaNegocio;

import AccesoDatos.DA_Cliente;
import Entidades.Cliente;
import java.util.ArrayList;
import java.util.List;

public class BL_Cliente {

    // ATRIBUTOS __________________________
    private String _mensaje;

    // PROPIEDADES ________________________
    public String getMensaje() {
        return _mensaje;
    }

    // CONSTRUCTOR
    public BL_Cliente() {
        _mensaje = "";
    }

    //Metodo para insertar un registro, se llama a la clase de Acceso de Datos
    public int Insertar(Cliente cliente) throws Exception {
        int resultado = 0;
        DA_Cliente accesoDatos = new DA_Cliente();
        
        try {
            resultado = accesoDatos.Insertar(cliente);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
  
    //Metodo para Modificar un registro, se llama a la clase de Acceso de Datos
    public int Modificar(Cliente cliente) throws Exception {
        int resultado = 0;
        DA_Cliente accesoDatos  = new DA_Cliente();
        
        try {
            resultado = accesoDatos.Modificar(cliente);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    //Metodo para Eliminar un registro, se llama a la clase de Acceso de Datos
    public int Eliminar(Cliente cliente) throws Exception {
        int resultado = 0;
        DA_Cliente accesoDatos  = new DA_Cliente();
        
        try {
            resultado = accesoDatos.Eliminar(cliente);
            _mensaje = accesoDatos.getMensaje();
        } catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    //Metodo para insertar un registro, se llama a la clase de Acceso de Datos
    public List<Cliente> ListarRegistros(String condicion) throws Exception{
        List<Cliente> resultado = new ArrayList();
        DA_Cliente accesoDatos  = new DA_Cliente();
        
        try {
            resultado = accesoDatos.ListarRegistros(condicion);
        } 
        catch (Exception e) {
            throw e;
        }
        return resultado;
    }
    
    //Metodo para Obtener un registro, se llama a la clase de Acceso de Datos
    public Cliente ObtenerRegistro(String condicion) throws Exception{
        Cliente cliente;
        DA_Cliente accesoDatos  = new DA_Cliente();;
        
        try {
            cliente = accesoDatos.ObtenerRegistro(condicion);
            
            if(cliente.isActivo()){
                _mensaje = "Cliente recuperado satisfactoriamente";
            }
            else {
                _mensaje = "El cliente no existe";
            }
            
        } catch (Exception e) {
            throw e;
        }
        
        return cliente;
    }
    
}
