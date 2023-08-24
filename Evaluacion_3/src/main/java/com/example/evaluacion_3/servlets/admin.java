package com.example.evaluacion_3.servlets;

import com.example.evaluacion_3.model.incidencia;
import com.example.evaluacion_3.utils.MySQLConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "admin", value = "/admin")
public class admin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        List<incidencia> incidenciaFinales = new ArrayList<>();

        try(Connection connection = new MySQLConnection().connect()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT idincidencia, titulo, descripcion, tipo, estado, usuarios.name FROM incidencias INNER JOIN usuarios on usuario = idusuario WHERE estado = 'Aceptado'");

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                incidencia incidencia3 = new incidencia();
                incidencia3.setId(rs.getInt("idincidencia"));
                incidencia3.setTitulo(rs.getString("titulo"));
                incidencia3.setDescripcion(rs.getString("descripcion"));
                incidencia3.setTipo(rs.getString("tipo"));
                incidencia3.setEstado(rs.getString("estado"));
                incidencia3.setUsuario(rs.getString("usuarios.name"));

                incidenciaFinales.add(incidencia3);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("incidenciaFinales", incidenciaFinales);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/adminAcp.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
