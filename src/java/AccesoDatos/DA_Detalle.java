package AccesoDatos;

import Entidades.DetalleFactura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DA_Detalle {

    //Atributo
    private String _Mensaje;

    //Metodo get
    public String getMensaje() {
        return _Mensaje;
    }

    //Constructor
    public DA_Detalle() {
        _Mensaje = "";
    }

    //Metodo para listar los detalles
    public List<DetalleFactura> ListarRegistros(String condicion) throws SQLException, Exception {
        //Creamos las variables necesarias 
        ResultSet rs = null;
        List<DetalleFactura> lista = new ArrayList<>();
        Connection _Conexion = null;

        //Iniciamos la conexion y la sentencia que deseamos ejecutar
        try {
            _Conexion = ClaseConexion.getConnection();
            Statement stm = _Conexion.createStatement();
            String sentencia = "select idfactura, idproducto, cantidadfactura, preciounitario, total from DetalleFacturas";
            //Revisamos si la condicion tiene algo
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            //Ejecutamos la sentencia y vamos agregando a la lista segun registros nos llegan
            rs = stm.executeQuery(sentencia);
            while (rs.next()) {
                lista.add(new DetalleFactura(rs.getInt("idfactura"), rs.getInt("idproducto"), rs.getInt("cantidadfactura"),
                        rs.getInt("preciounitario"), rs.getInt("total")));
            }

        } catch (Exception e) {
            throw e;
        } finally {
            //Cerramos la conexion
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }

        return lista;
    }

    //Metodo para insertar un registro
    public int Insertar(DetalleFactura detalleFactura) throws Exception {
        //Creamos las variables necesarias
        int resultado = -1;
        Connection _conexion = null;

        //Iniciamos la conexion y creamos la sentencia a ejecutar
        try {
            _conexion = ClaseConexion.getConnection();
            String sentencia = "insert into DetalleFacturas (IDFactura, IDProducto, CantidadFactura, PrecioUnitario, Total) values (?, ?, ?, ?, ?)";

            //Pasamos los valores a insertar
            PreparedStatement ps = _conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, detalleFactura.getIdFactura());
            ps.setInt(2, detalleFactura.getIdProducto());
            ps.setInt(3, detalleFactura.getCantidadFacturada());
            ps.setInt(4, detalleFactura.getPrecioUnidad());
            ps.setInt(5, detalleFactura.getTotal());

            //Ejecutamos
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                resultado = rs.getInt(1);
            }
            //En caso de error
        } catch (Exception e) {
            throw e;
        } finally {
            //Cerramos la conexion
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }

    //Metodo para eliminar un registro
    public int Eliminar(DetalleFactura detalleFactura) throws Exception {
        //Creamos las variables necesarias
        int resultado = -1;
        Connection _conexion = null;

        //Abrimos conexion y pasamos la sentencia
        try {
            _conexion = ClaseConexion.getConnection();
            String sentencia = "delete from DetalleFacturas where IDFactura = ? and IDProducto = ?";

            PreparedStatement ps = _conexion.prepareStatement(sentencia);
            ps.setInt(1, detalleFactura.getIdFactura());
            ps.setInt(2, detalleFactura.getIdProducto());

            resultado = ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            //Cerramos la conexion
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }

}
