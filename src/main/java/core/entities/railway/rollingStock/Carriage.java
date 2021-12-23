package core.entities.railway.rollingStock;

public class Carriage {
    private int carriage_ID;
    private int train_ID;
    private int carriageNumber;
    private String type;
    private int totalSeats;
    private int reservedSeats;

    public int getCarriage_ID() {
        return carriage_ID;
    }

    public void setCarriage_ID(int carriage_ID) {
        this.carriage_ID = carriage_ID;
    }

    public int getTrain_ID() {
        return train_ID;
    }

    public void setTrain_ID(int train_ID) {
        this.train_ID = train_ID;
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(int carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getReservedSeats() {
        return reservedSeats;
    }

    public void setReservedSeats(int reservedSeats) {
        this.reservedSeats = reservedSeats;
    }
}
