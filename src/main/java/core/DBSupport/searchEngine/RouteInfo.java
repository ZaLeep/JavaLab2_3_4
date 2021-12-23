package core.DBSupport.searchEngine;

import core.entities.railway.realEstate.Route;
import core.entities.railway.realEstate.Station;

import java.util.List;

public class RouteInfo {
    private Route route;
    private Station departStation;
    private Station destStation;
    private List<Station> stations;

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
    
    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public Station getDepartStation() {
        return departStation;
    }

    public void setDepartStation(Station departStation) {
        this.departStation = departStation;
    }

    public Station getDestStation() {
        return destStation;
    }

    public void setDestStation(Station destStation) {
        this.destStation = destStation;
    }
}
