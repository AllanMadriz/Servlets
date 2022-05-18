package Servlets;

import Entidades.Factura;
import LogicaNegocio.BL_Factura;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Facturar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            //Generamos los atributos necesarios
            BL_Factura LogicaFactura = new BL_Factura();
            Factura EntidadFactura = new Factura();
            int resultado;

            //Convertimos la fecha de hoy
            Date todaysDate = new Date();
            java.sql.Date sqlStartDate = new java.sql.Date(todaysDate.getTime());

            //Seteamos los valores 
            EntidadFactura.setFechaFactura(sqlStartDate);
            EntidadFactura.setTipoFactura("Venta");

            //Insertamos la factura
            resultado = LogicaFactura.Insertar(EntidadFactura);
            
            response.sendRedirect("Frm_Facturar.jsp?txtnumFactura=" + resultado);

        } catch (Exception e) {
            out.print(e.getMessage());
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
