package com.example.evaluacion_3.servlets;

import com.example.evaluacion_3.model.incidencia;
import com.example.evaluacion_3.model.user;
import com.example.evaluacion_3.utils.MySQLConnection;
import jakarta.servlet.RequestDispatcher;
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

@WebServlet(name = "listaincidencias", value = "/listaIncidenciasServlet")
public class listaIncidencias extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        user loggeduser = (user) request.getSession().getAttribute("loggeduser");
        int usuarioid = loggeduser.getId();

        List<incidencia> listaIncidencias = new ArrayList<>();

        try(Connection connection = new MySQLConnection().connect()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT titulo, descripcion, tipo, estado FROM incidencias WHERE usuario = ?");
            stmt.setInt(1, usuarioid);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                incidencia incidencia1 = new incidencia();
                incidencia1.setTitulo(rs.getString("titulo"));
                incidencia1.setDescripcion(rs.getString("descripcion"));
                incidencia1.setTipo(rs.getString("tipo"));
                incidencia1.setEstado(rs.getString("estado"));

                listaIncidencias.add(incidencia1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("listaIncidencias", listaIncidencias);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/mostrarIncidencias.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
