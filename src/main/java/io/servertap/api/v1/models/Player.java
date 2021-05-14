package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;
import io.servertap.Util;

/**
 * An online player
 */
public class Player {
    @Expose
    private String uuid;

    @Expose
    private String displayName;

    @Expose
    private long ticksAlive;

    @Expose
    private long joinDateMillis;

    @Expose
    private long playTimeTicks;

    @Expose
    private String joinDate;

    @Expose
    private String playTime;

    @Expose
    private Statistics statistics;

    public void setStatistics(Statistics statistics) { this.statistics = statistics; }

    public Statistics getStatistics() { return statistics; }

    public void setTicksAlive(long ticksAlive) { this.ticksAlive = ticksAlive; }

    public long getTicksAlive() { return ticksAlive; }

    public void setJoinDateMillis(long joinDateMillis) {
        this.joinDateMillis = joinDateMillis;
        this.joinDate = Util.getFormattedDate(joinDateMillis);
    }

    public String getJoinDate() {
        return joinDate;
    }

    public long getJoinDateMillis() { return joinDateMillis; }

    public void setPlayTimeTicks(long playTimeTicks) {
        this.playTimeTicks = playTimeTicks;
        this.playTime = Util.getFormattedTime(playTimeTicks);
    }

    public String getPlayTime() {
        return playTime;
    }

    public long getPlayTimeTicks() { return playTimeTicks; }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Player uuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Player displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }
}
