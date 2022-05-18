package AccesoDatos;

import Entidades.Producto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DA_Productos {

    //Atributo
    private String _Mensaje = "";

    //Metodo Get
    public String getMensaje() {
        return _Mensaje;
    }

    //Constructor
    public DA_Productos() {
        _Mensaje = "";
    }

    //Metodo para insertar un registro
    public int Insertar(Producto producto) throws Exception {
        //Creamos las variables necesarias
        int resultado = -1;
        Connection _conexion = null;

        //Abrimos la conexion y generamos la sentencia con la cual se inserta el registro
        try {
            _conexion = ClaseConexion.getConnection();
            String sentencia = "insert into Productos (NombreProducto, Stock, Precio, Descripcion, IDCategoriaProducto, IDProveedor)"
                    + "values (?, ?, ?, ?, ?, ?)";

            //Pasamos datos y ejecutamos
            PreparedStatement ps = _conexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, producto.getNombreProducto());
            ps.setInt(2, producto.getStock());
            ps.setInt(3, producto.getPrecio());
            ps.setString(4, producto.getDescripcion());
            ps.setInt(5, producto.getIdCategoriaProducto());
            ps.setInt(6, producto.getIdProveedor());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs != null && rs.next()) {
                resultado = rs.getInt(1);
            }
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
    public int Modificar(Producto producto) throws Exception {
        //Creamos las variables necesarias
        int resultado = -1;
        Connection _conexion = null;

        //Abrimos la conexion y generamos la sentencia 
        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement("update Productos set NombreProducto=?, Stock=?, Precio=?, "
                    + "Descripcion=?, IDCategoriaProducto=?, IDProveedor=? where IDProducto=?");
            
            //Pasamos datos y ejecutamos la sentencia
            ps.setString(1, producto.getNombreProducto());
            ps.setInt(2, producto.getStock());
            ps.setInt(3, producto.getPrecio());
            ps.setString(4, producto.getDescripcion());
            ps.setInt(5, producto.getIdCategoriaProducto());
            ps.setInt(6, producto.getIdProveedor());
            ps.setInt(7, producto.getIdProducto());
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

    //Metodo para eliminar un registro
    public int Eliminar(Producto producto) throws Exception {
        //Creamos variables necesarias
        int resultado = -1;
        Connection _conexion = null;

        //Abrimos conexion y creamos la sentencia
        try {
            _conexion = ClaseConexion.getConnection();
            PreparedStatement ps = _conexion.prepareStatement("update Productos set EnVenta = 0 where IDProducto=?");
            
            //Pasamos datos y ejecutamos
            ps.setInt(1, producto.getIdProducto());
            resultado = ps.executeUpdate();

        } catch (Exception e) {
            throw e;
        } finally {
            //Cerramos conexion
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }
        return resultado;
    }

    //Metodo para listar registros
    public List<Producto> ListarRegistros(String condicion) throws SQLException, Exception {
        //Generamos las variables necesarias
        ResultSet rs = null;
        List<Producto> lista = new ArrayList();
        Connection _cnn = null;

        //Abrimos la conexion y creamos la sentencia a utilizar
        try {
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();

            String sentencia = "select idproducto, nombreproducto, stock, precio, descripcion, idcategoriaproducto, idproveedor, enventa"
                    + " from Productos where EnVenta = 1 ";
            //Revisamos si tenemos alguna condicion
            if (!condicion.equals("")) {
                sentencia = String.format("%s and %s", sentencia, condicion);
            }
            //Ejecutamos
            rs = stm.executeQuery(sentencia);
            //Vamos agregando a la lista segun los registros recuperados
            while (rs.next()) {
                lista.add(new Producto(rs.getInt("idproducto"), rs.getString("nombreproducto"),
                        rs.getInt("stock"), rs.getInt("precio"), rs.getString("descripcion"), rs.getInt("idcategoriaproducto"),
                        rs.getInt("idproveedor"), rs.getBoolean("enventa")));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            //Cerramos la conexion
            if (_cnn != null) {
                ClaseConexion.close(_cnn);
            }
        }

        return lista;
    }

    //Metodo para obtener un registro
    public Producto ObtenerRegistro(String condicion) throws SQLException, Exception {
        //Generamos las variables necesarias
        Producto producto = new Producto();
        ResultSet rs;
        Connection _conexion = null;
        
        //Abrimos la conexion y generamos la sentencia necesaria a utilizar
        try {
            _conexion = ClaseConexion.getConnection();
            Statement stm = _conexion.createStatement();
            String sentencia = "select idproducto, nombreproducto, stock, precio, descripcion, idcategoriaproducto, idproveedor, enventa"
                    + " from Productos";

            //Revisamos la condicion
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }

            //Ejecutamos
            rs = stm.executeQuery(sentencia);

            //Rellenamos datos
            if (rs.next()) {
                producto.setIdProducto(rs.getInt(1));
                producto.setNombreProducto(rs.getString(2));
                producto.setStock(rs.getInt(3));
                producto.setPrecio(rs.getInt(4));
                producto.setDescripcion(rs.getString(5));
                producto.setIdCategoriaProducto(rs.getInt(6));
                producto.setIdProveedor(rs.getInt(7));
                producto.setEnVenta(rs.getBoolean(8));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            //Cerramos la conexion
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }

        return producto;
    }
}
