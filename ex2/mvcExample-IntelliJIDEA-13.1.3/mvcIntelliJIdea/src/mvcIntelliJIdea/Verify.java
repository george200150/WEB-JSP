package mvcIntelliJIdea;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Verify", urlPatterns = {"/verifiy"})
public class Verify extends HttpServlet {
    private static final long serialVersionUID = -1998450952225068314L;
    private ImageGenerator cig;
    private String captchaString;

    public void init() {
        try { // instantiate the image generator to be used to create the captcha code and the associated image
            cig = new ImageGenerator(300, 200);
            captchaString = cig.generateCaptchaString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String captchaInput = request.getParameter("captchaInput"); // get the captcha user's input through POST
        System.out.println("Server: " + captchaString + "  *vs*  Client: " + captchaInput);
        String message = "Wrong input"; // Server: tKe03e  *vs*  Client: <script>alert('0');</script> - there is no XSS
        if (captchaInput.equals(captchaString)) { // set the message according to what the user entered
            message = "Success";
        }
        captchaString = cig.generateCaptchaString();
        request.setAttribute("captcha", cig.getCaptcha(captchaString)); // the real value of the captcha code
        request.setAttribute("message", message); // the success/failure message that will be displayed the next time the page is loaded
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        // redirect the request having the captcha and the user intput to the same page, in order to reload it and print the result
    }
}