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
        <link href="lib/bootstrap-datepicker/css/bootstrap-datepicker3.standalone.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/DataTables/datatables.min.css" rel="stylesheet" type="text/css"/>
        <title>Facturacion</title>
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
        
        <!--Contenedor principal-->
        <div class="container">
            <div class="row">
                <div class="col-10"><hl>Facturación</hl></div>
            </div>
            <!--Vamos a obtener la factura y los detalle de la factura segun el id brindado-->
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

            <!--Creamos el formulario para usar el servlet de AddDetalle-->
            <form action="AddDetalle" method="post">
                <div class="form-group">
                    <!--Mostramos el id de factura y la fecha del dia-->
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
                <br/>
                
                <!--En este grupo vamos a tener los campos de descripcion, cantidad, precio y existencias del producto-->
                <div class="form-group">
                    <div class="input-group">
                        <input type="hidden" id="txtIdProducto" name="txtIdProducto" value="" readonly="" class="form-control"/>

                        <input type="text" id="txtdescripcion" name="txtdescripcion" class="form-control" readonly=""
                               placeholder="Seleccione un producto" required/> &nbsp; &nbsp;

                        <a id="btnBuscarP" class="btn btn-success" data-toggle="modal" data-target="#buscarProducto">
                            <i class="fas fa-search"></i></a>&nbsp; &nbsp;

                        <input type="number" id="txtcantidad" name="txtcantidad" value="" class="form-control"
                               placeholder="Cantidad" required/> &nbsp; &nbsp;

                        <input type="number" id="txtprecio" readonly="" name="txtprecio" value="" class="form-control"
                               placeholder="Precio"/> &nbsp; &nbsp;

                        <input type="number" id="txtexistencia" readonly="" name="txtexistencia" value="" class="form-control"
                               placeholder="Existencia"/>
                    </div>
                </div>
                <br>
                <!--Boton para guardar el registro seleccionado al detalle-->
                <div class="form-group">
                    <input type="submit" name="Guardar" id="BtnGuardar" value="Agregar" class="btn btn-primary"/>
                </div>
            </form>
            <hr/>
            
            <!--Tabla con los detalles de factura-->
            <h5>Detalle de Factura</h5>
            <table id="DetalleFactura" class="table">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                        <th>Subtotal</th>
                        <th>Eliminar</th>
                    </tr>
                </thead>
                <!--Por cada registro detectado vamos a crear una fila para mostrar los datos y dar la opcion de eliminar tambien-->
                <tbody>
                    <%
                        if (DatosDetalles != null) {
                            for (DetalleFactura registroDetalle : DatosDetalles) {

                    %>
                    <tr>
                        <%                            
                            Producto p = new Producto();
                            BL_Producto blp = new BL_Producto();
                            p = blp.ObtenerRegistro("IDProducto=" + registroDetalle.getIdProducto());

                            int numfactura = registroDetalle.getIdFactura();
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
                        <!--Boton para eliminar-->
                        <td>
                            <a href="EliminarDetalle?idproducto=<%=codigop%>&idfactura=<%=numfactura%>"><i class="fas fa-trash-alt"></i></a>

                        </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
                <!--Mostramos lo que llevamos en el precio bruto de los productos-->
            <div class="float-right">
                <p class="text-danger h5">Total (no IVA): <%= total%> </p>
            </div>
            <br><br>
            
            <!--Botones para facturar y cancelar la factura-->
            <input type="button" id="BtnFacturar" value="Facturar"
                   onclick="location.href = 'ModFact?txtnumFactura=' + <%=EntidadFactura.getIdFactura()%>"
                   class="btn btn-success"/> 
            &nbsp; &nbsp;
            <input type="button" id="BtnCancelar" value="Cancelar"
                   onclick="location.href = 'CancelarFactura?txtnumFactura=' + <%=EntidadFactura.getIdFactura()%>"
                   class="btn btn-secondary"/> 

        </div>

        <!-- Modal Productos -->
        <div class="modal" id="buscarProducto" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentana">Buscar Producto</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden='true'
                                onclick="LimpiarProducto()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <!--Tabla donde mostramos los productos-->
                    <div class="modal-body">
                        <table id="tablaProductos">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Precio</th>
                                    <th>Seleccionar</th>
                                </tr>
                            </thead>
                            <!--por cada producto se hace una fila en la tabla para mostrar la informacion-->
                            <tbody>
                                <%
                                    BL_Producto logicaProductos = new BL_Producto();
                                    List<Producto> datosProductos;
                                    datosProductos = logicaProductos.ListarRegistros("");
                                    for (Producto registroP : datosProductos) {
                                %>
                                <tr>
                                    <%
                                        int codigoProducto = registroP.getIdProducto();
                                        String nombreProducto = registroP.getNombreProducto();
                                        int precio = registroP.getPrecio();
                                        int existencia = registroP.getStock();
                                    %>
                                    <td><%=codigoProducto%></td>
                                    <td><%=nombreProducto%></td>
                                    <td><%=precio%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarProducto('<%=codigoProducto%>', '<%=nombreProducto%>', '<%=precio%>', '<%=existencia%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                            <!--Boton al final del modal pra cancelar-->
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="LimpiarProducto()">
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap-datepicker/locales/bootstrap-datepicker.es.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/datatables.min.js" type="text/javascript"></script>
        <script src="lib/DataTables/DataTables-1.10.21/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <script>
                            $(document).ready(function () {
//                                Seteamos los valores de la tabla
                                $('#tablaProductos').dataTable({
                                    "lengthMenu": [[5, 15, 15, -1], [5, 10, 15, "All"]],
                                    "language": {
                                        "info": "Pagina _PAGE_de_PAGES_",
                                        "infoEmpty": "No existen Registros disponibles",
                                        "zeroRecords": "No se encuentran registros",
                                        "search": "Buscar",
                                        "infoFiltered": "",
                                        "lengthMenu": "Mostrar _MENU_Registros",
                                        "paginate": {
                                            "first": "Primero",
                                            "last": "Ultimo",
                                            "next": "Siguiente",
                                            "previous": "Anterior"
                                        }
                                    }
                                });
                            });
//Creamos una funcion para seleccionar al producto
                            function SeleccionarProducto(idProducto, Descripcion, Precio, Existencia) {
                                $("#txtIdProducto").val(idProducto);
                                $("#txtdescripcion").val(Descripcion);
                                $("#txtprecio").val(Precio);
                                $("#txtexistencia").val(Existencia);
                                $("#txtcantidad").focus();
                            }
//Creamos una funcion para limpiar los campos
                            function Limpiar() {
                                $("#txtIdCliente").val("");
                                $("#txtNombreCliente").val("");
                                $("#txtcantidad").val("");
                            }
//Creamos una funcion para limpiar los campos del producto
                            function LimpiarProducto() {
                                $("#txtIdProducto").val("");
                                $("#txtdescripcion").val("");
                                $("#txtprecio").val("");
                                $("#txtexistencia").val("");
                            }
        </script>
    </body>
</html>