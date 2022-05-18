package AccesoDatos;

import Entidades.Proveedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DA_Proveedor {

    //Atributo
    private String _mensaje;

    //Metodo get
    public String getMensaje() {
        return _mensaje;
    }

    //Constructor
    public DA_Proveedor() {
        _mensaje = "";
    }

    //Metodo para listar registros 
    public List<Proveedor> ListarRegistros(String condicion) throws SQLException, Exception {
        //Creamos las variables necesarias
        ResultSet rs = null;
        List<Proveedor> lista = new ArrayList();
        Connection _cnn = null;

        //Abrimos la conexion y generamos la sentencia a utilizar
        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select idproveedor, nombreproveedor, descripcionproveedor, activo from Proveedores where activo = 1 ";
            //Revisamos si poseemos algua condicion
            if (!condicion.equals("")) {
                sentencia = String.format("%s and %s", sentencia, condicion);
            }
            //Ejecutamos la sentencia
            rs = stm.executeQuery(sentencia);
            //Vamos pasando datos a la lista
            while (rs.next()) {
                lista.add(new Proveedor(rs.getInt("idproveedor"), rs.getString("nombreproveedor"), rs.getString("descripcionproveedor"),
                        rs.getBoolean("activo")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }

        return lista;
    }

    //Metodo para obtener un regitro
    public Proveedor ObtenerProveedor(String condicion) throws SQLException, Exception {
        //Creamos variables necesarias
        Proveedor proveedor = new Proveedor();
        ResultSet rs;
        Connection _conexion = null;
        //Generamos la conexion y la sentencia que utilizaremos
        try {
            _conexion = ClaseConexion.getConnection();
            Statement stm = _conexion.createStatement();
            String sentencia = "select idproveedor, nombreproveedor, descripcionproveedor, activo from Proveedores";

            //Revisamos si tenemos alguna condicion
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }

            //Ejecutamos ls sentencia
            rs = stm.executeQuery(sentencia);
            //Rellenamos los datos
            if (rs.next()) {
                proveedor.setIdProveedor(rs.getInt(1));
                proveedor.setNombreProveedor(rs.getString(2));
                proveedor.setDescripcionProveedor(rs.getString(3));
                proveedor.setActivo(rs.getBoolean(4));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            //Cerramos la conexion
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }

        return proveedor;
    }
}
