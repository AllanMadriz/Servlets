package Servlets;

import Entidades.Cliente;
import LogicaNegocio.BL_Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CrearModificarCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            //Creamos las variables necesarias
            BL_Cliente Logica = new BL_Cliente();
            Cliente cliente = new Cliente();
            int resultado;

            //Seteamos los atributos segun los valores tomados de la peticion
            cliente.setIdCliente(Integer.parseInt(request.getParameter("txtCodigo")));

            cliente.setCedula(Integer.parseInt(request.getParameter("txtCedula")));

            cliente.setNombre(new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8"));

            cliente.setPrimerApellido(new String(request.getParameter("txtPrimerApellido").getBytes("ISO-8859-1"), "UTF-8"));

            cliente.setSegundoApellido(new String(request.getParameter("txtSegundoApellido").getBytes("ISO-8859-1"), "UTF-8"));

            cliente.setDireccionResidencia(new String(request.getParameter("txtDireccion").getBytes("ISO-8859-1"), "UTF-8"));

            cliente.setFechaNacimiento(new java.sql.Date(request.getDateHeader("txtFecha")));

            cliente.setTelefono(Integer.parseInt(request.getParameter("txtTelefono")));

            cliente.setGenero(new String(request.getParameter("txtGenero").getBytes("ISO-8859-1"), "UTF-8"));

            //Si el id existe modificamos si no insertamos
            if (cliente.getIdCliente() > 0) {
                resultado = Logica.Modificar(cliente);
            } else {
                resultado = Logica.Insertar(cliente);
            }
            response.sendRedirect("FrmListarClientes.jsp");

        } catch (Exception ex) {
            out.print(ex.getMessage());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
