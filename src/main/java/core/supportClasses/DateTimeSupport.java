package core.supportClasses;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public final class DateTimeSupport {
    private static final Logger log = Logger.getLogger(DateTimeSupport.class.getName());

    public static long parseDate(String date) {
        long result = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        try {
            result = dateFormat.parse(date).getTime();
        } catch (ParseException e) {
            log.error("String parsing failure", e);
        }
        return result;
    }

    public static int parseTime(String time) {
        String hours = time.split(":")[0];
        String minutes = time.split(":")[1];
        try {
            return (Integer.parseInt(hours) * 60 + Integer.parseInt(minutes)) * 60 * 1000;
        } catch (NumberFormatException e) {
            log.error("String parsing failure", e);
        }
        return 0;


    }

    public static long parseDateTime(String dateTime) {
        long result = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+3"));
        try {
            result = dateFormat.parse(dateTime).getTime();
        } catch (ParseException e) {
            log.error("String parsing failure", e);
        }
        return result;
    }

}
