package core.DBSupport.DAOs.entitesDAO.railway;

import core.DBSupport.DAOs.AbstractDAO;
import core.entities.railway.realEstate.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StationDAO extends AbstractDAO<Station> {
    /**
     * Returns specified by id station name.
     *
     * @param id station id
     * @return name
     */
    public String getName(int id) {
        String name = null;
        String sql = "SELECT s_name FROM stations WHERE station_ID=?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                name = rs.getString(1);
            }
        } catch (SQLException e) {
            log.error("Getting station name failure", e);
        }
        return name;
    }

    /**
     * Return all stations names.
     *
     * @return list of names.
     */
    public List<String> getAllNames() {
        String sql = "SELECT s_name FROM stations";
        List<String> names = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String name = rs.getString(1);
                names.add(name);
            }
        } catch (SQLException e) {
            log.error("All station names getting failure", e);
        }
        return names;
    }

    /**
     * Returns station instance.
     *
     * @param name station name.
     * @return station.
     */
    public Station getByName(String name) {
        String sql = "SELECT * FROM stations WHERE s_name=?";
        List<Station> stations;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            stations = parseResultSet(statement.executeQuery());
            if (stations.size() != 0) {
                return stations.iterator().next();
            }
        } catch (SQLException e) {
            log.error("Station name getting failure", e);
        }
        return null;
    }


    @Override
    protected String getInsertQuery() {
        return "INSERT INTO stations(s_name, city, s_state, country) VALUES (?,?,?,?)";
    }

    @Override
    protected String getByIdQuery() {
        return "SELECT * FROM stations WHERE station_ID=?";
    }

    @Override
    protected String getUpdateQuery() {
        return "UPDATE stations SET s_name=?, city=?, s_state=?, country=? WHERE station_ID=?";
    }

    @Override
    protected String getDeleteQuery() {
        return "DELETE FROM stations WHERE station_ID=?";
    }

    @Override
    protected String getGetAllQuery() {
        return "SELECT * FROM stations";
    }

    @Override
    protected List<Station> parseResultSet(ResultSet resultSet) throws SQLException {
        List<Station> stations = new ArrayList<>();
        while (resultSet.next()) {
            Station station = new Station();
            station.setStation_ID(resultSet.getInt(1));
            station.setName(resultSet.getString(2));
            station.setCity(resultSet.getString(3));
            station.setState(resultSet.getString(4));
            station.setCountry(resultSet.getString(5));
            stations.add(station);
        }
        return stations;
    }

    @Override
    protected void prepareInsertStatement(PreparedStatement statement, Station object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getCity());
        statement.setString(3, object.getState());
        statement.setString(4, object.getCountry());
    }

    @Override
    protected void prepareUpdateStatement(PreparedStatement statement, Station object) throws SQLException {
        statement.setString(1, object.getName());
        statement.setString(2, object.getCity());
        statement.setString(3, object.getState());
        statement.setString(4, object.getCountry());
        statement.setInt(5, object.getStation_ID());
    }

    @Override
    protected void prepareDeleteStatement(PreparedStatement statement, Station object) throws SQLException {
        statement.setInt(1, object.getStation_ID());
    }


    public StationDAO(Connection connection) {
        super.connection = connection;
    }
}
