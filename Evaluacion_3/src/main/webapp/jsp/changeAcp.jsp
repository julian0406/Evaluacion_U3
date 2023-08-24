<%@ page import="java.util.List" %>
<%@ page import="com.example.evaluacion_3.model.incidencia" %><%--
  Created by IntelliJ IDEA.
  User: rodri
  Date: 22/08/2023
  Time: 10:39 p. m.
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Incidencias</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="text-center mb-5">
        <h1 class="display-4">Incidencias Pendientes</h1>
        <a href="${pageContext.request.contextPath}/cerrarSesion" class="btn btn-danger">Cerrar Sesión</a>
    </div>
    <div class="card shadow p-4">
        <table class="table table-bordered table-hover mb-5">
            <thead class="table-dark">
            <tr>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Tipo</th>
                <th>Estado</th>
                <th>Usuario</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <%
                List<incidencia> incidenciasFaltante = (List<incidencia>) request.getAttribute("incidenciasFaltante");
                for(incidencia inc : incidenciasFaltante) {
            %>
            <tr>
                <td><%= inc.getTitulo() %></td>
                <td><%= inc.getDescripcion() %></td>
                <td><%= inc.getTipo() %></td>
                <td><%= inc.getEstado() %></td>
                <td><%= inc.getUsuario() %></td>
                <td>
                    <form action="acptOrchz" method="post">
                        <input type="hidden" name="idIncidencia" value="<%= inc.getId()%>">
                        <select name="nuevoEstado" class="form-control form-control-sm d-inline w-auto">
                            <option value="Pendiente" <%= inc.getEstado().equals("Pendiente") ? "selected" : "" %>>Pendiente</option>
                            <option value="Aceptado" <%= inc.getEstado().equals("Aceptado") ? "selected" : "" %>>Aceptado</option>
                            <option value="Rechazado" <%= inc.getEstado().equals("Rechazado") ? "selected" : "" %>>Rechazado</option>
                        </select>
                        <input type="submit" value="Actualizar" class="btn btn-primary btn-sm ml-2">
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
