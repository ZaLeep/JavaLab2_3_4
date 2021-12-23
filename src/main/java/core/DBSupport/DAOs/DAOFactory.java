package core.DBSupport.DAOs;


import core.DBSupport.DAOs.entitesDAO.railway.*;
import core.DBSupport.DAOs.entitesDAO.service.RoleDAO;
import core.DBSupport.DAOs.entitesDAO.service.TicketDAO;
import core.DBSupport.DAOs.entitesDAO.service.UserDAO;

import java.sql.Connection;

public class DAOFactory {

    public static RouteDAO getRouteDAO(Connection connection) {
        return new RouteDAO(connection);
    }

    public static StationDAO getStationDAO(Connection connection) {
        return new StationDAO(connection);
    }

    public static CarriageDAO getCarriageDAO(Connection connection) {
        return new CarriageDAO(connection);
    }

    public static TrainDAO getTrainDAO(Connection connection) {
        return new TrainDAO(connection);
    }

    public static UserDAO getUserDAO(Connection connection) {
        return new UserDAO(connection);
    }

    public static RoleDAO getRoleDAO(Connection connection){ return new RoleDAO(connection);}

    public static TicketDAO getTicketDAO(Connection connection){return new TicketDAO(connection);}
}
