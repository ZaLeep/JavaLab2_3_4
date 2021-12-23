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

@WebServlet(name = "deleteStation", urlPatterns = "/deleteStation")
public class DeleteStation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Station station = new Station();
        station.setStation_ID(Integer.parseInt(request.getParameter("id")));

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        boolean isDeleted = DAOFactory.getStationDAO(connection).delete(station);
        if (!isDeleted){
            request.getSession().setAttribute("isStationDeleted",isDeleted);
            response.sendRedirect("deleteError.jsp");
        }
        else {
            response.sendRedirect("stationsEdit.jsp");
        }
        cp.freeConnection(connection);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
