package core.entities.service;

public class Ticket {
    private int ticket_ID;
    private int user_ID;
    private int train_ID;
    private int carriageNumber;
    private int deptStation_ID;
    private long deptTime;
    private int destStation_ID;
    private long destTime;
    private double price;

    public int getTicket_ID() {
        return ticket_ID;
    }

    public void setTicket_ID(int ticket_ID) {
        this.ticket_ID = ticket_ID;
    }

    public int getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(int user_ID) {
        this.user_ID = user_ID;
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

    public int getDeptStation_ID() {
        return deptStation_ID;
    }

    public void setDeptStation_ID(int deptStation_ID) {
        this.deptStation_ID = deptStation_ID;
    }

    public long getDeptTime() {
        return deptTime;
    }

    public void setDeptTime(long deptTime) {
        this.deptTime = deptTime;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
