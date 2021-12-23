package core.entities.railway.realEstate;

public class Station {
    private int station_ID;
    private String name;
    private String city;
    private String state;
    private String country;

    public int getStation_ID() {
        return station_ID;
    }

    public void setStation_ID(int station_ID) {
        this.station_ID = station_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
