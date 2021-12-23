package controllers.filters;

import core.DBSupport.DAOs.DAOFactory;
import core.DBSupport.connectionPool.ConnectionPool;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

@WebFilter(filterName = "RoutesEditPageUpdate")
public class RoutesEditPageUpdate implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        ConnectionPool cp = (ConnectionPool) request.getServletContext().getAttribute("DBConnection");
        Connection connection = cp.getConnection();
        HttpSession session = request.getSession();
        session.setAttribute("routes", DAOFactory.getRouteDAO(connection).getAll());
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
