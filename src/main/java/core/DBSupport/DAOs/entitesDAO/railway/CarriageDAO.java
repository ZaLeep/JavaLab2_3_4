package core.DBSupport.DAOs.entitesDAO.railway;

import core.DBSupport.DAOs.AbstractDAO;
import core.entities.railway.rollingStock.Carriage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CarriageDAO extends AbstractDAO<Carriage> {

    public boolean reserveSeat(int train_ID, int carriageNumber) {
        boolean isExecuted;
        String sql = "UPDATE carriages SET reservedSeats=reservedSeats+1 WHERE train_ID=? AND carriageNumber=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train_ID);
            statement.setInt(2, carriageNumber);
            isExecuted = statement.execute();
        } catch (SQLException e) {
            log.error("Seat reserving failure", e);
            return false;
        }
        return isExecuted;
    }

    public Carriage getByNumber(int train_ID, int carriageNumber) {
        Carriage carriage = null;
        String sql = "SELECT * FROM carriages WHERE train_ID=? AND carriageNumber=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train_ID);
            statement.setInt(2, carriageNumber);
            ResultSet rs = statement.executeQuery();
            List<Carriage> carriages = parseResultSet(rs);
            carriage = carriages.iterator().next();
        } catch (SQLException e) {
            log.error("Can't get cattiage by number", e);
        }
        return carriage;
    }

    public List<Carriage> getNotFullCarriages(int train_ID) {
        List<Carriage> carriages = null;
        String sql = "SELECT * FROM carriages WHERE train_ID = ? AND reservedSeats < totalSeats";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, train_ID);
            ResultSet rs = statement.executeQuery();
            carriages = parseResultSet(rs);
        } catch (SQLException e) {
            log.error("Can't get not full carriages", e);
        }

        return carriages;
    }

    public boolean deleteByTrainID(int id) {
        String sql = "DELETE FROM carriages WHERE train_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            log.info("Can't delete carriages", e);
            return false;
        }
        return true;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO carriages (train_ID, carriageNumber, c_type, totalSeats, reservedSeats) VALUES(?,?,?,?,?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM carriages WHERE carriage_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE carriages SET train_ID=?, carriageNumber=?, c_type=?, totalSeats=?, reservedSeats=? WHERE carriage_ID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM carriages WHERE carriage_ID=?";
    }

    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM carriages";
    }

    @Override
    protected List<Carriage> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Carriage> carriages = new ArrayList<>();
        while (resultSet.next()) {
            Carriage carriage = new Carriage();
            carriage.setCarriage_ID(resultSet.getInt(1));
            carriage.setTrain_ID(resultSet.getInt(2));
            carriage.setCarriageNumber(resultSet.getInt(3));
            carriage.setType(resultSet.getString(4));
            carriage.setTotalSeats(resultSet.getInt(5));
            carriage.setReservedSeats(resultSet.getInt(6));
            carriages.add(carriage);
        }
        return carriages;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, Carriage object) throws SQLException {
        statement.setInt(1, object.getTrain_ID());
        statement.setInt(2, object.getCarriageNumber());
        statement.setString(3, object.getType());
        statement.setInt(4, object.getTotalSeats());
        statement.setInt(5, object.getReservedSeats());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, Carriage object) throws SQLException {
        statement.setInt(1, object.getTrain_ID());
        statement.setInt(2, object.getCarriageNumber());
        statement.setString(3, object.getType());
        statement.setInt(4, object.getTotalSeats());
        statement.setInt(5, object.getReservedSeats());
        statement.setInt(6, object.getCarriage_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, Carriage object) throws SQLException {
        statement.setInt(1, object.getCarriage_ID());
    }
    
    public CarriageDAO(Connection connection) {
        super.connection = connection;
    }
}
