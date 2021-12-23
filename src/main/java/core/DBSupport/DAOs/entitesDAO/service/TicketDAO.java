package core.DBSupport.DAOs.entitesDAO.service;

import core.DBSupport.DAOs.AbstractDAO;
import core.entities.service.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO extends AbstractDAO<Ticket> {
    @Override
    protected String getInsertQuery() {
        return "INSERT INTO tickets (user_ID, train_ID, carriageNumber, depStation_ID, depTime, destStation_ID, destTime, price)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM tickets WHERE ticket_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE tickets\n" +
                "SET ticket_ID = ?, user_ID = ?, train_ID = ?, carriageNumber = ?, depStation_ID = ?, depTime = ?, destStation_ID = ?,\n" +
                "  destTime    = ?, price = ?\n" +
                "WHERE ticket_ID = ?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM tickets WHERE ticket_ID=?";
    }

    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM tickets";
    }

    @Override
    protected List<Ticket> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Ticket> tickets = new ArrayList<>();
        while (resultSet.next()) {
            Ticket ticket = new Ticket();
            ticket.setTicket_ID(resultSet.getInt(1));
            ticket.setTrain_ID(resultSet.getInt(2));
            ticket.setUser_ID(resultSet.getInt(3));
            ticket.setCarriageNumber(resultSet.getInt(4));
            ticket.setDeptStation_ID(resultSet.getInt(5));
            ticket.setDeptTime(resultSet.getTimestamp(6).getTime());
            ticket.setDestStation_ID(resultSet.getInt(7));
            ticket.setDestTime(resultSet.getTimestamp(8).getTime());
            ticket.setPrice(resultSet.getDouble(9));
            tickets.add(ticket);
        }
        return tickets;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, Ticket object) throws SQLException {
        statement.setInt(1, object.getUser_ID());
        statement.setInt(2, object.getTrain_ID());
        statement.setInt(3, object.getCarriageNumber());
        statement.setInt(4, object.getDeptStation_ID());
        statement.setTimestamp(5, new Timestamp(object.getDeptTime()));
        statement.setInt(6, object.getDestStation_ID());
        statement.setTimestamp(7, new Timestamp(object.getDestTime()));
        statement.setDouble(8, object.getPrice());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, Ticket object) throws SQLException {
        statement.setInt(1, object.getTicket_ID());
        statement.setInt(2, object.getUser_ID());
        statement.setInt(3, object.getTrain_ID());
        statement.setInt(4, object.getCarriageNumber());
        statement.setInt(5, object.getDeptStation_ID());
        statement.setTimestamp(6, new Timestamp(object.getDeptTime()));
        statement.setInt(7, object.getDestStation_ID());
        statement.setTimestamp(8, new Timestamp(object.getDestTime()));
        statement.setDouble(9, object.getPrice());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, Ticket object) throws SQLException {
        statement.setInt(1, object.getTicket_ID());
    }

    public TicketDAO(Connection connection) {
        super.connection = connection;
    }
}
