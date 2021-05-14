package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;

public class Performance {

    @Expose
    private double tps;

    @Expose
    private Long uptimeLong;

    @Expose
    private Long totalMemory = 0L;

    @Expose
    private Long maxMemory = 0L;

    @Expose
    private Long freeMemory = 0L;

    @Expose
    private Integer cpus = 0;

    public double getTps() {
        return tps;
    }

    public void setTps(double tps) {
        this.tps = tps;
    }

    public Long getUptimeLong() { return uptimeLong; }

    public void setUptimeLong(Long uptime) { this.uptimeLong = uptime; }

    public Long getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(Long totalMemory) {
        this.totalMemory = totalMemory;
    }

    public Long getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(Long maxMemory) {
        this.maxMemory = maxMemory;
    }

    public Long getFreeMemory() {
        return freeMemory;
    }

    public void setFreeMemory(Long freeMemory) {
        this.freeMemory = freeMemory;
    }

    public Integer getCpus() { return cpus; }

    public void setCpus(Integer cpus) {
        this.cpus = cpus;
    }



}
