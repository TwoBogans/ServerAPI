package io.servertap.api.v1.models.stats;

import com.google.gson.annotations.Expose;

public class Placed {

    @Expose
    private long obsidian;

    @Expose
    private long enderChests;

    public void setObsidian(long obsidian) {
        this.obsidian = obsidian;
    }

    public long getObsidian() {
        return obsidian;
    }

    public void setEnderChests(long enderChests) {
        this.enderChests = enderChests;
    }

    public long getEnderChests() {
        return enderChests;
    }
}
