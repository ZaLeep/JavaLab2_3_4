package controllers.listeners;

import org.apache.log4j.LogManager;
import core.DBSupport.connectionPool.ConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class AppInitListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {

        ServletContext sc = sce.getServletContext();
        String driver = sc.getInitParameter("DB_Driver");
        String URL = sc.getInitParameter("DB_URL");
        String user = sc.getInitParameter("DB_User");
        String password = sc.getInitParameter("DB_Password");
        int maxConn = Integer.parseInt(sc.getInitParameter("Max_Conn"));
        ConnectionPool connectionPool = ConnectionPool.getInstance(driver, URL, user, password, maxConn);
        sc.setAttribute("DBConnection", connectionPool);

        sc.setInitParameter("language", "en");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        ConnectionPool connectionPool = (ConnectionPool) sce.getServletContext().getAttribute("DBConnection");
        connectionPool.release();
        LogManager.shutdown();
    }

}
