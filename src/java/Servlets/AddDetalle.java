package Servlets;

import Entidades.DetalleFactura;
import LogicaNegocio.BL_Detalle;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddDetalle extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //Creamos las variables necesarias
            BL_Detalle logica = new BL_Detalle();
            DetalleFactura detalle = new DetalleFactura();
            int resultado;

            //Asignamos los atributos en base a los valores retornados
            detalle.setIdFactura(Integer.parseInt(request.getParameter("txtnumFactura")));
            detalle.setIdProducto(Integer.parseInt(request.getParameter("txtIdProducto")));
            detalle.setCantidadFacturada(Integer.parseInt(request.getParameter("txtcantidad")));
            detalle.setPrecioUnidad(Integer.parseInt(request.getParameter("txtprecio")));
            detalle.setTotal(detalle.getCantidadFacturada() * detalle.getPrecioUnidad());
            
            //Insertamos el registro
            resultado = logica.Insertar(detalle);
            
            //Redireccionamos
            response.sendRedirect("Frm_Facturar.jsp?txtnumFactura=" + request.getParameter("txtnumFactura"));
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
