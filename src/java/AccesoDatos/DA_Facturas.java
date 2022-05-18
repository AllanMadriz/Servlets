package AccesoDatos;

import Entidades.Factura;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DA_Facturas {

    //Mensaje
    private String _Mensaje;

    //Metodo get del atributo
    public String getMensaje() {
        return _Mensaje;
    }

    //Constructor
    public DA_Facturas() {
        _Mensaje = "";
    }

    //Metodo para listar los registros 
    public List<Factura> ListarRegistros(String Condicion) throws Exception {
        //Se crean las variables necesarias
        ResultSet rs = null;
        Factura Entidad;
        List<Factura> ListaF = new ArrayList<>();
        Connection _Conexion = null;

        //Se inicia la conexion y se prepara la sentencia que se debe pasar a la BD
        try {
            _Conexion = ClaseConexion.getConnection();
            Statement st = _Conexion.createStatement();
            String Sentencia;
            Sentencia = "select idfactura, idcliente, idempleado, fechafactura, totalmonto, descuento, montopagar, tipofactura, cancelada from Facturas where cancelada = 0";
            //REvisamos si necesitamos alguna condicion en el momento de listar
            if (!Condicion.equals("")) {
                Sentencia = String.format("%s and %s", Sentencia, Condicion);
            }
            //Ejecutamos la sentencia
            rs = st.executeQuery(Sentencia);
            //Vamos anadiendo en la lista segun los registros que nos presenta
            while (rs.next()) {
                ListaF.add(new Factura(rs.getInt("idfactura"), rs.getInt("idcliente"), rs.getInt("idempleado"), rs.getDate("fechafactura"),
                        rs.getInt("totalmonto"), rs.getInt("descuento"), rs.getInt("montopagar"),
                        rs.getString("tipofactura"), rs.getBoolean("cancelada")));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //Cerramos la conexion
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }
        return ListaF;
    }

    //Metodo para obtener registros
    public Factura ObtenerRegistro(String Condicion) throws Exception {
        //Creamos las variables necesarias
        ResultSet rs = null;
        Factura Entidad = new Factura();
        String Sentencia;
        Connection _Conexion = null;
        //Creamos la sentencia y revisamos si tiene alguna condicion que debamos cumplir
        Sentencia = "select idfactura, idcliente, idempleado, fechafactura, totalmonto, descuento, montopagar, tipofactura, cancelada from Facturas";
        if (!Condicion.equals("")) {
            Sentencia = String.format("%s WHERE %s", Sentencia, Condicion);
        }
        //Abrimos la conexion y ejecutamos la sentencia
        try {
            _Conexion = ClaseConexion.getConnection();
            Statement stm = _Conexion.createStatement();
            rs = stm.executeQuery(Sentencia);
            //Rellenamos los atributos segun lo devuelto
            if (rs.next()) {
                Entidad.setIdFactura(rs.getInt(1));
                Entidad.setIdCliente(rs.getInt(2));
                Entidad.setIdEmpleado(rs.getInt(3));
                Entidad.setFechaFactura(rs.getDate(4));
                Entidad.setTotalMonto(rs.getInt(5));
                Entidad.setDescuento(rs.getInt(6));
                Entidad.setMontoPagar(rs.getInt(7));
                Entidad.setTipoFactura(rs.getString(8));
                Entidad.setCancelada(rs.getBoolean(9));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            //Cerramos la conexion 
            if (_Conexion != null) {
                ClaseConexion.close(_Conexion);
            }
        }

        return Entidad;
    }

    //Metodo para insertar un registro
    public int Insertar(Factura factura) throws Exception {
        //Creamos las variables necesarias
        int id = -1;
        Connection _conexion = null;

        //Abrimos la conexion y pasamos la sentencia
        try {
            _conexion = ClaseConexion.getConnection();
            String sentencia = "insert into Facturas (fechafactura, tipofactura) \n"
                    + "values (?, ?)";

            PreparedStatement ps = _conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, (Date) factura.getFechaFactura());
            ps.setString(2, factura.getTipoFactura());
            //Ejecutamos y guardamos la key generada
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs != null && rs.next()) {
                id = rs.getInt(1);

            }
        } catch (Exception e) {
            throw e;
        } finally {
            //Cerramos la conexion
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return id;
    }

    //Metodo para modificar un registro
    public int Modificar(Factura factura) throws Exception {
        //Creamos variables necesarias
        int resultado = -1;
        Connection _conexion = null;

        //Abrimos la conexion y creamos la sentencia 
        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement("update Facturas set TotalMonto = (SELECT SUM(PrecioUnitario * CantidadFactura) as sumPrice "
                    + "FROM DetalleFacturas "
                    + "WHERE IDFactura = ? "
                    + "GROUP BY IDFactura "
                    + ") where IDFactura = ?");

            //Pasamos datos y ejecutamos
            ps.setInt(1, factura.getIdFactura());
            ps.setInt(2, factura.getIdFactura());
            resultado = ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }

        }
        return resultado;
    }

    //Metodo igual al anterior, pero aqui la sentencia nos calcula el precio en base al descuento de 5%
    public int ModificarDesc(Factura factura) throws Exception {
        int resultado = -1;
        Connection _conexion = null;

        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement("update Facturas set Descuento = ((TotalMonto) - (TotalMonto * 0.05)) where IDFactura = ?");

            ps.setInt(1, factura.getIdFactura());
            resultado = ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }

        }
        return resultado;
    }

    //Metodo igual al anterior, pero en esta ocasion nos calcula el precio final con el IVA ya incluido
    public int ModificarTotal(Factura factura) throws Exception {
        int resultado = -1;
        Connection _conexion = null;

        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement("update Facturas set MontoPagar = ((Descuento * 0.13) + (Descuento)) where IDFactura = ? ");

            ps.setInt(1, factura.getIdFactura());
            resultado = ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }

        }
        return resultado;
    }

    //Metodo para eliminar un registro
    public int Eliminar(Factura factura) throws Exception {
        //Creamos las variables necesarias
        int resultado = -1;
        Connection _conexion = null;

        //Abrimos la conexion y generamos la sentencia a utilizar
        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement("update Facturas set Cancelada = 1 where IDFactura = ?");

            //Pasamos datos y ejecutamos
            ps.setInt(1, factura.getIdFactura());
            resultado = ps.executeUpdate();

        } catch (Exception ex) {
            throw ex;
        } finally {
            //Cerramos la conexion
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }

        }
        return resultado;
    }
}
