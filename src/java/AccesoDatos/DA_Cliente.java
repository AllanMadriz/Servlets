package AccesoDatos;

import Entidades.Cliente;

import java.sql.*;
import java.util.*;

public class DA_Cliente {

    //Creamos una variable mensaje
    private String _mensaje;

    //get del mensaje
    public String getMensaje() {
        return _mensaje;
    }

    //Constructor
    public DA_Cliente() {
        _mensaje = "";
    }

    //Metodo para insertar los registros
    public int Insertar(Cliente cliente) throws Exception {

        //Creamos las variables necesarias
        int resultado = -1;
        Connection _conexion = null;

        //Se inicia la conexion y se prepara la sentencia a llamar en la BD
        try {
            _conexion = ClaseConexion.getConnection();
            String sentencia = "insert into Clientes (Cedula, Nombre, PrimerApellido, SegundoApellido, DireccionResidencia, FechaNacimiento, Telefono, Genero) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?)";

            //Pasamos los datos necesarios para la sentencia y sus valores
            PreparedStatement ps = _conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, cliente.getCedula());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getPrimerApellido());
            ps.setString(4, cliente.getSegundoApellido());
            ps.setString(5, cliente.getDireccionResidencia());
            ps.setDate(6, (java.sql.Date) cliente.getFechaNacimiento());
            ps.setInt(7, cliente.getTelefono());
            ps.setString(8, cliente.getGenero());

            //Ejecutamos la sentencia
            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                resultado = rs.getInt(1);
            }
            //En caso ed un error lo manipulamos aca
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

    //Metodo para modificar un registro
    public int Modificar(Cliente cliente) throws Exception {
        //Creamos las variables necesarias
        int resultado = -1;
        Connection _conexion = null;

        //Se inicia la conexion y preparmos el statement con la sentencia
        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement("update Clientes set Cedula=?, Nombre=?, PrimerApellido=?, SegundoApellido=?, DireccionResidencia=?,"
                    + " FechaNacimiento=?, Telefono=?, Genero=? where IDCliente = ?");

            //Pasamos loa valores necesarios para la sentencia
            ps.setInt(1, cliente.getCedula());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getPrimerApellido());
            ps.setString(4, cliente.getSegundoApellido());
            ps.setString(5, cliente.getDireccionResidencia());
            ps.setDate(6, (java.sql.Date) cliente.getFechaNacimiento());
            ps.setInt(7, cliente.getTelefono());
            ps.setString(8, cliente.getGenero());
            ps.setInt(9, cliente.getIdCliente());
            //Ejecutamos la query
            resultado = ps.executeUpdate();
            //En caso de error lo manipulamos
        } catch (Exception ex) {
            throw ex;
        } finally {
            //Cerramos conexion
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }

        }
        return resultado;
    }

    //Metodo para eliminar un registro
    public int Eliminar(Cliente cliente) throws Exception {
        //Creamos las variables necesarias
        int resultado = -1;
        Connection _conexion = null;

        //Iniciamos conexion y preparamos la sentencia
        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement("update Clientes set activo = 0 where idcliente=?");

            //Pasamos datos y ejecutamos
            ps.setInt(1, cliente.getIdCliente());
            resultado = ps.executeUpdate();
            //Atrapamos el error
        } catch (Exception ex) {
            throw ex;
        } finally {
            //Cerramos conexion
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }

        }
        return resultado;
    }

    //Metodo para listar los registros de la clase
    public List<Cliente> ListarRegistros(String condicion) throws SQLException, Exception {
        //Creamos variables necesarias
        ResultSet rs = null;
        List<Cliente> lista = new ArrayList();
        Connection _cnn = null;

        //Se crea la conexion con el statement 
        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            String sentencia = "select idcliente, idusuario, cedula, nombre, primerapellido, segundoapellido, direccionresidencia, fechanacimiento, telefono, genero, activo "
                    + "from Clientes where activo = 1";
            //Verificamos si hay alguna condicion
            if (!condicion.equals("")) {
                sentencia = String.format("%s and %s", sentencia, condicion);
            }
            //Ejecutamos la sentencia
            rs = stm.executeQuery(sentencia);
            //Vamos agregando a la lista segun registros nos trae
            while (rs.next()) {
                lista.add(new Cliente(rs.getInt("cedula"), rs.getString("nombre"),
                        rs.getString("primerapellido"), rs.getString("segundoapellido"), rs.getString("direccionresidencia"), rs.getDate("fechanacimiento"),
                        rs.getInt("telefono"), rs.getString("genero"), rs.getInt("idusuario"), rs.getBoolean("activo"), rs.getInt("idcliente")));
            }
            //Atrapamos el error
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }

        return lista;
    }

    //Metodo para obtener un unico registro, se maneja igual que el de listar, la diferencia es que retorna un objeto no una lista de objetos
    public Cliente ObtenerRegistro(String Condicion) throws Exception {

        ResultSet rs = null;
        Cliente cliente = new Cliente();
        String sentencia;
        Connection _conexion = null;
        try {
            _conexion = ClaseConexion.getConnection();
            Statement st = _conexion.createStatement();

            sentencia = "select idcliente, idusuario, cedula, nombre, primerapellido, segundoapellido, direccionresidencia, fechanacimiento, telefono, genero, activo "
                    + "from Clientes";
            if (!Condicion.equals("")) {
                sentencia = String.format("%s WHERE %s", sentencia, Condicion);
            }
            rs = st.executeQuery(sentencia);

            if (rs.next()) {
                cliente.setIdCliente(rs.getInt(1));
                cliente.setIdUsuario(rs.getInt(2));
                cliente.setCedula(rs.getInt(3));
                cliente.setNombre(rs.getString(4));
                cliente.setPrimerApellido(rs.getString(5));
                cliente.setSegundoApellido(rs.getString(6));
                cliente.setDireccionResidencia(rs.getString(7));
                cliente.setFechaNacimiento(rs.getDate(8));
                cliente.setTelefono(rs.getInt(9));
                cliente.setGenero(rs.getString(10));
                cliente.setActivo(rs.getBoolean(11));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return cliente;
    }
}
