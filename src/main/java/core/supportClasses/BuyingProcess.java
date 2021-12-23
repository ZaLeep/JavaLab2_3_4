package core.supportClasses;

import org.apache.log4j.Logger;
import core.DBSupport.DAOs.DAOFactory;
import core.DBSupport.DAOs.entitesDAO.railway.CarriageDAO;
import core.DBSupport.DAOs.entitesDAO.service.TicketDAO;
import core.DBSupport.connectionPool.ConnectionPool;
import core.entities.railway.rollingStock.Carriage;
import core.entities.service.Ticket;

import java.sql.Connection;
import java.sql.SQLException;

public class BuyingProcess {
    private static final Logger log = Logger.getLogger(BuyingProcess.class.getName());

    public static synchronized boolean buy(Ticket ticket) {
        boolean isDone = false;
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection connection = cp.getConnection();
        try {
            connection.setAutoCommit(false);
            TicketDAO ticketDAO = DAOFactory.getTicketDAO(connection);
            ticketDAO.insert(ticket);

            CarriageDAO carriageDAO = DAOFactory.getCarriageDAO(connection);
            Carriage carriage = carriageDAO.getByNumber(ticket.getTrain_ID(), ticket.getCarriageNumber());
            if (carriage.getTotalSeats() > carriage.getReservedSeats()) {
                carriageDAO.reserveSeat(ticket.getTrain_ID(), ticket.getCarriageNumber());
                connection.commit();
                isDone = true;
                log.info("Ticket buying transaction successful");

            } else {
                isDone = false;
                connection.rollback();
                log.info("Ticket buying transaction aborted");
            }


        } catch (SQLException e) {
            log.error("Ticket buying operation failure");
            try {
                connection.rollback();
                log.info("Ticket buying transaction aborted");
            } catch (SQLException ex) {
                log.error("Rollback failure");
            }
        } finally {
            cp.freeConnection(connection);
        }
        return isDone;
    }
}
