package com.example.evaluacion_3.utils;

import com.example.evaluacion_3.model.user;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/*", "/changeRol/*", "/jsp/registrarIncidencia.jsp"})
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession session = httpRequest.getSession(false);

        if (session == null || session.getAttribute("userLogged") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/index.jsp");
            return;
        }
            user loggeduser = (user) session.getAttribute("loggeduser");
            String requestPath = httpRequest.getRequestURI();

        if(loggeduser.getRol().equals("ADMIN_ROLE") && requestPath.startsWith(httpRequest.getContextPath() + "/admin")) {
            chain.doFilter(request, response);
        } else if(loggeduser.getRol().equals("USER_ROLE") && requestPath.startsWith(httpRequest.getContextPath() + "/jsp/registrarIncidencia.jsp")) {
            chain.doFilter(request, response);
        } else if(loggeduser.getRol().equals("CHARGE_ROLE") && requestPath.startsWith(httpRequest.getContextPath() + "/changeRol")) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/noAutorizado.jsp");
        }
    }
}
