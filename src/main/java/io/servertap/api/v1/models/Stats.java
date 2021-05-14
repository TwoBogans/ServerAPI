package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;

import java.util.Calendar;

/**
 * A Bukkit/Spigot/Paper server
 */
public class Stats {

    @Expose
    private String size;

    @Expose
    private int players;

    @Expose
    private long age;

    @Expose
    private int years;

    @Expose
    private int months;

    @Expose
    private int days;

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPlayers() {
        return players;
    }

    public void setPlayers(int players) {
        this.players = players;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getYears() {
        return years;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getMonths() {
        return months;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getDays() {
        return days;
    }
}
