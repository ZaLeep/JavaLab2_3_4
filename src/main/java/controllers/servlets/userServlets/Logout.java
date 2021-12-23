package controllers.servlets.userServlets;

import core.entities.service.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "logout",urlPatterns = "/logout")
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session=request.getSession();
        User user=(User)session.getAttribute("user");
        user.setLogged(false);
        session.removeAttribute("user");
        session.removeAttribute("role");
        session.removeAttribute("searchResults");
        session.removeAttribute("selectedResult");
        response.sendRedirect("index.jsp");

    }
}
