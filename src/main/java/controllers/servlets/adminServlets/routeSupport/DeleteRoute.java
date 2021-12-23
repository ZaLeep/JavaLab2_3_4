package controllers.servlets.adminServlets.routeSupport;

import core.DBSupport.DAOs.DAOFactory;
import core.DBSupport.connectionPool.ConnectionPool;
import core.entities.railway.realEstate.Route;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "deleteRoute", urlPatterns = "/deleteRoute")
public class DeleteRoute extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Route route = new Route();
        route.setRoute_ID(Integer.parseInt(request.getParameter("routeID")));

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();
        DAOFactory.getRouteDAO(connection).delete(route);

        cp.freeConnection(connection);

        response.sendRedirect("routesEdit.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
