package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;

public class Server {

    @Expose
    private String name;

    @Expose
    private String motd;

    @Expose
    private String version;

    @Expose
    private String uptime;

    @Expose
    private int online;

    @Expose
    private Performance performance;

    public Server name(String name) {
        this.name = name;
        return this;
    }

    public void setOnline(int online) { this.online = online; }

    public int getOnline() { return online; }

    public void setUptime(String uptime) { this.uptime = uptime; }

    public String getUptime() { return uptime; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setPerformance(Performance performance) { this.performance = performance; }

    public Performance getPerformance() { return performance; }

}
