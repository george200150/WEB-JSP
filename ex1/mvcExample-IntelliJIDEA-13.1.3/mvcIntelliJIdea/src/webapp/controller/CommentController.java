package webapp.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;

@WebServlet("/comment")
public class CommentController extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username"); // get the username and the comment from the request
        String message = request.getParameter("message");

        ServletContext application = request.getSession().getServletContext();
        Connection connection = (Connection) application.getAttribute("conexiune"); // get connection as an app attr.
        String sql = "INSERT INTO messages(username, message, active) VALUES(?,?, 0)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql); // avoid SQLI
            preparedStatement.setString(1, EscapeUtils.escapeHtml(username)); // sanitize input
            preparedStatement.setString(2, EscapeUtils.escapeHtml(message)); // sanitize input
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("index.jsp"); // redirect the user from /comment to /index
    }
}
