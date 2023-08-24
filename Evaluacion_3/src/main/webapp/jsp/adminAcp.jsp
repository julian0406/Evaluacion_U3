<%@ page import="java.util.List" %>
<%@ page import="com.example.evaluacion_3.model.incidencia" %><%--
  Created by IntelliJ IDEA.
  User: rodri
  Date: 23/08/2023
  Time: 01:25 a. m.
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
        List<incidencia> incidenciaFinales = (List<incidencia>) request.getAttribute("incidenciaFinales");
        for(incidencia inci : incidenciaFinales) {
      %>
      <tr>
        <td><%= inci.getTitulo() %></td>
        <td><%= inci.getDescripcion() %></td>
        <td><%= inci.getTipo() %></td>
        <td><%= inci.getEstado() %></td>
        <td><%= inci.getUsuario()%></td>
        <td>
          <form action="acepAdmin" method="post">
            <input type="hidden" name="idIncide" value="<%= inci.getId()%>">
            <select name="nvEstado" class="form-control form-control-sm d-inline w-auto">
              <option value="Aceptado" <%= inci.getEstado().equals("Aceptado") ? "selected" : "" %>>Aceptado</option>
              <option value="Aprobado" <%= inci.getEstado().equals("Aprobado") ? "selected" : "" %>>Aprobado</option>
              <option value="Rechazado" <%= inci.getEstado().equals("Rechazado") ? "selected" : "" %>>Rechazado</option>
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