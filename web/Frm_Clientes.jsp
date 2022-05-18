<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="LogicaNegocio.BL_Cliente" %>
<%@ page import="Entidades.Cliente" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="lib/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Mantenimiento de Clientes</title>

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

        <!--Contenedor main de la pagina-->
        <div class="container">
            <div class="row">
                <div class="col-md-8 mx-auto">
                    <div class="card-header">
                        <h1>Mantenimiento de  Clientes</h1>
                    </div>
                    <!--Vamos a obtener el cliente en base al id que se nos dio--> 
                    <!--En caso de no tener haremos un registro nuevo-->
                    <%
                        String id = request.getParameter("idCrearModificar");
                        int codigo = Integer.parseInt(id);
                        Cliente cliente;
                        BL_Cliente logica = new BL_Cliente();

                        if (codigo > 0) {
                            cliente = logica.ObtenerRegistro("IDCliente=" + id);
                        } else {
                            cliente = new Cliente();
                        }
                    %>
                    <!--Formulario donde se llama al servlet y recolectamos los datos--> 
                    <form action="CrearModificarCliente" method="post" id="form_AgregarModificar">

                        <div class="form-group">
                            <%if (codigo > 0) {%>
                            <label for="txtCodigo" class="control-label">Código</label>
                            <input type="number" id="txtCodigo" name="txtCodigo" value="<%=cliente.getIdCliente()%>" readonly class="form-control"/><br>
                            <%} else {%>
                            <input type="hidden" id="txtCodigo" name="txtCodigo" value="-1"/><br>
                            <%}%>
                        </div>

                        <!-- form-group para los controles de Cedula -->
                        <div class="form-group">
                            <label for="txtCedula" class="control-label">Cedula</label>
                            <input type="number" id="txtCedula" name="txtCedula" value="<%=cliente.getCedula()%>" class="form-control"/><br>
                        </div>

                        <!-- form-group para los controles de Nombre -->
                        <div class="form-group">
                            <label for="txtNombre" class="control-label">Nombre</label>
                            <input type="text" id="txtNombre" name="txtNombre" value="<%=cliente.getNombre()%>" class="form-control"/><br>
                        </div>

                        <!-- form-group para los controles de Primer Apellido -->
                        <div class="form-group">
                            <label for="txtPrimerApellido" class="control-label">Primer Apellido</label>
                            <input type="text" id="txtPrimerApellido" name="txtPrimerApellido" value="<%=cliente.getPrimerApellido()%>" class="form-control"/><br>
                        </div>

                        <!-- form-group para los controles de Segundo Apellido -->
                        <div class="form-group">
                            <label for="txtSegundoApellido" class="control-label">Segundo Apellido</label>
                            <input type="text" id="txtSegundoApellido" name="txtSegundoApellido" value="<%=cliente.getSegundoApellido()%>" class="form-control"/><br>
                        </div>

                        <!-- form-group para los controles de Dirección -->
                        <div class="form-group">
                            <label for="txtDireccion" class="control-label">Dirección</label>
                            <input type="txt" id="txtDireccion" name="txtDireccion" value="<%=cliente.getDireccionResidencia()%>" class="form-control"/><br>
                        </div>


                        <!-- form-group para los controles de Fecha -->
                        <div class="form-group">
                            <label for="txtFecha" class="control-label">Fecha Nacimiento</label>
                            <input type="date" id="txtFecha" name="txtFecha" value="<%=cliente.getFechaNacimiento()%>" class="form-control" />
                            <br>
                        </div>

                        <!-- form-group para los controles de Teléfono -->
                        <div class="form-group">
                            <label for="txtTelefono" class="control-label">Teléfono</label>
                            <input type="number" id="txtTelefono" name="txtTelefono" value="<%=cliente.getTelefono()%>" class="form-control" placeholder="00-00-00-00"/><br>
                        </div>

                        <!-- form-group para los controles de Genero -->
                        <div class="form-group">
                            <label for="txtGenero" class="control-label">Genero</label>
                            <input type="text" id="txtGenero" name="txtGenero" value="<%=cliente.getGenero()%>" class="form-control" />
                            <br>
                        </div>

                        <!-- form-group para los BOTONES de guardar y regresar  -->
                        <div class="form-group">
                            <div class="input-group">
                                <input type="submit" id="btnGuardar" value="Guardar" class="btn btn-primary"/> &nbsp;&nbsp;
                                <input type="button" id="btnRegresar" value="Regresar" onclick="location.href = 'FrmListarClientes.jsp'" class="btn btn-secondary"/>

                            </div>
                        </div>

                    </form>

                </div> <!-- clase que crea las 6 columnas -->

            </div> <!-- class row, div de la fila -->

        </div> <!-- class container -->

        <script src="lib/bootstrap/dist/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <script src="lib/jquery/dist/jquery.min.js" type="text/javascript"></script>
        <script src="lib/jquery-validation/dist/jquery.validate.min.js" type="text/javascript"></script>

        <script>

                                    $(document).ready(function () {
//                                        Validamos un poco los campos y retornamos un mensaje si es necesario
                                        $("#form_AgregarModificar").validate({
                                            rules: {

                                                txtNombre: {required: true, maxlength: 50},
                                                txtTelefono: {required: true, minlength: 8, maxlength: 11},

                                                txtDireccion: {required: true, maxlength: 80}
                                            },
                                            messages: {
                                                txtNombre: "El campo de Nombre es obligatorio (max 50 caracteres)",
                                                txtTelefono: "El campo Teléfono es obligatorio (mínimo 8 caracteres, máximo 11)",
                                                txtDireccion: "El campo Dirección es obligatorio (max 80 caracteres)"
                                            },
                                            errorElement: 'span'

                                        });
                                    });
        </script>

    </body>
</html>
