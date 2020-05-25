package mvcIntelliJIdea.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/login")
public class LoginController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            ServletContext application = request.getSession().getServletContext();
            Connection connection = (Connection) application.getAttribute("conexiune");
            String sql = "SELECT password FROM users WHERE username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            if (rs.getString("password").equals(password)) {
                // password ok
                HttpSession session = request.getSession();
                session.setAttribute("login", "true");
                RequestDispatcher dispatcher=getServletContext().getRequestDispatcher( "/WEB-INF/indexAdmin.jsp" ); // make it impossible to directly access
                dispatcher.forward( request, response );
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            response.sendRedirect("index.jsp");
        }
    }
}
