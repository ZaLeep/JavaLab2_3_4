package core.DBSupport.searchEngine;


import org.apache.log4j.Logger;
import core.DBSupport.DAOs.DAOFactory;


import core.DBSupport.connectionPool.ConnectionPool;
import core.entities.railway.realEstate.Station;
import core.entities.railway.rollingStock.Train;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Search {

    private static final Logger log = Logger.getLogger(Search.class.getName());

    private Station departStation;
    private Station destStation;
    private String departDate;
    private Connection connection;

    public List<SearchResult> search() {

        List<Train> trains = searchTrains();

        List<SearchResult> searchResults = new ArrayList<>();
        int resultNumber = 1;
        for (Train train : trains) {
            SearchResult searchResult = new SearchResult();
            searchResult.setResult_ID(resultNumber++);
            searchResult.setTrain(train);
            searchResult.setDepartStation(departStation);
            searchResult.setDestStation(destStation);
            long departTime = determineDepartTime(train.getRoute_ID(), departStation.getStation_ID());
            long destTime = determineArriveTime(train.getRoute_ID(), destStation.getStation_ID());
            long wayTime = destTime - departTime;
            searchResult.setDepartTime(determineDepartTime(train.getRoute_ID(), departStation.getStation_ID()));
            searchResult.setWayTime(wayTime);
            searchResult.setDestTime(destTime);
            searchResult.setCarriages(DAOFactory.getCarriageDAO(connection).getNotFullCarriages(train.getTrain_ID()));
            searchResult.setPrice(train.getPrice());
            searchResults.add(searchResult);

        }
        return searchResults;
    }

    private List<Train> searchTrains() {

        List<Train> potentialTrains = new ArrayList<>();

        List<Integer> routes = searchRoute_IDs();
        
        for (Integer route_ID : routes) {
            potentialTrains.addAll(DAOFactory.getTrainDAO(connection).getByRouteID(route_ID));
        }
        String sql = "SELECT * FROM trains JOIN carriages ON trains.train_ID = carriages.train_ID\n" +
                "WHERE trains.train_ID=? AND carriages.reservedSeats < carriages.totalSeats";
        List<Train> result = new ArrayList<>();
        List<Integer> selectedTrainsIDs = new ArrayList<>();
        for (Train potentialTrain : potentialTrains) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, potentialTrain.getTrain_ID());
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Train t = new Train();
                    t.setTrain_ID(rs.getInt(1));
                    t.setRoute_ID(rs.getInt(2));
                    /*
                    Check, if founded train already exist in result list. If true, don't add it.
                     */
                    if (!selectedTrainsIDs.contains(t.getTrain_ID())) {
                        result.add(potentialTrain);
                        selectedTrainsIDs.add(potentialTrain.getTrain_ID());
                    }
                }
            } catch (SQLException e) {
                log.error("Train search failure", e);
            }
        }
        return result;
    }

    private List<Integer> searchRoute_IDs() {
        List<Integer> routes_IDs = new ArrayList<>();
        String sql = "SELECT routes.route_ID FROM routes WHERE (departStation_ID = ?) AND (destStation_ID = ?) AND (DATE(departtime) = DATE(?))";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, departStation.getStation_ID());
            statement.setInt(2, destStation.getStation_ID());
            statement.setString(3, departDate);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                if (!routes_IDs.contains(id)) {
                    routes_IDs.add(id);
                }
            }
        } catch (SQLException e) {
            log.error("Route search failure", e);
        }
        return routes_IDs;
    }

    public Search(Station departStation, Station destStation, String departDate) {
        this.departStation = departStation;
        this.destStation = destStation;
        this.departDate = departDate;
        connection = ConnectionPool.getInstance().getConnection();
    }


    private long determineDepartTime(int route_ID, int station_ID) {
        long date = DAOFactory.getRouteDAO(connection).getDepartTime(route_ID, station_ID);
        return date;
    }

    private long determineArriveTime(int route_ID, int station_ID) {
        long date = DAOFactory.getRouteDAO(connection).getDestTime(route_ID, station_ID);
        return date;
    }
}
