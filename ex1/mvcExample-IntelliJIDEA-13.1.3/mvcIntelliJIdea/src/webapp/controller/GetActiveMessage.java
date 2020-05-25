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

@WebServlet("/getAllActive")
public class GetActiveMessage extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Mesaj> list = new ArrayList<>();
        try {
            Connection con = (Connection) request.getSession().getServletContext().getAttribute("conexiune");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT id, username, message FROM messages WHERE active=1"); // messages that have been approved, are show on the index, for everyone
            while (rs.next()) { // iter through the result set and add the information to the messages list
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
        for (Mesaj m : list) { // print the messages as simple html entities
            outp.println("<tr><th><hr/>" + m.getUsername() + ":" + "</th></tr>");
            outp.println("<tr><td>" + m.getMsg() + "</td> </tr>");
        }
    }
}
