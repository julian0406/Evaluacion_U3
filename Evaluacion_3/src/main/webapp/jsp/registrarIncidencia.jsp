
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="text-center mb-5">
        <h1 class="display-4">Crear Incidencia</h1>
        <a href="${pageContext.request.contextPath}/cerrarSesion" class="btn btn-danger">Cerrar Sesión</a>
    </div>
    <div class="card shadow p-4">
        <form action="${pageContext.request.contextPath}/incidenciaServlet" method="post">
            <div class="form-group">
                <label for="titulo">Titulo:</label>
                <input type="text" name="titulo" class="form-control" id="titulo" required>
            </div>
            <div class="form-group">
                <label for="descripcion">Descripcion:</label>
                <input type="text" name="descripcion" class="form-control" id="descripcion" required>
            </div>
            <div class="form-group">
                <label for="tipoIncidencia">Tipo de Incidencia:</label>
                <select name="tipoIncidencia" class="form-control" id="tipoIncidencia">
                    <option value="Falta">Falta</option>
                    <option value="Retardo">Retardo</option>
                </select>
            </div>
            <input type="submit" value="Aceptar" class="btn btn-primary">
        </form>
    </div>
    <div class="mt-4 text-center">
        <a href="${pageContext.request.contextPath}/listaIncidenciasServlet" class="button">Mis incidencias</a>
    </div>
</div>
</body>
</html>
