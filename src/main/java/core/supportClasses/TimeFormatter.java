package core.supportClasses;


import java.util.concurrent.TimeUnit;


public class TimeFormatter {
    String time;
    private long total;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


    public void setTotal(long total) {
        this.total = total;
        long hours = TimeUnit.MILLISECONDS.toHours(total);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(total) - TimeUnit.HOURS.toMinutes(hours);
        time = String.format("%02d : %02d", hours, minutes);
    }
}
