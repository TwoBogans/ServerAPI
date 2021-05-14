package io.servertap.api.v1.models.stats;

import com.google.gson.annotations.Expose;

public class Statistics {

    @Expose
    private long playerKills;

    @Expose
    private long mobKills;

    @Expose
    private long deaths;

    @Expose
    private long distanceFlown;

    @Expose
    private Mined mined;

    @Expose
    private Placed placed;

    public void setPlayerKills(long playerKills) {
        this.playerKills = playerKills;
    }

    public long getPlayerKills() {
        return playerKills;
    }

    public void setMobKills(long mobKills) {
        this.mobKills = mobKills;
    }

    public long getMobKills() {
        return mobKills;
    }

    public void setDeaths(long deaths) {
        this.deaths = deaths;
    }

    public long getDeaths() {
        return deaths;
    }

    public void setMined(Mined mined) {
        this.mined = mined;
    }

    public Mined getMined() {
        return mined;
    }

    public void setPlaced(Placed placed) {
        this.placed = placed;
    }

    public Placed getPlaced() {
        return placed;
    }

    public void setDistanceFlown(long distanceFlown) { this.distanceFlown = distanceFlown; }

    public long getDistanceFlown() { return distanceFlown; }


}
