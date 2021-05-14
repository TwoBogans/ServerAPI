package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;

public class Server {

    @Expose
    private String name;

    @Expose
    private String version;

    @Expose
    private String tps;

    @Expose
    private int online;

    @Expose
    private Long uptime = 0L;

    @Expose
    private Integer cpus = 0;

    @Expose
    private Long totalMemory = 0L;

    @Expose
    private Long maxMemory = 0L;

    @Expose
    private Long freeMemory = 0L;

    public Integer getCpus() {
        return cpus;
    }

    public void setCpus(Integer cpus) {
        this.cpus = cpus;
    }

    public Long getUptime() {
        return uptime;
    }

    public void setUptime(Long uptime) {
        this.uptime = uptime;
    }

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

    public Server name(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps;
    }

    public int getOnline() { return online; }

    public void setOnline(int online) { this.online = online; }

}
