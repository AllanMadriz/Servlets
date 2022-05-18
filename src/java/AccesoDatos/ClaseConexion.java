package AccesoDatos;

import java.sql.*;

//Clase de acceso a la BD, de aqui nos conectamos para extraer los datos
public class ClaseConexion {
    //Creamos una cadena de conexion, donde se le pasan los parametros necesarios como el host, db Name, user y password del servidor de BD que dispongamos
    private static final String connectionString = "jdbc:sqlserver://localhost:1433;databaseName=Facturacion;user=sa;password=sa;";
    
    //Hacemos la conexion junto al jdbc que estamos manipulando para la base de datos
    public static Connection getConnection() throws SQLException, ClassNotFoundException{  
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return DriverManager.getConnection(connectionString);
    }

    
    //Cerramos el resultset
    public static void close(ResultSet rs) throws SQLException{
        rs.close();
    }
    
     //Cerramos el statement
   public static void close(Statement st) throws SQLException{
        st.close();
    }
    
     //Cerramos el preparedstatement
   public static void close(PreparedStatement pst) throws SQLException{
        pst.close();
    }
    
     //Cerramos el callablestatement
   public static void close(CallableStatement cst) throws SQLException{
        cst.close();
    }
    
     //Cerramos la conexion
   public static void close(Connection conexion) throws SQLException{
        conexion.close();
    }
            
}
