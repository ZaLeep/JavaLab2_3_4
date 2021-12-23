package controllers.servlets.userServlets;

import core.DBSupport.DAOs.DAOFactory;
import core.DBSupport.connectionPool.ConnectionPool;
import core.DBSupport.searchEngine.Search;
import core.DBSupport.searchEngine.SearchResult;
import core.entities.railway.realEstate.Station;
import core.entities.railway.rollingStock.Train;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.util.List;

import static core.supportClasses.DateTimeSupport.parseDate;

@WebServlet(name = "Search", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ConnectionPool cp = (ConnectionPool) getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();

        String departStationName = request.getParameter("departStation");
        String destStationName = request.getParameter("destStation");

        List<String> stationNames = DAOFactory.getStationDAO(connection).getAllNames();
        if (stationNames.contains(departStationName) && stationNames.contains(destStationName)) {

            Station departStation = DAOFactory.getStationDAO(connection).getByName(departStationName);
            Station destStation = DAOFactory.getStationDAO(connection).getByName(destStationName);

            Search search = new Search(departStation, destStation, request.getParameter("date"));

            List<SearchResult> results = search.search();
            if (results.size() != 0) {
                HttpSession session = request.getSession();
                cp.freeConnection(connection);
                session.setAttribute("searchResults", results);
                response.sendRedirect("searchResults.jsp");
            }else {
                response.sendRedirect("searchError.jsp");
            }
        } else {
            response.sendRedirect("searchError.jsp");
        }

    }
}
