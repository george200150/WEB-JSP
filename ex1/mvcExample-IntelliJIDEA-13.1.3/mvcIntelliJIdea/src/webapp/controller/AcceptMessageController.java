package webapp.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/accept")
public class AcceptMessageController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "UPDATE messages SET active = 1 WHERE id = ?"; // accept the message by setting the "active" flag to 1, which means the message can be shown on the main page
        int id = Integer.parseInt(request.getParameter("id")); // get the message's id from the request's parameters
        System.out.println("ID-UL: " + id);
        try {
            ServletContext application = request.getSession().getServletContext();
            Connection connection = (Connection) application.getAttribute("conexiune");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("indexAdmin.jsp"); // redirect the user from /accept to /indexAdmin
    }
}
