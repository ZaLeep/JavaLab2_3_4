package core.entities.railway.realEstate;

public class Route {
    private int route_ID;
    private int departStation_ID;
    private long departTime;
    private int destStation_ID;
    private long destTime;


    public int getRoute_ID() {
        return route_ID;
    }

    public void setRoute_ID(int route_ID) {
        this.route_ID = route_ID;
    }

    public int getDepartStation_ID() {
        return departStation_ID;
    }

    public void setDepartStation_ID(int departStation_ID) {
        this.departStation_ID = departStation_ID;
    }

    public long getDepartTime() {
        return departTime;
    }

    public void setDepartTime(long departTime) {
        this.departTime = departTime;
    }

    public int getDestStation_ID() {
        return destStation_ID;
    }

    public void setDestStation_ID(int destStation_ID) {
        this.destStation_ID = destStation_ID;
    }

    public long getDestTime() {
        return destTime;
    }

    public void setDestTime(long destTime) {
        this.destTime = destTime;
    }
}
