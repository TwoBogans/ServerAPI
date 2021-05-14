package io.servertap;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Util {

    public static String getFormattedDate(long l){
        Date date = new Date(l);
        return new SimpleDateFormat("E, MMM dd yyyy").format(date);
    }

    public static String getFormattedTime(long l) {
        long ticks = l / 20;
        int days = (int) TimeUnit.SECONDS.toDays(ticks);
        int hours = (int) (TimeUnit.SECONDS.toHours(ticks) - TimeUnit.DAYS.toHours(days));
        int minutes = (int) (TimeUnit.SECONDS.toMinutes(ticks) - TimeUnit.HOURS.toMinutes(hours) - TimeUnit.DAYS.toMinutes(days));
        int seconds = (int) (TimeUnit.SECONDS.toSeconds(ticks) - TimeUnit.MINUTES.toSeconds(minutes) - TimeUnit.HOURS.toSeconds(hours) - TimeUnit.DAYS.toSeconds(days));
        if (days != 0) return days + " days, " + hours + " hours, " + minutes + " mins, " + seconds + " secs";
        else if (hours != 0) return hours + " hours, " + minutes + " minutes, " + seconds + " seconds";
        else if (minutes != 0) return minutes + " minutes, " + seconds + " seconds";
        else return seconds + " seconds";
    }

}
