package controllers.servlets.adminServlets.routeSupport;

import core.DBSupport.DAOs.DAOFactory;
import core.DBSupport.connectionPool.ConnectionPool;
import core.entities.railway.realEstate.Route;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import static core.supportClasses.DateTimeSupport.parseDate;
import static core.supportClasses.DateTimeSupport.parseTime;

@WebServlet(name = "createRoute", urlPatterns = "/createRoute")
public class CreateRoute extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        long deptDate = parseDate(request.getParameter("deptDate"));
        long deptTime = parseTime(request.getParameter("deptTime"));
        long destDate = parseDate(request.getParameter("destDate"));
        long destTime = parseTime(request.getParameter("destTime"));
        if(deptDate < destDate || (deptDate == destDate && deptTime < destTime)) {
            Route route = new Route();
            route.setDepartStation_ID(Integer.parseInt(request.getParameter("deptStationID")));
            route.setDepartTime(deptTime + deptDate);
            route.setDestStation_ID(Integer.parseInt(request.getParameter("destStationID")));
            route.setDestTime(destDate + destTime);

            ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
            Connection connection = cp.getConnection();

            DAOFactory.getRouteDAO(connection).insert(route);

            cp.freeConnection(connection);

            response.sendRedirect("routesEdit.jsp");
        }
        else {
            response.sendRedirect("errorPage.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }


}
