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

@WebServlet(name = "RolCharge", value = "/changeRol")
public class chargeRol extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<incidencia> incidenciasFaltante = new ArrayList<>();

        try(Connection connection = new MySQLConnection().connect()) {
            PreparedStatement stmt = connection.prepareStatement("SELECT idincidencia, titulo, descripcion, tipo, estado, usuarios.name FROM incidencias INNER JOIN usuarios on usuario = idusuario WHERE estado = 'Pendiente'");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                incidencia incidencia2 = new incidencia();
                incidencia2.setId(rs.getInt("idincidencia"));
                incidencia2.setTitulo(rs.getString("titulo"));
                incidencia2.setDescripcion(rs.getString("descripcion"));
                incidencia2.setTipo(rs.getString("tipo"));
                incidencia2.setEstado(rs.getString("estado"));
                incidencia2.setUsuario(rs.getString("usuarios.name"));

                incidenciasFaltante.add(incidencia2);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("incidenciasFaltante", incidenciasFaltante);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/jsp/changeAcp.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
