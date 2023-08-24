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

@WebServlet(name = "AceptarAdmin", value = "/acepAdmin")
public class aceptAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idIncide = Integer.parseInt(request.getParameter("idIncide"));
        String nvEstado = request.getParameter("nvEstado");

        try(Connection connection = new MySQLConnection().connect()){
            PreparedStatement stmt = connection.prepareStatement("UPDATE incidencias SET estado = ? WHERE idincidencia = ?");
            stmt.setString(1, nvEstado);
            stmt.setInt(2, idIncide);

            int rowUpdate = stmt.executeUpdate();

            if (rowUpdate > 0){
                response.getWriter().write("Estado de incidencia actualizado con éxito.");
            }else{
                response.getWriter().write("Error al actualizar el estado de la incidencia.");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect("admin");
    }
}
