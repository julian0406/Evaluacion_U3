package com.example.evaluacion_3.servlets;

import com.example.evaluacion_3.model.user;
import com.example.evaluacion_3.utils.MySQLConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "registrarincidencia", value = "/incidenciaServlet")
public class incidenciaServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String tipo = request.getParameter("tipoIncidencia");

        user loggeduser = (user) request.getSession().getAttribute("loggeduser");
        int idusuario = loggeduser.getId();

        try(Connection connection = new MySQLConnection().connect()){
            PreparedStatement stmt = connection.prepareStatement("INSERT INTO incidencias(titulo, descripcion, tipo, usuario) VALUES(?,?,?,?)");
            stmt.setString(1, titulo);
            stmt.setString(2, descripcion);
            stmt.setString(3, tipo);
            stmt.setInt(4, idusuario);

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                response.getWriter().write("Incidencia registrada con éxito.");
            } else {
                response.getWriter().write("Error al registrar la incidencia.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
