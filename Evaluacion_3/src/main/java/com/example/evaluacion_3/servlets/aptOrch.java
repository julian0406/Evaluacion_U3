package com.example.evaluacion_3.servlets;

import com.example.evaluacion_3.utils.MySQLConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet(name = "aceptarOrechazar", value = "/acptOrchz")
public class aptOrch extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idIncidencia = Integer.parseInt(request.getParameter("idIncidencia"));
        String nuevoEstado = request.getParameter("nuevoEstado");

        try(Connection connection = new MySQLConnection().connect()){
            PreparedStatement stmt = connection.prepareStatement("UPDATE incidencias SET estado = ? WHERE idincidencia = ?");
            stmt.setString(1, nuevoEstado);
            stmt.setInt(2, idIncidencia);

            int rowUpdate = stmt.executeUpdate();

            if (rowUpdate > 0){
                response.getWriter().write("Estado de incidencia actualizado con éxito.");
            }else{
                response.getWriter().write("Error al actualizar el estado de la incidencia.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("changeRol");
    }
}
