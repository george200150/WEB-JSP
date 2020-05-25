package mvcIntelliJIdea.model;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;


public class TextFilter implements Filter {
    private String outText = null;

    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain) {
        ArrayList<String> regexuri = new ArrayList<>();
        try {
            Connection con = (Connection) request.getServletContext().getAttribute("conexiune");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT regex FROM expresii");
            while (rs.next()) {
                String msg = rs.getString("regex");
                regexuri.add(msg); // get all the reg-ex-es
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        ArrayList<Mesaj> list = new ArrayList<>();
        try {
            Connection con = (Connection) request.getServletContext().getAttribute("conexiune");
            Statement query = con.createStatement();
            ResultSet rs = query.executeQuery("SELECT id, message FROM rxmessages");
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String msg = rs.getString("message");
                list.add(new Mesaj(id, msg)); // get all the messages (filtering them will only censor the output on the screen, the messages remain unchanged in the DB)
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        try {
            PrintWriter out = response.getWriter();
            int index = 0;
            out.println("<table>");
            for(Mesaj m: list) {
                index += 1;
                String requestedText = m.getMsg();
                for (String reg : regexuri) {
                    requestedText = requestedText.replaceAll(reg, this.outText); // built-in regex based replacer (changes with "***")
                }
                System.out.println(requestedText);
                out.println("<tr><th> MESAJ " + index + ":  " + "</th></tr>");
                out.println("<tr><td>" + requestedText + "</td> </tr>");
            }
            out.println("</table>");

            chain.doFilter(request, response); // do the actual filtering
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(FilterConfig filterConfig) { // get the placeholder from the web.xml file of the characters changed by the filtering
        this.outText = filterConfig.getInitParameter("outText");
    }

    public void destroy() { }
}

