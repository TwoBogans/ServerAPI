package io.servertap.api.v1.models;

import lombok.Getter;
import lombok.Setter;
import me.clip.placeholderapi.libs.gson.annotations.Expose;

public class Performance {

    @Expose
    @Getter
    @Setter
    private double tps;

    @Expose
    @Getter
    @Setter
    private Long uptimeLong;

    @Expose
    @Getter
    @Setter
    private Long totalMemory;

    @Expose
    @Getter
    @Setter
    private Long maxMemory;

    @Expose
    @Getter
    @Setter
    private Long freeMemory;

    @Expose
    @Getter
    @Setter
    private Integer cpus;


}
