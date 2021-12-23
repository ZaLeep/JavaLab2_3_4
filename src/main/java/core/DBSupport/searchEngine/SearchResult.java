package core.DBSupport.searchEngine;

import core.entities.railway.realEstate.Station;
import core.entities.railway.rollingStock.Carriage;
import core.entities.railway.rollingStock.Train;

import java.util.List;

/**
 * Container for all data about train, route ect. for displaying to user.
 */
public class SearchResult {

    private int result_ID;
    private Train train;
    private Station departStation;
    private long departTime;
    private long wayTime;
    private Station destStation;
    private long destTime;
    private List<Carriage> carriages;
    private double price;
    private RouteInfo routeInfo;

    public int getResult_ID() {
        return result_ID;
    }

    public void setResult_ID(int result_ID) {
        this.result_ID = result_ID;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Station getDepartStation() {
        return departStation;
    }

    public void setDepartStation(Station departStation) {
        this.departStation = departStation;
    }

    public long getDepartTime() {
        return departTime;
    }

    public void setDepartTime(long departTime) {
        this.departTime = departTime;
    }

    public long getWayTime() {
        return wayTime;
    }

    public void setWayTime(long wayTime) {
        this.wayTime = wayTime;
    }

    public Station getDestStation() {
        return destStation;
    }

    public void setDestStation(Station destStation) {
        this.destStation = destStation;
    }

    public long getDestTime() {
        return destTime;
    }

    public void setDestTime(long destTime) {
        this.destTime = destTime;
    }

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RouteInfo getRouteInfo() {
        return routeInfo;
    }

    public void setRouteInfo(RouteInfo routeInfo) {
        this.routeInfo = routeInfo;
    }
}
