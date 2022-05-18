<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.util.List"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Entidades.Producto"%>
<%@ page import="LogicaNegocio.BL_Producto"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Lista de productos</title>
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <!--Navbar de la aplicacion-->
        <header>
            <nav class="navbar navbar-expand-sm navbar-toggleable-sm navbar-light bg-white border-bottom box-shadow mb-3">
                <div class="container">
                    <a class="navbar-brand" href="index.html">Sistema Facturación </a>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target=".navbar-collapse" aria-controls="navbarSupportedContent" 
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="navbar-collapse collapse d-sm-inline-flex flex-sm-row-reverse">
                        <ul class="navbar-nav flex-grow-1">
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="index.html">Inicio</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="Frm_ListarProductos.jsp">Productos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="FrmListarClientes.jsp">Clientes</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link text-dark" href="Frm_ListarFacturas.jsp">Facturación</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <div class="container">  <!-- container y card-header son clases de BOOTSTRAP -->
            <div class="row">
                <div class="col-md-8 mx-auto">

                    <div class="card-header">
                        <h1>Listado de Productos</h1>
                    </div>
                    <br/>

                    <form action="Frm_ListarProductos.jsp" method="post">
                        <div class="form-group">
                            <div class="input-group">
                                <label for="txtnombre">Nombre de Producto</label>&nbsp; &nbsp;
                                <input type="text" id="txtnombre" name="txtnombre" placeholder="Descripción" class="form-control"/>&nbsp; &nbsp;

                                <input type="submit" value="Buscar" name="btnBuscar" class="btn btn-primary"/>
                            </div>
                        </div>
                    </form>

                    <br>

                    <%
                        if (request.getParameter("mensaje") != null
                                && !request.getParameter("mensaje").equals("")) {
                            out.print("<p style='color:red'>" + request.getParameter("mensaje") + "</p>");
                        }
                    %>
                    <!--Creamos una tabla, donde por cada registro se hara una fila y en la fila pasara la informacion correspondiente-->
                    <table class="table">
                        <tr>
                            <th style="text-align: left">Código</th>
                            <th style="text-align: left">Nombre</th>
                            <th style="text-align: left">Precio</th>
                            <th style="text-align: left">Existencia</th>
                            <th style="text-align: left">Opciones</th>
                        </tr>

                        <%
                            String nombre;
                            String condicion = "";
                            if (request.getParameter("txtnombre") != null
                                    && !request.getParameter("txtnombre").equals("")) {
                                nombre = request.getParameter("txtnombre");
                                condicion = "nombreproducto like '%" + nombre + "%'";
                            }
                            BL_Producto logica = new BL_Producto();
                            List<Producto> datos = logica.ListarRegistros(condicion);
                            for (Producto registro : datos) {

                        %>
                        <!--Aqui mostramos la informacion y los botones para la funcionalidad de los datos-->
                        <tr>
                            <%int codigo = registro.getIdProducto();%>
                            <td><%= codigo%></td>
                            <td><%= registro.getNombreProducto()%></td>
                            <td><%= registro.getPrecio()%></td>
                            <td><%= registro.getStock()%></td>
                            <td>
                                <a href="Frm_Productos.jsp?idCrearModificar=<%= codigo%>"><i class="fas fa-user-edit"></i></a> |
                                <a href="EliminarProducto?idEliminar=<%= codigo%>"><i class="fas fa-trash-alt"></i></a>
                            </td>
                        </tr>
                        <% }%>

                    </table>

                    <br>
                    <!--Aqui permitimos agregar un nuevo producto-->
                    <a href="Frm_Productos.jsp?idCrearModificar=-1" class="btn btn-primary" >Agregar Nuevo Producto</a>
                </div>
            </div>
        </div> 

        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>

    </body>
</html>

