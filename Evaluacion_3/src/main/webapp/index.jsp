<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>inicio sesion</title>
    <link rel="stylesheet" href="./assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="./assets/css/style.css">
    <script src="./assets/js/bootstrap.bundle.min.js"></script>
</head>
<body>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-4 mx-auto">
            <div class="card">
                <div class="card-body">
                    <h2 class="text-center">Iniciar Sesion</h2>
                    <form action="loginServlet" method="post" class="mt-4">
                        <div class="mb-3">
                            <label for="name" class="form-label">Nombre:</label>
                            <input type="text" class="form-control" id="mame" name="name" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña:</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <input type="submit" value="Aceptar" class="btn btn-primary btn-block">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

