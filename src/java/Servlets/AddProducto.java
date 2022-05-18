package Servlets;

import Entidades.Producto;
import LogicaNegocio.BL_Producto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddProducto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        try {
            
            //Generamos las variables necesarias
            BL_Producto logica = new BL_Producto();

            Producto producto = new Producto();

            int resultado;
            
            //Seteamos los atributos segun los valores tomados del formulario
            producto.setIdProducto(Integer.parseInt(request.getParameter("txtCodigo")));

            producto.setNombreProducto(new String(request.getParameter("txtNombre").getBytes("ISO-8859-1"), "UTF-8"));

            producto.setStock(Integer.parseInt(request.getParameter("txtStock")));

            producto.setPrecio(Integer.parseInt(request.getParameter("txtPrecio")));

            producto.setDescripcion(new String(request.getParameter("txtDescripcion").getBytes("ISO-8859-1"), "UTF-8"));

            producto.setIdCategoriaProducto(Integer.parseInt(request.getParameter("txtIdCategoria")));

            producto.setIdProveedor(Integer.parseInt(request.getParameter("txtIdProveedor")));

            //Si el id existe se modifica y si no se inserta
            if (producto.getIdProducto() > 0) {
                resultado = logica.Modificar(producto);
            } else {
                resultado = logica.Insertar(producto);
            }
            
            //Redireccionamos al final
            response.sendRedirect("Frm_ListarProductos.jsp");

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
