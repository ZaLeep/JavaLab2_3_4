package core.entities.railway.rollingStock;

public class Train {
    private int train_ID;
    private int route_ID;
    private double price;

    public int getTrain_ID() {
        return train_ID;
    }

    public void setTrain_ID(int train_ID) {
        this.train_ID = train_ID;
    }

    public int getRoute_ID() {
        return route_ID;
    }

    public void setRoute_ID(int route_ID) {
        this.route_ID = route_ID;
    }
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
