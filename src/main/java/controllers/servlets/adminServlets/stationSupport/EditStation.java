package controllers.servlets.adminServlets.stationSupport;

import core.DBSupport.DAOs.DAOFactory;
import core.DBSupport.connectionPool.ConnectionPool;
import core.entities.railway.realEstate.Station;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet(name = "editStation", urlPatterns = "/editStation")
public class EditStation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Station station = new Station();
        station.setStation_ID(Integer.parseInt(request.getParameter("id")));
        station.setName(request.getParameter("name"));
        station.setCity(request.getParameter("city"));
        station.setState(request.getParameter("state"));
        station.setCountry(request.getParameter("country"));

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        DAOFactory.getStationDAO(connection).update(station);
        cp.freeConnection(connection);
        response.sendRedirect("stationsEdit.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
