package io.servertap;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Util implements Runnable {

    public static String getFormattedDate(long l){
        Date date = new Date(l);
        return new SimpleDateFormat("E, MMM dd yyyy").format(date);
    }

    public static String getFormattedTicks(long l) {
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

    public static String getReadableTime(long ms) {
        long timeDelta = ms;

        timeDelta /= 1000;
        int seconds = (int) (timeDelta % 60);
        timeDelta /= 60;
        int minutes = (int) (timeDelta % 60);
        timeDelta /= 60;
        int hours = (int) (timeDelta % 24);
        int days = (int) (timeDelta / 24);

        if (days != 0) return days + " days, " + hours + " hours, " + minutes + " mins, " + seconds + " secs";
        else if (hours != 0) return hours + " hours, " + minutes + " minutes, " + seconds + " seconds";
        else if (minutes != 0) return minutes + " minutes, " + seconds + " seconds";
        else return seconds + " seconds";
    }

    private static final long[] TICKS = new long[600];
    private static int TICK_COUNT = 0;

    public static UUID safeUUID(String input) {
        try {
            return UUID.fromString(input);
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }

    public static double getTPSFormatted() {
        try {
            double tpsDouble = getTPS(); if (tpsDouble > 19.95) tpsDouble = 20;
            DecimalFormat df = new DecimalFormat("#.##");
            return Double.parseDouble(df.format(tpsDouble));
        } catch (Exception e) {
            return 69;
        }
    }

    public static double getTPS() {
        return getTPS(100);
    }

    public static double getTPS(int ticks) {
        if (TICK_COUNT < ticks) return 20;
        int target = (TICK_COUNT - 1 - ticks) % TICKS.length;
        long elapsed = System.currentTimeMillis() - TICKS[target];
        return ticks / (elapsed / 1000d);
    }

    public void run() {
        TICKS[(TICK_COUNT % TICKS.length)] = System.currentTimeMillis();
        TICK_COUNT += 1;
    }

}
