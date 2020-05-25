package webapp.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { // there is a GET request received from the submit Logout button
        HttpSession session = request.getSession();
        session.setAttribute("login", "false"); // unset the logged in attribute from session
        response.sendRedirect("index.jsp");
    }
}
