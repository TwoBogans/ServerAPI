package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.Setter;

import java.util.Calendar;

public class Stats {

    @Expose
    @Getter
    @Setter
    private double size;

    @Expose
    @Getter
    @Setter
    private int players;

    @Expose
    @Getter
    @Setter
    private long age;

    @Expose
    @Getter
    @Setter
    private int years;

    @Expose
    @Getter
    @Setter
    private int months;

    @Expose
    @Getter
    @Setter
    private int days;

}
