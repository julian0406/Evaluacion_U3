package com.example.evaluacion_3.servlets;

import com.example.evaluacion_3.model.user;
import com.example.evaluacion_3.utils.MySQLConnection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/loginServlet")
public class loginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException{
        String name = request.getParameter("name");
        String password = request.getParameter("password");

    try (Connection connection = new MySQLConnection().connect()){
        PreparedStatement stmt =connection.prepareStatement("SELECT idusuario, password, rol FROM usuarios WHERE name = ?");
        stmt.setString(1, name);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()){
            String userpasword = rs.getString("password");
            String userrol = rs.getString("rol");
            int userid = rs.getInt("idusuario");

            if(password.equals(userpasword)){
                user loggeduser = new user();
                loggeduser.setName(name);
                loggeduser.setRol(userrol);
                loggeduser.setId(userid);

                HttpSession session = request.getSession();
                session.setAttribute("userLogged", true);
                session.setAttribute("loggeduser", loggeduser);

                switch (userrol){
                    case "ADMIN_ROLE":
                        response.sendRedirect("admin");
                        break;

                    case "CHARGE_ROLE":
                        response.sendRedirect("changeRol");
                        break;

                    case "USER_ROLE":
                        response.sendRedirect("jsp/registrarIncidencia.jsp");
                        break;

                    default:
                        response.getWriter().write("Rol desconocido.");
                        break;
                }
            }else{
                response.getWriter().write("Login fallido. Por favor intenta de nuevo.");
            }
        }else{
            response.getWriter().write("Usuario no encontrado.");
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }

}

