<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="LogicaNegocio.*" %>
<%@page import="Entidades.*" %>

<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <title>Facturacion</title>
    </head>
    <body>
        <!--Navbar de la apliacion-->
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
            <div class="row">
                <div class="col-10"><hl>Facturación</hl></div>

            </div>
            <!--Obtenemos los datos de la factura y sus detalles-->
            <%
                int numFactura = -1;
                int total = 0;
                Factura EntidadFactura = new Factura();
                BL_Factura logicaFactura = new BL_Factura();
                BL_Detalle logicaDetalle = new BL_Detalle();
                List<DetalleFactura> DatosDetalles = null;
                if (request.getParameter("txtnumFactura") != null && Integer.parseInt(request.getParameter("txtnumFactura")) != -1) {
                    numFactura = Integer.parseInt(request.getParameter("txtnumFactura"));
                    EntidadFactura = logicaFactura.ObtenerRegistro("IDFactura=" + numFactura);
                    DatosDetalles = logicaDetalle.ListarRegistros("IDFactura=" + numFactura);
                }
            %>
            <br>
            
            <form action="AddDetalle" method="post">
                <!--Mostramos los valores del id factura y la fecha que posee-->
                <div class="form-group">
                    <div class="input-group">
                        <label for="txtnumFactura" class="form-control">Num. Factura</label>
                        <input type="text" id="txtnumFactura" name="txtnumFactura" value="<%=EntidadFactura.getIdFactura()%>"
                               readonly class="form-control"/>
                        &nbsp; &nbsp;
                        <label for="txtFechaFactura" class="form-control">Fecha</label>
                        <input type="text" id="txtFechaFactura" name="txtFechaFactura" readonly value="<%=EntidadFactura.getFechaFactura()%>"
                               required class="datepicker form-control"/>
                    </div>

                </div>

                <br>
            </form>
            <hr/>
            <!--En esta tabla vamos a mostrar todos los detalles-->
            <h5>Detalle de Factura</h5>
            <table id="DetalleFactura" class="table">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                        <th>Subtotal</th>
                    </tr>
                </thead>
                <!--Por cada detalle detectado se creara una fila en la tabla-->
                <tbody>
                    <%
                        if (DatosDetalles != null) {
                            for (DetalleFactura registroDetalle : DatosDetalles) {

                    %>
                    <tr>
                        <%                            Producto p = new Producto();
                            BL_Producto blp = new BL_Producto();
                            p = blp.ObtenerRegistro("IDProducto=" + registroDetalle.getIdProducto());

                            int codigop = registroDetalle.getIdProducto();
                            String descripcion = p.getNombreProducto();
                            int cantidad = registroDetalle.getCantidadFacturada();
                            int precioV = registroDetalle.getPrecioUnidad();
                            total += (cantidad * precioV);
                        %>
                        <td><%= codigop%></td>
                        <td><%= descripcion%></td>
                        <td><%= cantidad%></td>
                        <td><%= precioV%></td>
                        <td><%= cantidad * precioV%></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
            <div class="float-right">
                <p class="text-danger h5">Total (no IVA): <%= total%> </p>
            </div>
            <br><br>

            <!--Boton para poder devolvernos a la lista-->
            <a href="Frm_ListarFacturas.jsp" class="btn btn-primary" >Volver</a>

        </div>

        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>

    </body>
</html>