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

@WebServlet(name = "addStation", urlPatterns = "/addStation")
public class AddStation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        if(!request.getParameter("name").equals("") && !request.getParameter("city").equals("") && !request.getParameter("state").equals("") && !request.getParameter("country").equals("") ) {
        	Station station = new Station();
            station.setName(request.getParameter("name"));
            station.setCity(request.getParameter("city"));
            station.setState(request.getParameter("state"));
            station.setCountry(request.getParameter("country"));
            
            ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
            Connection connection = cp.getConnection();
            DAOFactory.getStationDAO(connection).insert(station);

            cp.freeConnection(connection);
            response.sendRedirect("stationsEdit.jsp");
        }
        else {
            response.sendRedirect("errorPage.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
