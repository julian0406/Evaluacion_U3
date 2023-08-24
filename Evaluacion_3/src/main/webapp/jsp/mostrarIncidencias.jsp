<%@ page import="com.example.evaluacion_3.model.incidencia" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: rodri
  Date: 22/08/2023
  Time: 08:29 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lista de incidencias</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="text-center mb-5">
        <h1 class="display-4">Mis Incidencias</h1>
        <a href="${pageContext.request.contextPath}/cerrarSesion" class="btn btn-danger">Cerrar Sesión</a>
    </div>
    <div class="card shadow p-4">
    <table class="table custom-table table-bordered table-hover mb-5">
        <thead class="table-dark">
        <tr>
            <th>Titulo</th>
            <th>Descripcion</th>
            <th>Tipo</th>
            <th>Estado</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<incidencia> listaIncidencias = (List<incidencia>) request.getAttribute("listaIncidencias");
            for(incidencia incidencia1 : listaIncidencias) {
        %>
        <tr>
            <td><%= incidencia1.getTitulo() %></td>
            <td><%= incidencia1.getDescripcion() %></td>
            <td><%= incidencia1.getTipo() %></td>
            <td><%= incidencia1.getEstado() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    </div>
    <div class="mt-4 text-center">
        <a href="jsp/registrarIncidencia.jsp" class="button">Registrar Incidencia</a>
    </div>
</div>
</body>
</html>
