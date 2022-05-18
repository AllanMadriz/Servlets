package AccesoDatos;

import Entidades.Categoria;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DA_Categoria {

    //Creamos una variable mensaje
    private String _mensaje;

    //get del mensaje
    public String getMensaje() {
        return _mensaje;
    }

    //Constructor
    public DA_Categoria() {
        _mensaje = "";
    }

    //Metodo para listar los registros de la clase
    public List<Categoria> ListarRegistros(String condicion) throws SQLException, Exception {
        //Creamos una lista y sus variables
        ResultSet rs = null;
        List<Categoria> lista = new ArrayList();
        Connection _cnn = null;

        //Con un try catch manipulamos los errores
        try {
            //Se iniciar la conexion y el statement
            _cnn = ClaseConexion.getConnection();
            Statement stm = _cnn.createStatement();
            //Se crea la sentencia que se le pasara a la BD
            String sentencia = "select idproductocategoria, categoria, descripcioncategoria from ProductoCategorias";
            //Revisamos si hay alguna condicion para listar los datos
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            //Ejecutamos la sentencia
            rs = stm.executeQuery(sentencia);
            //Por cada registro que detecta se agregara a la lista
            while (rs.next()) {
                lista.add(new Categoria(rs.getInt("idproductocategoria"), rs.getString("categoria"), rs.getString("descripcioncategoria")));
            }
            //En caso de algun error nos tira la excepcion 
        } catch (Exception e) {
            throw e;
        } finally {
            _cnn = null;
        }

        return lista;
    }

    //Metodo para obtener un solo registros
    public Categoria ObtenerRegistro(String condicion) throws SQLException, Exception {
        //Creamos las variables necesarias
        Categoria categoria = new Categoria();
        ResultSet rs;
        Connection _conexion = null;
        try {
            //Se inicia la conexion, ademas preparamos la sentencia que se le va pasar a la BD
            _conexion = ClaseConexion.getConnection();
            Statement stm = _conexion.createStatement();
            String sentencia = "select idproductocategoria, categoria, descripcioncategoria from ProductoCategorias";
            //Revisamos si tiene alguna condicion para aplicar
            if (!condicion.equals("")) {
                sentencia = String.format("%s where %s", sentencia, condicion);
            }
            //Ejecutamos la sentencia
            rs = stm.executeQuery(sentencia);
            //Vamos tomando datos
            if (rs.next()) {
                categoria.setIdProductoCategoria(rs.getInt(1));
                categoria.setCategoria(rs.getString(2));
                categoria.setDescripcionCategoria(rs.getString(3));
            }
            //En caso de error lo manipulamos aca
        } catch (Exception e) {
            throw e;
        } finally {
            if (_conexion != null) {
                ClaseConexion.close(_conexion);
            }
        }

        return categoria;
    }
}
