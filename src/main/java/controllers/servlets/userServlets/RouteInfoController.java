package controllers.servlets.userServlets;

import core.DBSupport.DAOs.DAOFactory;
import core.DBSupport.connectionPool.ConnectionPool;
import core.DBSupport.searchEngine.RouteInfo;
import core.entities.railway.realEstate.Route;
import core.entities.railway.realEstate.Station;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "routeInfo", urlPatterns = "/routeInfo")
public class RouteInfoController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        int route_ID = Integer.parseInt(request.getParameter("route_ID"));

        Route route = DAOFactory.getRouteDAO(connection).read(route_ID);

        Station departStation = DAOFactory.getStationDAO(connection).read(route.getDepartStation_ID());
        Station destStation = DAOFactory.getStationDAO(connection).read(route.getDestStation_ID());
     
        List<Station> stations = new ArrayList<>();

        RouteInfo routeInfo = new RouteInfo();
        routeInfo.setRoute(route);
        routeInfo.setDepartStation(departStation);
        routeInfo.setDestStation(destStation);
        routeInfo.setStations(stations);
        request.setAttribute("routeInfo", routeInfo);

        cp.freeConnection(connection);

        RequestDispatcher dispatcher = request.getRequestDispatcher("routeInfo.jsp");
        dispatcher.forward(request, response);
    }
}
