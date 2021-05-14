package io.servertap.api.v1.models;

import com.google.gson.annotations.Expose;

public class Statistics {

    @Expose
    private long obsidianPlaced;

    @Expose
    private long enderChestsMined;

    @Expose
    private long distanceFlown;

    public void setDistanceFlown(long distanceFlown) {
        this.distanceFlown = distanceFlown;
    }

    public long getDistanceFlown() {
        return distanceFlown;
    }

    public void setEnderChestsMined(long enderChestsMined) {
        this.enderChestsMined = enderChestsMined;
    }

    public long getEnderChestsMined() {
        return enderChestsMined;
    }

    public void setObsidianPlaced(long obsidianPlaced) {
        this.obsidianPlaced = obsidianPlaced;
    }

    public long getObsidianPlaced() {
        return obsidianPlaced;
    }


}
