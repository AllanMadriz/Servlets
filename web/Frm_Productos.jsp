<%@page import="Entidades.Categoria"%>
<%@page import="LogicaNegocio.BL_Categoria"%>
<%@page import="java.util.List"%>
<%@page import="LogicaNegocio.BL_Proveedor"%>
<%@page import="Entidades.Proveedor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Entidades.Producto" %>
<%@page import="LogicaNegocio.BL_Producto" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Agregar productos</title>
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="lib/fontawesome-free-5.14.0-web/css/all.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
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
                <div class="col-md-8 mx-auto">
                    <div class="card-header">
                        <h1>Lista de Productos</h1>
                    </div>

                    <%
                        String id = request.getParameter("idCrearModificar");
                        int codigo = Integer.parseInt(id);

                        Producto entidad;
                        Proveedor prov = new Proveedor();
                        Categoria cat = new Categoria();

                        BL_Categoria logicaCat = new BL_Categoria();
                        BL_Proveedor logicaProv = new BL_Proveedor();
                        BL_Producto logica = new BL_Producto();

                        if (codigo > 0) {
                            entidad = logica.ObtenerRegistro("IdProducto=" + id);
                            prov = logicaProv.ObtenerRegistro("idproveedor=" + entidad.getIdProveedor());
                            cat = logicaCat.ObtenerRegistro("idproductocategoria=" + entidad.getIdCategoriaProducto());
                        } else {
                            entidad = new Producto();
                        }
                    %>
                    <br>
                    <form action="AddProducto" method="post">

                        <div class="form-group">
                            <input type="hidden" name="txtCodigo" id="txtCodigo" class="form-control"
                                   value="<%= entidad.getIdProducto()%>" readonly/>

                            <label for="txtNombre">Nombre</label>
                            <input type="text" name="txtNombre" id="txtNombre" 
                                   value="<%= entidad.getNombreProducto()%>" required class="form-control"/>
                        </div>  
                        <div class="form-group">
                            <label for="txtDescripcion">Descripcion</label>
                            <input type="text" name="txtDescripcion" id="txtDescripcion" 
                                   value="<%= entidad.getDescripcion()%>" required class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="txtPrecio">Precio</label>
                            <input type="number" name="txtPrecio" id="txtPrecio" 
                                   value="<%= entidad.getPrecio()%>" required class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="txtStock">Existencia</label>
                            <input type="number" name="txtStock" id="txtStock" 
                                   value="<%= entidad.getStock()%>" required class="form-control"/>
                        </div>  

                        <div class="form-group">
                            <label for="txtIdProveedor">Proveedor</label>
                            <div class="input-group">
                                <input type="number" name="txtIdProveedor" id="txtIdProveedor" 
                                       value="<%= entidad.getIdProveedor()%>" readonly required class="form-control"/>

                                <input type="text" name="txtProv" id="txtProv" 
                                       value="<%= prov.getNombreProveedor()%>" readonly required class="form-control"/>
                                <a id="btnbuscar" class="btn btn-success" data-toggle="modal" data-target="#buscarProveedor"><i class="fas fa-search"></i></a>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="txtIdCategoria">Categoria Producto</label>
                            <div class="input-group">
                                <input type="number" name="txtIdCategoria" id="txtIdCategoria" 
                                       value="<%= entidad.getIdCategoriaProducto()%>" readonly required class="form-control"/>

                                <input type="text" name="txtCat" id="txtCat" 
                                       value="<%= cat.getCategoria()%>" readonly required class="form-control"/>
                                <a id="btnbuscar" class="btn btn-success" data-toggle="modal" data-target="#buscarCategoria"><i class="fas fa-search"></i></a>
                            </div>
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Guardar" class="btn btn-primary">
                            <input type="button" id="btnRegresar" value="Regresar" onclick="location.href = 'Frm_ListarProductos.jsp'" class="btn btn-secondary"/>
                        </div>  
                    </form>
                </div>
            </div>
        </div>

        <div class="modal" id="buscarProveedor" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentana">Buscar Proveedor</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                onclick="Limpiar()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table id="tablaClientes">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    BL_Proveedor logicaP = new BL_Proveedor();
                                    List<Proveedor> datos;
                                    datos = logicaP.ListarRegistros("");
                                    for (Proveedor registroC : datos) {
                                %>
                                <tr>
                                    <% int codigoP = registroC.getIdProveedor();
                                        String nombre = registroC.getNombreProveedor();%>
                                    <td><%= codigoP%></td>
                                    <td><%= nombre%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarProveedor('<%=codigoP%>', '<%= nombre%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="Limpiar()">
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal" id="buscarCategoria" tabindex="1" role="dialog" aria-labelledby="tituloVentana">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 id="tituloVentana">Buscar Categoria</h5>
                        <button class="close" data-dismiss="modal" aria-label="Cerrar" aria-hidden="true"
                                onclick="Limpiar()">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table id="tablaClientes">
                            <thead>
                                <tr>
                                    <th>Código</th>
                                    <th>Nombre</th>
                                    <th>Seleccionar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    BL_Categoria logicaC = new BL_Categoria();
                                    List<Categoria> datosC;
                                    datosC = logicaC.ListarRegistros("");
                                    for (Categoria registroC : datosC) {
                                %>
                                <tr>
                                    <% int codigoC = registroC.getIdProductoCategoria();
                                        String nombreC = registroC.getCategoria();%>
                                    <td><%= codigoC%></td>
                                    <td><%= nombreC%></td>
                                    <td>
                                        <a href="#" data-dismiss="modal"
                                           onclick="SeleccionarCategoria('<%=codigoC%>', '<%= nombreC%>');">Seleccionar</a>
                                    </td>
                                </tr>
                                <%}%>
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-warning" type="button" data-dismiss="modal" onclick="Limpiar()">
                            Cancelar
                        </button>
                    </div>
                </div>
            </div>
        </div>

        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script>

                            function SeleccionarProveedor(idProv, nom) {
                                $("#txtIdProveedor").val(idProv);
                                $("#txtProv").val(nom);
                            }

                            function SeleccionarCategoria(idCat, nom) {
                                $("#txtIdCategoria").val(idCat);
                                $("#txtCat").val(nom);
                            }

        </script>
    </body>
</html>
