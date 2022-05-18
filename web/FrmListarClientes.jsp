<!--importamos los paquetes necesarios-->
<%@ page import="Entidades.Cliente" %>
<%@ page import="LogicaNegocio.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Listado de Clientes</title>
    </head>

    <body>
        <!--navbar de la aplicacion web que se presenta-->
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

        <!--Contenedor principal de la pagina-->
        <div class="container">
            <div class="card-header">
                <h1>Listado de Clientes</h1>
            </div>
            <br/>

            <!--Formulario donde llamamos a la lista-->
            <form action="FrmListarClientes.jsp" method="post">
                <!--Grupo para buscar un registro-->
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" id="txtnombre" name="txtnombre" value="" placeholder="Buscar por nombre..."
                               class="form-control"/>&nbsp; &nbsp;

                        <input type="submit" id="btnbuscar" name="btnBuscar" value="Buscar" class="btn btn-primary"/><br><br>
                    </div>
                </div>
            </form>
            <hr/>

            <!--Creamos una tabla para mostrar los datos-->
            <table class="table">
                <thead>
                    <tr id="titulos">
                        <th>ID</th>
                        <th>Cedula</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Teléfono</th>
                        <th>Opciones</th>
                    </tr>
                </thead>
                <tbody>
                    <!--Vamos a listar todos los registros o los que coincidan con la busqueda, dado el caso-->
                    <%
                        String nombre = "";
                        String condicion = "";

                        if (request.getParameter("txtnombre") != null) {
                            nombre = request.getParameter("txtnombre");
                            condicion = "Activo = 1 and NOMBRE LIKE '%" + nombre + "%'";
                        }
                        BL_Cliente logica = new BL_Cliente();
                        List<Cliente> datos;

                        datos = logica.ListarRegistros(condicion);

                        for (Cliente registro : datos) {
                    %>
                    
                    <!--Por cada registro se hara una fila de la tabla, donde ira la informacion correspondiente-->
                    <tr>
                        <%int codigo = registro.getIdCliente();%>

                        <td><%= codigo%></td>
                        <td><%= registro.getCedula()%></td>
                        <td><%= registro.getNombre()%></td>
                        <td><%= registro.getPrimerApellido() + " " + registro.getSegundoApellido()%></td>
                        <td><%= registro.getTelefono()%></td>
                        <!--Botones para eliminar o editar-->
                        <td>
                            <a href="Frm_Clientes.jsp?idCrearModificar=<%=codigo%>"><i class="fas fa-user-edit"></i></a>
                            <a href="EliminarCliente?idEliminar=<%=codigo%>"><i class="fas fa-trash-alt"></i></a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <br>
            <!--Mensaje para eliminar un cliente-->
            <%
                if (request.getParameter("mensajeServletEliminarCliente") != null) {
                    out.print("<p class='text-danger'>" + new String(request.getParameter("mensajeServletEliminarCliente").getBytes("ISO-8859-1"), "UTF-8") + "</p>");
                }
            %>
            <!--Boton para agregar un cliente-->
            <a href="Frm_Clientes.jsp?idCrearModificar=-1" class="btn btn-primary">Agregar Cliente</a> 

        </div>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>

    </body>
</html>





