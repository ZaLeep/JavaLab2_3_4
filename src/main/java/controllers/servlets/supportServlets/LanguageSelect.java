package controllers.servlets.supportServlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LanguageSelect",urlPatterns = "/language")
public class LanguageSelect extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language=request.getParameter("language");
        HttpSession session=request.getSession();
        session.setAttribute("language",language);
        String referer=request.getHeader("Referer");
        response.sendRedirect(referer);
    }
}
