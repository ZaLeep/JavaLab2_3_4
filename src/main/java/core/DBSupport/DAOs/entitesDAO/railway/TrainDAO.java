package core.DBSupport.DAOs.entitesDAO.railway;

import core.DBSupport.DAOs.AbstractDAO;
import core.entities.railway.realEstate.Station;
import core.entities.railway.rollingStock.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Data access object for train.
 */
public class TrainDAO extends AbstractDAO<Train> {
    /**
     * Returns list of trains on specified route.
     *
     * @param route_ID route.
     * @return list of trains.
     */
    public List<Train> getByRouteID(int route_ID) {
        String sql = "SELECT * FROM trains WHERE route_ID=?";
        List<Train> trains = null;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, route_ID);
            trains = parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            log.error("Route id getting failure", e);
        }
        return trains;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO trains (route_ID) VALUES (?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM trainsS WHERE train_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE trains SET route_ID=? WHERE train_ID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM trains WHERE train_ID=?";
    }

    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM trains";
    }

    @Override
    protected List<Train> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Train> trains = new ArrayList<>();
        while (resultSet.next()) {
            Train train = new Train();
            train.setTrain_ID(resultSet.getInt(1));
            train.setRoute_ID(resultSet.getInt(2));
            train.setPrice(resultSet.getInt(3));
            trains.add(train);
        }
        return trains;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, Train object) throws SQLException {
        statement.setInt(1, object.getRoute_ID());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, Train object) throws SQLException {
        statement.setInt(1, object.getRoute_ID());
        statement.setInt(2, object.getTrain_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, Train object) throws SQLException {
        statement.setInt(1, object.getTrain_ID());
    }


    public TrainDAO(Connection connection) {
        super.connection = connection;
    }
}
