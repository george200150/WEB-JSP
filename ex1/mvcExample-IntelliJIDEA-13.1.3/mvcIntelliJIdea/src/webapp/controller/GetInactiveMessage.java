package webapp.controller;

import webapp.model.Mesaj;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet("/getAllInactive")
public class GetInactiveMessage extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Mesaj> list = new ArrayList<>();
        try {
            Connection con = (Connection) request.getSession().getServletContext().getAttribute("conexiune");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT id, username, message FROM messages WHERE active=0"); // messages that have not been moderated yet, are shown in the admin page
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String userName = rs.getString("username");
                String msg = rs.getString("message");
                list.add(new Mesaj(id, userName, msg));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            response.sendRedirect("index.jsp");
        }
        PrintWriter outp = response.getWriter();
        for (Mesaj m : list) { // format the elements of the list so that they are displayed correctly in HTML
            outp.println("<tr><td>");
            outp.println("<form action ='/accept' method='post'>"); // add functions to the messages, so that the admins can approve the messages
            outp.println("<input type='hidden' name='id' value = '" + m.getId() + "'>");
            outp.println("<button type='submit'> Aproba </button>");
            outp.println("</form>");
            outp.println("</td><td>");
            outp.println("<strong>" + m.getUsername() + ":" + "</strong>");
            outp.println("<p>" + m.getMsg() + "</p>");
            outp.println("</td></tr>");
        }
    }
}
