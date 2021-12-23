package core.DBSupport.DAOs.entitesDAO.railway;

import core.DBSupport.DAOs.AbstractDAO;
import core.entities.railway.realEstate.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static core.supportClasses.DateTimeSupport.parseDateTime;

public class RouteDAO extends AbstractDAO<Route> {
    /**
     * Returns destination time for specified route.
     *
     * @param route_ID   route
     * @param station_ID station
     * @return time
     */
    public long getDestTime(int route_ID, int station_ID) {
        long date = 0;
        String sql = "SELECT destTime FROM routes WHERE route_ID=? AND destStation_ID=?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, route_ID);
            statement.setInt(2, station_ID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                date = parseDateTime(rs.getString(1));
            }
        } catch (SQLException e) {
            log.error("Can't get destination time", e);
        }
        return date;
    }

    /**
     * Returns department time for specified station
     *
     * @param route_ID   route
     * @param station_ID station
     * @return time
     */
    public long getDepartTime(int route_ID, int station_ID) {
        long date = 0;
        String sql = "SELECT departTime FROM routes WHERE route_ID=? AND departStation_ID=?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(2, station_ID);
            statement.setInt(1, route_ID);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                date = parseDateTime(rs.getString(1));
            }
        } catch (SQLException e) {
            log.error("Can't get depart time", e);
        }
        return date;
    }

    @Override
    protected String getInsertQuery() {
        return "INSERT INTO routes (departStation_ID, departTime, destStation_ID, destTime) VALUES(?,?,?,?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM routes WHERE route_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE routes SET departStation_ID=?, departTime=?, destStation_ID=?, destTime=? WHERE route_ID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM routes WHERE route_ID=?";
    }

    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM routes";
    }


    @Override
    protected List<Route> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Route> routes = new ArrayList<>();
        while (resultSet.next()) {
            Route route = new Route();
            route.setRoute_ID(resultSet.getInt(1));
            route.setDepartStation_ID(resultSet.getInt(2));
            route.setDepartTime(resultSet.getTimestamp(3).getTime());
            route.setDestStation_ID(resultSet.getInt(4));
            route.setDestTime(resultSet.getTimestamp(5).getTime());
            routes.add(route);
        }
        return routes;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, Route object) throws SQLException {
        statement.setInt(1, object.getDepartStation_ID());
        statement.setTimestamp(2, new Timestamp(object.getDepartTime()));
        statement.setInt(3, object.getDestStation_ID());
        statement.setTimestamp(4, new Timestamp(object.getDestTime()));
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, Route object) throws SQLException {
        statement.setInt(1, object.getDepartStation_ID());
        statement.setTimestamp(2, new Timestamp(object.getDepartTime()));
        statement.setInt(3, object.getDestStation_ID());
        statement.setTimestamp(4, new Timestamp(object.getDestTime()));
        statement.setInt(5, object.getRoute_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, Route object) throws SQLException {
        statement.setInt(1, object.getRoute_ID());
    }

    /**
     * Simple constructor.
     *
     * @param connection connection with database
     */
    public RouteDAO(Connection connection) {
        super.connection = connection;
    }
}
