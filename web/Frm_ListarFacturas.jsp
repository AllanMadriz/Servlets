<%@page import="Entidades.Factura"%>
<%@page import="java.util.List"%>
<%@page import="LogicaNegocio.BL_Factura"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Listado de Facturas</title>
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

        <div class="container">
            <div class="card-header">
                <hl>Listado de Facturas</hl>
            </div>
            <br/>
            <!--Pequeno form donde tenemos la opcion de buscar por ID-->
            <form action="Frm_ListarFacturas.jsp" method="post">
                <div class="form-group">
                    <div class="input-group">
                        <input type="text" id="txtIdBuscar" name="txtIdBuscar" class="form-control" placeholder="Buscar por ID..."/>&nbsp; &nbsp;

                        <input type="submit" value="Buscar" name="btnBuscar" class="btn btn-primary"/>
                    </div>
                </div>
            </form>
            <hr>
            
            <!--Tabla para mostrar los registros de las facturas-->
            <table class="table">
                <thead>
                    <tr>
                        <th>Num. Factura</th>
                        <th>Monto</th>
                        <th>Descuento</th>
                        <th>Monto Total</th>
                        <th>Opciones</th>
                    </tr>
                </thead>
                <!--Por cada factura se creara una fila nueva en la tabla y se le pasaran los datos-->
                <tbody>
                    <%
                        String idBuscar = "";
                        String condicion = "";
                        if (request.getParameter("txtIdBuscar") != null && !request.getParameter("txtIdBuscar").equals("")) {
                            idBuscar = request.getParameter("txtIdBuscar");
                            condicion = "idfactura = " + idBuscar;

                        }
                        BL_Factura logica = new BL_Factura();
                        List<Factura> datos;
                        datos = logica.ListarRegistros(condicion);
                        for (Factura registro : datos) {
                    %>
                    <tr>
                        <% int num = registro.getIdFactura();%>
                        <td><%= num%></td>
                        <td><%= registro.getTotalMonto()%></td>
                        <td><%= registro.getDescuento()%></td>
                        <td><%= registro.getMontoPagar()%></td>
                        <td>
                            <a href="Frm_FacturarVer.jsp?txtnumFactura=<%= num%>">
                                <i class="fas fa-cart-plus"></i></a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
            <br>
            
            <!--Botones para agregar una nueva o volver al index-->
            <form action="Facturar" method="post">
                <div class="form-group">
                    <div class="input-group">
                        <input type="submit" name="Guardar" id="BtnGuardar" value="Agregar" class="btn btn-primary"/>
                        &nbsp; &nbsp;
                        <input type="button" id="btnRegresar" value="Regresar" onclick="location.href = 'index.html'" class="btn btn-secondary"/>
                    </div>
                </div>
            </form>
        </div>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    </body>
</html>