package Servlets;

import Entidades.Cliente;
import LogicaNegocio.BL_Cliente;
import java.io.*; // Se necesita para crear una variable OUT y poder imprimir en pantalla
import java.net.URLEncoder; // Para hacer codificación de caracteres 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/EliminarCliente") 

public class EliminarCliente extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        response.setContentType("text/html;charset=UTF-8");  

        PrintWriter out = response.getWriter();
        
        try{
            //Tomamos el id del cliente a eliminar
            BL_Cliente logica = new BL_Cliente();
            
            String id = request.getParameter("idEliminar"); 

            //Seteamos el cliente con el id y por ultimo lo eliminamos
            int codigo = Integer.parseInt(id);
            Cliente cliente = new Cliente();
            cliente.setIdCliente(codigo);
            
            int resultado = logica.Eliminar(cliente);

            String mensaje = logica.getMensaje();
            
            mensaje = URLEncoder.encode(mensaje,"UTF-8");


            response.sendRedirect("FrmListarClientes.jsp?mensajeServletEliminarCliente=" + mensaje + "&resultado=" + resultado);
        }
        catch(Exception ex){
            out.print(ex.getMessage());
        }
    }
}

